package com.stefanini.mav.es;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.stefanini.mav.es.SimpleAttr.AttrImpl;
import com.stefanini.mav.mensagem.CodigoMensagem;

public class ContextoEntradaSaida {
	
	private final static Map<Class<?>, AdaptadorTipo<?>> adapters = Collections.synchronizedMap(new HashMap<Class<?>, AdaptadorTipo<?>>());
	
	static {
		
		adapters.put(Integer.class, new AdaptadorTipo<Integer>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Integer ler(String in, SimpleAttr map) {
				
				return Integer.valueOf(in);
			}
		});
		
		adapters.put(String.class, new AdaptadorTipo<String>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public String ler(String in, SimpleAttr m) {
				
				/*if(m.getAttr().trim()) {
					return in.trim();
				}
				else {*/
					return in;
				//}
			}
		});
		
		adapters.put(Date.class, new AdaptadorTipo<Date>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Date ler(String in, SimpleAttr m) throws MapeamentoNaoEncontrado {
				
				return Calendar.getInstance().getTime();
				/*try {
					return UtilsDate.parse(in, UtilsDate.FORMATADORES.get(m.getAttr().formato()));
				} catch (ParseException e) {
					
					throw new MapeamentoNaoEncontrado(atributoClasse(m.getCampo().getName(), m.getCampo().getType()), e);
				}*/
			}
		});
		
		adapters.put(Boolean.class, new AdaptadorTipo<Boolean>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Boolean ler(String in, SimpleAttr m) {
				
				return Integer.valueOf(in) == 1;
			}
		});
		
		adapters.put(Double.class, new AdaptadorTipo<Double>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Double ler(String in, SimpleAttr m) throws MapeamentoNaoEncontrado {
				
				/*int scale = m.getAttr().scale();
				int intval = m.getAttr().tamanho() - scale;
				
				DecimalFormat formmatter = (DecimalFormat) DecimalFormat.getInstance();
				formmatter.setGroupingUsed(false);
				formmatter.setMaximumIntegerDigits(intval);
				formmatter.setMinimumFractionDigits(scale);
				formmatter.setMaximumFractionDigits(scale);
				
				//montagem de valor com ponto
				String valorComPonto = in.substring(0, intval);
				valorComPonto += Character.toString(formmatter.getDecimalFormatSymbols().getDecimalSeparator());
				valorComPonto += in.substring(intval);
				
				try {
					return Double.valueOf(formmatter.parse(valorComPonto).doubleValue());
				} catch (ParseException e) {
					throw new MapeamentoNaoEncontrado(e);
				}*/
				return 0d;
			}
		});
		
		adapters.put(CodigoMensagem.class, new AdaptadorTipo<CodigoMensagem>() {
			@Override
			public void escrever() throws MapeamentoNaoEncontrado {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public CodigoMensagem ler(String in, SimpleAttr map) throws MapeamentoNaoEncontrado {
				
				return CodigoMensagem.parse(in);
			}
		});
	}
	
	private static SimpleAttr criarMapper(Object instance, Field field, Mapper map) {
		
		SimpleAttr impl = new SimpleAttr();
		impl.setInstance(instance);
		impl.setCampo(map.path().isEmpty() ? field.getName() : map.path());
		impl.setAttr(new AttrImpl());
		
		impl.getAttr().setFormato(map.formato());
		impl.getAttr().setObrigatorio(map.obrigatorio());
		impl.getAttr().setScale(map.scale());
		impl.getAttr().setTamanho(map.tamanho());
		impl.getAttr().setTrim(map.trim());
		
		return impl;
	}
	
	protected static <T> List<SimpleAttr> getListaMapper(T instance) throws MapeamentoNaoEncontrado {
		
		List<SimpleAttr> mappers = new LinkedList<>();
		
		for (Field field : instance.getClass().getDeclaredFields()) {
			
			if(field.isAnnotationPresent(MapAtributo.class)) {
				
				Mapper map = field.getAnnotation(MapAtributo.class).value();
				mappers.add(criarMapper(instance, field, map));
			}
			else if(field.isAnnotationPresent(MapBean.class)) {
				
				//Object bean = field.getType().newInstance()
				//invoke(instance, valor, metodo, field);
				//;
				/*try {
					Object bean = field.getType().newInstance();
					Mapper[] map = field.getAnnotation(MapBean.class).value();
					for (Mapper mapper : map) {
						mappers.add(criarMapper(instance, field, map);
					}
				} catch (InstantiationException | IllegalAccessException e) {
					
					throw new MapeamentoNaoEncontrado(atributoClasse(field.getName(), instance.getClass()));
				}*/
			}
		}
		
		return mappers;
	}
	
	private static void addLast(List<TargetRead> paraLer, Object instance) {
		paraLer.add(new TargetRead());
		paraLer.get(paraLer.size() - 1).setInstance(instance);
		paraLer.get(paraLer.size() - 1).setCampos(new LinkedList<String>());
	}
	
	protected static <T> List<TargetRead> getSequenciaLeitura(T instance) {
		
		List<TargetRead> paraLer = new LinkedList<>();
		addLast(paraLer, instance);
		
		List<Field> campos = getAllFields(instance.getClass());
		for (Field field : campos) {
			
			if(field.getName().contains(".")) {
				Object obj = null;//field.getType().newInstance();
				addLast(paraLer, obj);
				
				paraLer.addAll(getSequenciaLeitura(obj)); 
			}
			else {
				paraLer.get(paraLer.size() - 1).getCampos().add(field.getName());	
			}
			
			
		}
		
		return paraLer;
	}
	
	protected static <T> List<Field> getAllFields(Class<T> clazz) {
		
		if(clazz == null) {
			return Collections.emptyList();
		}
		
		List<Field> allFields = new LinkedList<>();
		allFields.addAll(getAllFields(clazz.getSuperclass()));
		allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		
		return allFields;
	}
	
	protected static Field findField(String name, List<Field> fields) {

		for (int i = 0; i < fields.size(); i++) {
			
			if(fields.get(i).getName().equals(name)) {
				return fields.get(i); 
			}
		}
		
		return null;
	}
	
	private static <T> String atributoClasse(String nome, Class<T> tipo) {
		
		return nome + "[" + tipo.getName() + "]";
	}
	
	public static <T> T ler(String entrada, Class<T> tipo, Object... args) throws MapeamentoNaoEncontrado {
		
		/*List<MapperImpl> paraLer = new LinkedList<>();
		
		for (Field field : tipo.getDeclaredFields()) {
			
			if(field.isAnnotationPresent(MapAtributo.class)) {
				MapperImpl impl = new MapperImpl();
				paraLer.add(impl);
				impl.setField(field);
				impl.setAttr(field.getAnnotation(MapAtributo.class).value());
			}
		}
		
		if(paraLer.isEmpty()) {
			
			throw new MapeamentoNaoEncontrado();
		}*/
		
		T instance;
		try {
			Class<?>[] params = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				params[i] = args[i].getClass();
			}
			
			instance = tipo.getDeclaredConstructor(params).newInstance(args);
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			
			throw new MapeamentoNaoEncontrado("Classe " + tipo.getName() + " inválida", e);
		}
		
		int posicao = 0;
		if(tipo.isAnnotationPresent(PosicaoInicio.class)) {
			posicao = tipo.getAnnotation(PosicaoInicio.class).posicao();
		}
		
		//ler(paraLer, instance, posicao, entrada);
		return instance;
	}
	
	private static <T> int ler(List<SimpleAttr> paraLer, T instance, int position, String entrada) throws MapeamentoNaoEncontrado {
		
		/*for (MapperImpl attr : paraLer) {
			
			if(adapters.get(attr.getCampo().getType()) == null) {
			
				position = lerBean(entrada, instance, position, attr);
				
			}
			else {
				position = lerSimples(entrada, instance, position, attr);	
			}
			
		}*/
		
		return position;
	}

	private static <T> int lerBean(String entrada, T instance, int position, SimpleAttr attr) throws MapeamentoNaoEncontrado {

		int tamanhoBean = 0;
		/*try {
			
			List<MapperImpl> paraLer = new LinkedList<>();
			
			Object bean = attr.getCampo().getType().newInstance();
			if(attr.getCampo().getAnnotation(MapAtributo.class).bean().value().length == 0) {
				
				throw new MapeamentoNaoEncontrado("Erro ao ler entrada " + atributoClasse(attr.getCampo().getName(), attr.getCampo().getType()) + ".");
			}
			
			MapBean mapBean = attr.getCampo().getAnnotation(MapAtributo.class).bean();
			
			List<Field> fields = getAllFields(bean.getClass());
			
			for (Mapper subMap : mapBean.value()) {
			
				tamanhoBean += subMap.tamanho();
				MapperImpl impl = new MapperImpl();
				paraLer.add(impl);
				impl.setField(findField(subMap.path(), fields));
				impl.setAttr(subMap);
			}
			
			ler(paraLer, bean, 0, entrada.substring(position, position + tamanhoBean));
			invoke(instance, bean, attr.getNomeMetodo(), attr.getCampo());
		} 
		catch (InstantiationException | IllegalAccessException | SecurityException e) {
			
			throw new MapeamentoNaoEncontrado("Erro ao ler entrada " + atributoClasse(attr.getCampo().getName(), attr.getCampo().getType()) + ".", e);
		}*/
		
		return position + tamanhoBean;
	}

	private static <T> int lerSimples(String entrada, T instance, int position, SimpleAttr attr) throws MapeamentoNaoEncontrado {
		
		/*String in = entrada.substring(position, position + attr.getAttr().tamanho());
		Object valor = adapters.get(attr.getCampo().getType()).ler(in, attr);*/
		
		//invoke(instance, valor, attr.getNomeMetodo(), attr.getCampo());
		return position;//+ attr.getAttr().tamanho();
	}
	
	private static void invoke(Object target, Object valor, String metodo, Field field) throws MapeamentoNaoEncontrado {
		
		try{
			Method m = target.getClass().getMethod(metodo, field.getType());
			m.invoke(target, valor);
		}
		catch (StringIndexOutOfBoundsException e) {
			
			throw new MapeamentoNaoEncontrado("Erro ao ler entrada " + atributoClasse(field.getName(), target.getClass()) + ".", e);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			throw new MapeamentoNaoEncontrado("Metodo " + atributoClasse(field.getName(), target.getClass()) + " inválida");
		}
	}

}
