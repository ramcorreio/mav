package com.stefanini.mav.es;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.es.SimpleAttr.AttrImpl;
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
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		
		MapAtributoBean b = ContextoEntradaSaida.ler(entrada, MapAtributoBean.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}
	
	@Test
	public void lerAtributoSubBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    ";
		MapSubBean expected = new MapSubBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setBean(new SubBean());
		expected.getBean().setConta(45);
		expected.getBean().setTexto("Opa!!!");
		
		MapSubBean b = ContextoEntradaSaida.ler(entrada, MapSubBean.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void lerAtributoSubBeanHerdado() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    04122015";
		MapSubBeanHerdado expected = new MapSubBeanHerdado();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setBean(new SubBeanHerdado());
		expected.getBean().setConta(45);
		expected.getBean().setTexto("Opa!!!");
		expected.getBean().setHoje(UtilsDate.parse("04122015"));
		
		MapSubBeanHerdado b = ContextoEntradaSaida.ler(entrada, MapSubBeanHerdado.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}
	
	@Test
	public void lerAtributoSubSubBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    04122015";
		MapSubSubBean expected = new MapSubSubBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setBean(new SubSubBean());
		expected.getBean().setBean(new SubBean());
		expected.getBean().getBean().setConta(45);
		expected.getBean().getBean().setTexto("Opa!!!");
		expected.getBean().setHoje(UtilsDate.parse("04122015"));
		
		MapSubSubBean b = ContextoEntradaSaida.ler(entrada, MapSubSubBean.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}
	
	@Test
	public void posicaoInicio() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    ";
		MapPosicaoInicio expected = new MapPosicaoInicio();
		//expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		
		MapPosicaoInicio b = ContextoEntradaSaida.ler(entrada, MapPosicaoInicio.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void listarCampos() {
		
		SubBeanHerdado b = new SubBeanHerdado();
		List<Field> campos = ContextoEntradaSaida.getAllFields(b.getClass());
		MatcherAssert.assertThat(campos.size(), Matchers.equalTo(3));
		MatcherAssert.assertThat(campos.get(0).getName(), Matchers.equalTo("conta"));
		MatcherAssert.assertThat(campos.get(1).getName(), Matchers.equalTo("texto"));
		MatcherAssert.assertThat(campos.get(2).getName(), Matchers.equalTo("hoje"));
	}
	
	@Test
	public void localizarCampo() {
		
		SubBeanHerdado b = new SubBeanHerdado();
		List<Field> campos = ContextoEntradaSaida.getAllFields(b.getClass());
		Field f = ContextoEntradaSaida.findField("texto", campos);
		MatcherAssert.assertThat(f.getName(), Matchers.equalTo("texto"));
	}
	
	private SimpleAttr criarMapper(Object instance, final String path, final int tamanho, final boolean obrigatorio, final int scale, final boolean trim, final String formato) {
		
		SimpleAttr impl = new SimpleAttr();
		impl.setCampo(path);
		impl.setInstance(instance);
		impl.setAttr(new AttrImpl());
		impl.getAttr().setTamanho(tamanho);
		impl.getAttr().setObrigatorio(obrigatorio);
		impl.getAttr().setScale(scale);
		impl.getAttr().setTrim(trim);
		impl.getAttr().setFormato(formato);
		
		return impl;
	}
	
	private SimpleAttr criarMapper(Object instance, int tamanho) {
		return criarMapper(instance, "", tamanho, false, 2, true, "ddMMyyyy");
	}
	
	private SimpleAttr criarMapper(Object instance, String path, int tamanho) {
		return criarMapper(instance, "", tamanho, false, 2, true, "ddMMyyyy");
	}
	
	private SimpleAttr criarMapper(Object instance, String path) {
		return criarMapper(instance, path, 1);
	}
	
	@Test
	public void listarMappers() throws MapeamentoNaoEncontrado {
		
		MapSubSubBean b = new MapSubSubBean();
		
		List<SimpleAttr> expected = new LinkedList<>();
		expected.add(criarMapper(b, "nome", 25));
		expected.add(criarMapper(b, "idade", 3));
		expected.add(criarMapper(b, "data", 8));
		expected.add(criarMapper(b, "temFilhos"));
		expected.add(criarMapper(b, "salario", 9));
		//expected.add(criarMapper("bean.bean.conta", 3));
		//expected.add(criarMapper("bean.bean.texto", 10));
		//expected.add(criarMapper("bean.hoje", 8));
		
		List<SimpleAttr> mapper = ContextoEntradaSaida.getListaMapper(b);
		MatcherAssert.assertThat(mapper, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void carregarCamposParaLer() {
		
		/*BaseMatcher<Field> field = new BaseMatcher<Field>() {
			
		    private final Object expectedValue;

		    public IsEqual(T equalArg) {
		        expectedValue = equalArg;
		    }

			@Override
			public boolean matches(Object item) {
				if (actual == null) {
		            return expected == null;
		        }
		        
		        if (expected != null && isArray(actual)) {
		            return isArray(expected) && areArraysEqual(actual, expected);
		        }
				return false;
			}

			@Override
			public void describeTo(Description description) {
				
			}
			
		};*/
		
		MapSubSubBean b = new MapSubSubBean();
		
		List<TargetRead> expected = new LinkedList<>();
		expected.add(new TargetRead());
		
		int pos = expected.size() - 1;
		expected.get(pos).setInstance(b);
		expected.get(pos).setCampos(new LinkedList<String>());
		expected.get(pos).getCampos().add("nome");
		expected.get(pos).getCampos().add("idade");
		expected.get(pos).getCampos().add("data");
		expected.get(pos).getCampos().add("temFilhos");
		expected.get(pos).getCampos().add("salario");
		
		
		expected.add(new TargetRead());
		pos = expected.size() - 1;
		expected.get(pos).setInstance(new SubSubBean());
		expected.get(pos).setCampos(new LinkedList<String>());
		expected.get(pos).getCampos().add("conta");
		expected.get(pos).getCampos().add("texto");
		expected.get(pos).getCampos().add("hoje");
		
		List<TargetRead> paraLer = ContextoEntradaSaida.getSequenciaLeitura(b);
		MatcherAssert.assertThat(expected, BeanMatchers.theSameAs(paraLer));
	}
}
