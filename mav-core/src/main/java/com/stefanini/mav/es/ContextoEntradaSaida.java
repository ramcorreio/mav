package com.stefanini.mav.es;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.stefanini.mav.util.UtilsDate;

public class ContextoEntradaSaida {
	
	private final static Map<Class<?>, AdaptadorTipo<?>> adapters = Collections.synchronizedMap(new HashMap<Class<?>, AdaptadorTipo<?>>());
	
	static {
		
		adapters.put(Integer.class, new AdaptadorTipo<Integer>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Integer ler(String in, MapAtributo map) {
				
				return Integer.valueOf(in);
			}
		});
		
		adapters.put(String.class, new AdaptadorTipo<String>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public String ler(String in, MapAtributo m) {
				
				if(m.trim()) {
					return in.trim();
				}
				else {
					return in;
				}	
			}
		});
		
		adapters.put(Date.class, new AdaptadorTipo<Date>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Date ler(String in, MapAtributo m) throws MapeamentoNaoEncontrado {
				
				try {
					return UtilsDate.parse(in, UtilsDate.formatadores.get(m.formato()));
				} catch (ParseException e) {
					
					throw new MapeamentoNaoEncontrado(e);
				}
			}
		});
		
		adapters.put(Boolean.class, new AdaptadorTipo<Boolean>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Boolean ler(String in, MapAtributo m) {
				
				return Integer.valueOf(in) == 1;
			}
		});
		
		adapters.put(Double.class, new AdaptadorTipo<Double>() {
			
			@Override
			public void escrever() {
				
			}
			
			@Override
			public Double ler(String in, MapAtributo m) throws MapeamentoNaoEncontrado {
				
				int intval = m.tamanho() - m.scale();
				
				DecimalFormat formmatter = (DecimalFormat) DecimalFormat.getInstance();
				formmatter.setGroupingUsed(false);
				formmatter.setMaximumIntegerDigits(intval);
				formmatter.setMinimumFractionDigits(m.scale());
				formmatter.setMaximumFractionDigits(m.scale());
				
				//montagem de valor com ponto
				String valorComPonto = in.substring(0, intval);
				valorComPonto += Character.toString(formmatter.getDecimalFormatSymbols().getDecimalSeparator());
				valorComPonto += in.substring(intval);
				
				try {
					return Double.valueOf(formmatter.parse(valorComPonto).doubleValue());
				} catch (ParseException e) {
					throw new MapeamentoNaoEncontrado(e);
				}
			}
		});
	}
	
	private static <T> String atributoClasse(String nome, Class<T> tipo) {
		
		return nome + "[" + tipo.getName() + "]";
	}
	
	public static <T> T ler(String entrada, Class<T> tipo, Object... args) throws MapeamentoNaoEncontrado {
		
		List<MapAtributoImpl> paraLer = new LinkedList<>();
		
		for (Field field : tipo.getDeclaredFields()) {
			
			if(field.isAnnotationPresent(MapAtributo.class)) {
				MapAtributoImpl impl = new MapAtributoImpl();
				paraLer.add(impl);
				impl.setField(field);
				impl.setMap(field.getAnnotation(MapAtributo.class));
			}
		}
		
		if(paraLer.isEmpty()) {
			
			throw new MapeamentoNaoEncontrado();
		}
		
		T instance;
		try {
			instance = tipo.newInstance();
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			throw new MapeamentoNaoEncontrado("Classe " + tipo.getName() + " inválida");
		}
		
		ler(paraLer, instance, 0, entrada);
		
		/*int position = 0;
		for (Field field : paraLer) {
			
			String fName = field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1));
			String mName = "set".concat(fName);
			MapAtributo map = field.getAnnotation(MapAtributo.class);
			
			if(adapters.get(field.getType()) == null) {
			
				position = lerBean(entrada, instance, position, map, field, mName);
				
			}
			else {
				position = lerSimples(entrada, instance, position, map, field, mName);	
			}
			
		}*/
		
		return instance;
	}
	
	
	
	private static <T> int ler(List<MapAtributoImpl> paraLer, T instance, int position, String entrada) throws MapeamentoNaoEncontrado {
		
		for (MapAtributoImpl attr : paraLer) {
			
			if(adapters.get(attr.getField().getType()) == null) {
			
				position = lerBean(entrada, instance, position, attr);
				
			}
			else {
				position = lerSimples(entrada, instance, position, attr);	
			}
			
		}
		
		return position;
	}

	private static <T> int lerBean(String entrada, T instance, int position, MapAtributoImpl attr) throws MapeamentoNaoEncontrado {

		int tamanhoBean = 0;
		try {
			
			List<MapAtributoImpl> paraLer = new LinkedList<>();
			
			Object bean = attr.getField().getType().newInstance();
			MapBean mapBean = attr.getField().getAnnotation(MapBean.class);
			
			for (MapAtributo subMap : mapBean.value()) {
			
				System.out.println(bean.getClass());
				System.out.println(bean);
				System.out.println(subMap.nome());
				tamanhoBean += subMap.tamanho();
				MapAtributoImpl impl = new MapAtributoImpl();
				paraLer.add(impl);
				impl.setField(bean.getClass().getDeclaredField(subMap.nome()));
				impl.setMap(subMap);
			}
			
			ler(paraLer, bean, 0, entrada.substring(position, position + tamanhoBean));
			invoke(instance, bean, attr.getNomeMetodo(), attr.getField());
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			throw new MapeamentoNaoEncontrado("Erro ao ler entrada " + atributoClasse(attr.getField().getName(), attr.getField().getType()) + ".", e);
		}
		
		return position + tamanhoBean;
	}

	private static <T> int lerSimples(String entrada, T instance, int position, MapAtributoImpl attr) throws MapeamentoNaoEncontrado {
		
		String in = entrada.substring(position, position + attr.getMap().tamanho());
		Object valor = adapters.get(attr.getField().getType()).ler(in, attr.getMap());
		
		invoke(instance, valor, attr.getNomeMetodo(), attr.getField());
		return position + attr.getMap().tamanho();
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
