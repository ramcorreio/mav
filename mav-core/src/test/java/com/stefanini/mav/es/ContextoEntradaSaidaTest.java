package com.stefanini.mav.es;

import java.lang.reflect.Field;
import java.util.Date;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.es.MapSubSubBean.SubBean;
import com.stefanini.mav.es.MapSubSubBean.SubSubBean;
import com.stefanini.mav.mensagem.Cabecalho;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
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
		expected.setSubSubBean(new SubSubBean());
		expected.getSubSubBean().setSubBean(new SubBean());
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
		
		SubBeanHerdado b = new SubBeanHerdado();
		Map<String, Field> campos = ContextoEntradaSaida.getAllFields(b.getClass());
		MatcherAssert.assertThat(campos.keySet(), Matchers.hasSize(7));
		MatcherAssert.assertThat(campos, Matchers.hasKey("conta"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("texto"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("hoje"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subBean"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subBean.nome"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subBean.subSubBean"));
		MatcherAssert.assertThat(campos, Matchers.hasKey("subBean.subSubBean.nome"));
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
	
	protected SimpleMapper criarSimpleMapper(Class<?> tipo, String nome, String path, int tamanho, boolean obrigatorio, int scale, boolean trim, String formato, String comparador) {
		
		return ContextoEntradaSaida.criarSimpleMapper(tipo, nome, path, tamanho, obrigatorio, scale, trim, formato, comparador);
	}
	
	private <T> void assertMapper(BaseMapper actual, BaseMapper expected) {
		
		if(BeanMapper.class.isInstance(actual)) {
			BeanMapper actualBean = BeanMapper.class.cast(actual);
			BeanMapper expectedBean = BeanMapper.class.cast(expected);
			assertThat(actualBean.getPath(), actualBean.getMappers(), expectedBean.getMappers());
		}
		else {
			MatcherAssert.assertThat(actual.getPath(), actual, BeanMatchers.theSameAs(expected));	
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
		
		BeanMapper subSubBean = criarBeanMapper(SubSubBean.class, "subSubBean", "subSubBean");
		expected.add(subSubBean);
		
		BeanMapper subBean = criarBeanMapper(SubBean.class, "subBean", "subSubBean.subBean");
		subSubBean.getMappers().add(subBean);
		subBean.getMappers().add(criarSimpleMapper(Integer.class, "conta", "subSubBean.subBean.conta", 3));
		subBean.getMappers().add(criarSimpleMapper(String.class, "texto", "subSubBean.subBean.texto", 10));
		
		subSubBean.getMappers().add(criarSimpleMapper(Date.class, "hoje", "subSubBean.hoje", 8));
		
		List<BaseMapper> mappers = ContextoEntradaSaida.getListaMapper(MapSubSubBean.class);
		assertThat(mappers, expected);
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
		
		List<BaseMapper> mappers = ContextoEntradaSaida.getListaMapper(MapSubBeanHerdado.class);
		assertThat(mappers, expected);
		
	}	
	
	
	@Test
	public void contarTamanhoBean() throws MapeamentoNaoEncontrado {
		
		BeanMapper subSubBean = criarBeanMapper(SubSubBean.class, "subSubBean", "subSubBean");
		
		List<BaseMapper> mappers = ContextoEntradaSaida.getListaMapper(MapSubSubBean.class);
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
	

}
