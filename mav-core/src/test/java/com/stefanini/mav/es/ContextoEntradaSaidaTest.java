package com.stefanini.mav.es;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.mensagem.Cabecalho;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.util.UtilsDate;

public class ContextoEntradaSaidaTest {
	
	@Test(expected = MapeamentoNaoEncontrado.class)
	public void noMap() throws MapeamentoNaoEncontrado {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523";
		ContextoEntradaSaida.ler(entrada, NoMap.class);
		
	}
	
	@Test
	public void lerAtributoBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523";
		MapAtributoBean expected = new MapAtributoBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		
		MapAtributoBean b = ContextoEntradaSaida.ler(entrada, MapAtributoBean.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void lerAtributoSubBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    ";
		MapSubBean expected = new MapSubBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setBean(new SuperBean());
		expected.getBean().setConta(45);
		expected.getBean().setTexto("Opa!!!");
		
		MapSubBean b = ContextoEntradaSaida.ler(entrada, MapSubBean.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void lerAtributoSubBeanHerdado() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    04122015";
		MapSubBeanHerdado expected = new MapSubBeanHerdado();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setBean(new SubBeanHerdado());
		expected.getBean().setConta(45);
		expected.getBean().setTexto("Opa!!!");
		expected.getBean().setHoje(UtilsDate.parse("04122015", UtilsDate.FormatadorData.DATA));
		
		MapSubBeanHerdado b = ContextoEntradaSaida.ler(entrada, MapSubBeanHerdado.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}
	
	@Test
	public void lerAtributoSubSubBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    04122015";
		MapSubSubBean expected = new MapSubSubBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setSubSubBean(new MapSubSubBean.SubSubBean());
		expected.getSubSubBean().setSubBean(new MapSubSubBean.SubBean());
		expected.getSubSubBean().getSubBean().setConta(45);
		expected.getSubSubBean().getSubBean().setTexto("Opa!!!");
		expected.getSubSubBean().setHoje(UtilsDate.parse("04122015", UtilsDate.FormatadorData.DATA));
		
		MapSubSubBean b = ContextoEntradaSaida.ler(entrada, MapSubSubBean.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}
	
	@Test
	public void checkConfig() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    ";
		MapPosicaoInicio expected = new MapPosicaoInicio(new Cabecalho());
		expected.getCabecalho().setSentidoFluxo(Fluxo.ENTRADA);
		expected.getCabecalho().setCodigo(CodigoMensagem.C0100);
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		
		Cabecalho c = new Cabecalho();
		c.setCodigo(CodigoMensagem.C0100);
		
		MapPosicaoInicio b = ContextoEntradaSaida.ler(entrada, MapPosicaoInicio.class, new Object[]{c});
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void listarCampos() {
		
		MapBeanListaSimples b = new MapBeanListaSimples();
		Map<String, Field> campos = ContextoEntradaSaida.getAllFields(b.getClass());
		MatcherAssert.assertThat(campos.keySet(), Matchers.hasSize(11));
		MatcherAssert.assertThat(campos, Matchers.hasKey("nome"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("idade"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("data"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("temFilhos"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("salario"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.subBean"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.subBean.conta"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.subBean.texto"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.hoje"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("coisas"));
	}
	
	@Test
	public void listarCamposComLista() {
		
		MapBeanListaSimples b = new MapBeanListaSimples();
		Map<String, Field> campos = ContextoEntradaSaida.getAllFields(b.getClass());
		MatcherAssert.assertThat(campos.keySet(), Matchers.hasSize(11));
		MatcherAssert.assertThat(campos, Matchers.hasKey("nome"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("idade"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("data"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("temFilhos"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("salario"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.subBean"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.subBean.conta"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.subBean.texto"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subSubBean.hoje"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("coisas"));
	}
	
	@Test
	public void listarCamposNaoRecursivo() {
		
		SubBeanHerdado b = new SubBeanHerdado();
		Map<String, Field> campos = ContextoEntradaSaida.getAllFields(b.getClass(), false);
		MatcherAssert.assertThat(campos.keySet(), Matchers.hasSize(4));
		MatcherAssert.assertThat(campos, Matchers.hasKey("conta"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("texto"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("hoje"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subBean"));
	}
	
	@Test
	public void localizarCampo() throws MapeamentoNaoEncontrado {
		
		MapSubSubBean b = new MapSubSubBean();
		Field f = ContextoEntradaSaida.findField("subSubBean.subBean.texto", b.getClass());
		MatcherAssert.assertThat(f, Matchers.notNullValue());
		MatcherAssert.assertThat(f.getName(), Matchers.equalTo("texto"));
	}
	
	private BeanMapper criarBeanMapper(Class<?> tipo, String nome, String path) {
		return ContextoEntradaSaida.criarBeanMapper(tipo, nome, path);
	}
	
	protected SimpleMapper criarSimpleMapper(Class<?> tipo, String nome, String path) {
		return criarSimpleMapper(tipo, nome, path, 1);
	}
	
	protected SimpleMapper criarSimpleMapper(Class<?> tipo, String nome, String path, int tamanho) {
		return criarSimpleMapper(tipo, nome, path, tamanho, false, 2, true, "ddMMyyyy", "1");
	}
	
	protected <T extends BaseMapper> ListaMapper<T> criarListaMapper(Class<?> tipo, String nome, String path, int maxSize, T mapper) {
		return ContextoEntradaSaida.criarListaMapper(tipo, nome, path, maxSize, mapper);
	}
	
	protected SimpleMapper criarSimpleMapper(Class<?> tipo, String nome, String path, int tamanho, boolean obrigatorio, int scale, boolean trim, String formato, String comparador) {
		
		return ContextoEntradaSaida.criarSimpleMapper(tipo, nome, path, tamanho, obrigatorio, scale, trim, formato, comparador);
	}
	
	private <T> void assertMapper(BaseMapper actual, BaseMapper expected) {
		
		if(BeanMapper.class.isInstance(actual)) {
			BeanMapper actualBean = BeanMapper.class.cast(actual);
			BeanMapper expectedBean = BeanMapper.class.cast(expected);
			assertThat(actualBean.getPath(), actualBean.getMappers(), expectedBean.getMappers());
			
		}
		else if(ListaMapper.class.isInstance(actual)) {
			ListaMapper<?> actualBean = ListaMapper.class.cast(actual);
			ListaMapper<?> expectedBean = ListaMapper.class.cast(expected);
			
			List<BaseMapper> actualList = new LinkedList<>();
			actualList.add(actualBean.getMapper());
			
			List<BaseMapper> expectedList = new LinkedList<>();
			expectedList.add(expectedBean.getMapper());
			
			assertThat(actualBean.getPath(), actualList, expectedList);
		}
		else {
			MatcherAssert.assertThat(actual.getPath(), actual, BeanMatchers.theSameAs(expected).compareProperty("tipo", Matchers.equalTo(expected.getTipo())));
		}
		
	}
	
	private <T> void assertThat(List<BaseMapper> actual, List<BaseMapper> expected) {
		assertThat("", actual, expected);
	}
	
	private <T> void assertThat(String reason, List<BaseMapper> actual, List<BaseMapper> expected) {
		
		MatcherAssert.assertThat(reason, actual.size(), Matchers.equalTo(expected.size()));
		
		for (int i = 0; i < expected.size(); i++) {
		
			assertMapper(actual.get(i), expected.get(i));
		}
	}
	
	
	
	@Test
	public void listarMappers() throws MapeamentoNaoEncontrado {
		
		List<BaseMapper> expected = new LinkedList<>();
		expected.add(criarSimpleMapper(String.class, "nome", "nome", 25));
		expected.add(criarSimpleMapper(Integer.class, "idade", "idade", 3));
		expected.add(criarSimpleMapper(Date.class, "data", "data", 8));
		expected.add(criarSimpleMapper(Boolean.class, "temFilhos", "temFilhos"));
		expected.add(criarSimpleMapper(Double.class, "salario", "salario", 9));
		
		BeanMapper subSubBean = criarBeanMapper(MapSubSubBean.SubSubBean.class, "subSubBean", "subSubBean");
		expected.add(subSubBean);
		
		BeanMapper subBean = criarBeanMapper(MapSubSubBean.SubBean.class, "subBean", "subSubBean.subBean");
		subSubBean.getMappers().add(subBean);
		subBean.getMappers().add(criarSimpleMapper(String.class, "conta", "subSubBean.subBean.conta", 3));
		subBean.getMappers().add(criarSimpleMapper(String.class, "texto", "subSubBean.subBean.texto", 10));
		
		subSubBean.getMappers().add(criarSimpleMapper(Date.class, "hoje", "subSubBean.hoje", 8));
		
		verificarMappers(MapSubSubBean.class);
	}

	/**
	 * @param <T>
	 * @throws MapeamentoNaoEncontrado
	 */
	private <T> void verificarMappers(Class<T> clazz) throws MapeamentoNaoEncontrado {
		Map<String, BaseMapper> allMappers = mapMapper(ContextoEntradaSaida.getMappersFilhos(clazz));
		Map<String, Field> fields = ContextoEntradaSaida.getAllFields(clazz);
		MatcherAssert.assertThat(allMappers.size(), Matchers.equalTo(fields.size()));
		for (Entry<String, BaseMapper> mapper : allMappers.entrySet()) {
			MatcherAssert.assertThat(true, Matchers.equalTo(fields.containsKey(mapper.getKey())));
		}
	}
	
	@Test
	public void listarMappersHeranca() throws MapeamentoNaoEncontrado {
		
		List<BaseMapper> expected = new LinkedList<>();
		expected.add(criarSimpleMapper(String.class, "nome", "nome", 25));
		expected.add(criarSimpleMapper(Integer.class, "idade", "idade", 3));
		expected.add(criarSimpleMapper(Date.class, "data", "data", 8));
		expected.add(criarSimpleMapper(Boolean.class, "temFilhos", "temFilhos"));
		expected.add(criarSimpleMapper(Double.class, "salario", "salario", 9));
		
		BeanMapper bean = criarBeanMapper(SubBeanHerdado.class, "bean", "bean");
		expected.add(bean);
		bean.getMappers().add(criarSimpleMapper(Integer.class, "conta", "bean.conta", 3));
		bean.getMappers().add(criarSimpleMapper(String.class, "texto", "bean.texto", 10));
		
		bean.getMappers().add(criarSimpleMapper(Date.class, "hoje", "bean.hoje", 8));
		
		//verificarMappers(MapSubBeanHerdado.class);
		List<BaseMapper> mappers = ContextoEntradaSaida.getMappersFilhos(MapSubBeanHerdado.class);
		assertThat(mappers, expected);
	}
	
	@Test
	public void listarMappersLista() throws MapeamentoNaoEncontrado {
		
		List<BaseMapper> expected = new LinkedList<>();
		expected.add(criarSimpleMapper(String.class, "nome", "nome", 25));
		expected.add(criarSimpleMapper(Integer.class, "idade", "idade", 3));
		expected.add(criarSimpleMapper(Date.class, "data", "data", 8));
		expected.add(criarSimpleMapper(Boolean.class, "temFilhos", "temFilhos"));
		expected.add(criarSimpleMapper(Double.class, "salario", "salario", 9));
		
		BeanMapper subSubBean = criarBeanMapper(MapBeanListaSimples.SubSubBean.class, "subSubBean", "subSubBean");
		expected.add(subSubBean);
		
		BeanMapper subBean = criarBeanMapper(MapBeanListaSimples.SubBean.class, "subBean", "subSubBean.subBean");
		subSubBean.getMappers().add(subBean);
		subBean.getMappers().add(criarSimpleMapper(Integer.class, "conta", "subSubBean.subBean.conta", 3));
		subBean.getMappers().add(criarSimpleMapper(String.class, "texto", "subSubBean.subBean.texto", 10));
		
		subSubBean.getMappers().add(criarSimpleMapper(Date.class, "hoje", "subSubBean.hoje", 8));
		
		SimpleMapper arrayMap = criarSimpleMapper(String.class, "[]", "coisas[]", 15);
		ListaMapper<SimpleMapper> coisas = criarListaMapper(List.class, "coisas", "coisas", 4, arrayMap);
		expected.add(coisas);
		List<BaseMapper> mappers = ContextoEntradaSaida.getMappersFilhos(MapBeanListaSimples.class);
		verificarMappers(MapBeanListaSimples.class);
		assertThat(mappers, expected);
	}
	
	private Map<String, BaseMapper> mapMapper(List<BaseMapper> mappers) {
		
		Map<String, BaseMapper> listaMappers = new LinkedHashMap<>();
		for (BaseMapper mapper : mappers) {
			
			listaMappers.put(mapper.getPath(), mapper);
			if(BeanMapper.class.isInstance(mapper)) {
				listaMappers.putAll(mapMapper(BeanMapper.class.cast(mapper).getMappers()));
			}
		}
		return listaMappers;
	}
	
	@Test
	public void contarTamanhoBean() throws MapeamentoNaoEncontrado {
		
		BeanMapper subSubBean = criarBeanMapper(MapSubSubBean.SubSubBean.class, "subSubBean", "subSubBean");
		
		List<BaseMapper> mappers = ContextoEntradaSaida.getMappersFilhos(MapSubSubBean.class);
		BaseMapper paraContar = mappers.get(mappers.indexOf(subSubBean));
		
		MatcherAssert.assertThat(ContextoEntradaSaida.getTamanho(paraContar), Matchers.is(Matchers.equalTo(21)));
	}
	
	@Test
	public void escreverAtributoSubBean() throws MapeamentoNaoEncontrado, ParseException {
		
		MapSubBean entrada = new MapSubBean();
		entrada.setNome("rodrigo afonso macedo");
		entrada.setIdade(37);
		entrada.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		entrada.setTemFilhos(true);
		entrada.setSalario(345.23);
		entrada.setBean(new SuperBean());
		entrada.getBean().setConta(45);
		entrada.getBean().setTexto("Opa!!!");
		
		String expected = "rodrigo afonso macedo    037281119781000034523045Opa!!!    ";
		MatcherAssert.assertThat(expected, Matchers.is(Matchers.equalTo(ContextoEntradaSaida.escrever(entrada))));
	}
	
	
	@Test
	public void lerAtributoLista() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    04122015apartamento    carro          bicicleta                     ";
		MapBeanListaSimples expected = new MapBeanListaSimples();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setSubSubBean(new MapBeanListaSimples.SubSubBean());
		expected.getSubSubBean().setSubBean(new MapBeanListaSimples.SubBean());
		expected.getSubSubBean().getSubBean().setConta(45);
		expected.getSubSubBean().getSubBean().setTexto("Opa!!!");
		expected.getSubSubBean().setHoje(UtilsDate.parse("04122015", UtilsDate.FormatadorData.DATA));
		expected.setCoisas(new LinkedList<String>());
		expected.getCoisas().add("apartamento");
		expected.getCoisas().add("carro");
		expected.getCoisas().add("bicicleta");
		
		MapBeanListaSimples b = ContextoEntradaSaida.ler(entrada, MapBeanListaSimples.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void lerAtributoListaBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    0412201501apartamento    02carro          03bicicleta                       ";
		MapBeanListaBean expected = new MapBeanListaBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setSubSubBean(new MapBeanListaBean.SubSubBean());
		expected.getSubSubBean().setSubBean(new MapBeanListaBean.SubBean());
		expected.getSubSubBean().getSubBean().setConta(45);
		expected.getSubSubBean().getSubBean().setTexto("Opa!!!");
		expected.getSubSubBean().setHoje(UtilsDate.parse("04122015", UtilsDate.FormatadorData.DATA));
		
		expected.setCoisas(new LinkedList<SubBeanLista>());
		
		expected.getCoisas().add(new SubBeanLista());
		expected.getCoisas().get(0).setId(1);
		expected.getCoisas().get(0).setDescricao("apartamento");
		
		expected.getCoisas().add(new SubBeanLista());
		expected.getCoisas().get(1).setId(2);
		expected.getCoisas().get(1).setDescricao("carro");
		
		expected.getCoisas().add(new SubBeanLista());
		expected.getCoisas().get(2).setId(3);
		expected.getCoisas().get(2).setDescricao("bicicleta");
		
		MapBeanListaBean b = ContextoEntradaSaida.ler(entrada, MapBeanListaBean.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void lerAtributoListaInt() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    04122015000000000000000000000000000000000000000000000000000000000000          67";
		MapBeanListaInt expected = new MapBeanListaInt();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978", UtilsDate.FormatadorData.DATA));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setSubSubBean(new MapBeanListaInt.SubSubBean());
		expected.getSubSubBean().setSubBean(new MapBeanListaInt.SubBean());
		expected.getSubSubBean().getSubBean().setConta(45);
		expected.getSubSubBean().getSubBean().setTexto("Opa!!!");
		expected.getSubSubBean().setHoje(UtilsDate.parse("04122015", UtilsDate.FormatadorData.DATA));
		expected.setCoisas(new LinkedList<Integer>());
		/*expected.getCoisas().add(84);
		expected.getCoisas().add(753);
		expected.getCoisas().add(1);*/
		expected.setFiller(AdaptadorTipo.escreverString(10, " "));
		expected.setValor(67);
		
		MapBeanListaInt b = ContextoEntradaSaida.ler(entrada, MapBeanListaInt.class, false);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}

}
