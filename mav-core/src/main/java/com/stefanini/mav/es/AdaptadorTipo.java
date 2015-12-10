package com.stefanini.mav.es;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.FormaPagamento;
import com.stefanini.mav.mensagem.StatusProposta;
import com.stefanini.mav.util.UtilsDate;

	
public abstract class AdaptadorTipo<T> {
	
	public static final Map<Class<?>, AdaptadorTipo<?>> adapters = Collections.synchronizedMap(new HashMap<Class<?>, AdaptadorTipo<?>>());
	
	static {
		
		adapters.put(Integer.class, new AdaptadorTipo<Integer>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {
				
				return escreverInt(map.getTamanho(), Integer.class.cast(in));
			}
			
			@Override
			public Integer ler(String in, SimpleMapper map) {

				return in.trim().isEmpty() ? null : Integer.valueOf(in.trim());
			}
		});
		
		adapters.put(String.class, new AdaptadorTipo<String>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {

				return String.format("%-" + map.getTamanho() + "s" , in.toString());
			}
			
			@Override
			public String ler(String in, SimpleMapper m) {
				
				if(m.isTrim()) {
					return in.trim();
				}
				else {
					return in;
				}
			}
		});
		
		adapters.put(Date.class, new AdaptadorTipo<Date>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {

				return UtilsDate.format(Date.class.cast(in), UtilsDate.FORMATADORES.get(map.getFormato()).getFormatador());
			}
			
			@Override
			public Date ler(String in, SimpleMapper m) throws MapeamentoNaoEncontrado {
				
				if(in.trim().isEmpty()) {
					return null;
				}
				else if(Long.parseLong(in.trim()) == 0) {
					return null;
				}
				else {
					try {
						
						return UtilsDate.parse(in, UtilsDate.FORMATADORES.get(m.getFormato()));
					} catch (ParseException e) {
						
						throw new MapeamentoNaoEncontrado(m.getPath(), e);
					}
				}
			}
		});
		
		adapters.put(Boolean.class, new AdaptadorTipo<Boolean>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {
				
				return escreverInt(map.getTamanho(), Boolean.class.cast(in) ? 1 : 0);
			}
			
			@Override
			public Boolean ler(String in, SimpleMapper m) {
				
				try {
					return in.trim().isEmpty() ? false : (in.trim().equals(m.getComparador()) ? true : false);
				}
				catch(NullPointerException e) {
					return false;
				}
			}
		});
		
		adapters.put(Double.class, new AdaptadorTipo<Double>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {

				Double valor = Double.class.cast(in);
				for (int i = 0; i < map.getScale(); i++) {
					valor*=10;
				}
				
				return escreverInt(map.getTamanho(), valor.intValue());
			}
			
			@Override
			public Double ler(String in, SimpleMapper m) throws MapeamentoNaoEncontrado {
				
				String cleanVal = in.trim();
				if(cleanVal.isEmpty()) {
					return null;
				}
				
				int scale = m.getScale();
				int intval = m.getTamanho() - scale;
				
				DecimalFormat formmatter = (DecimalFormat) DecimalFormat.getInstance();
				formmatter.setGroupingUsed(false);
				formmatter.setMaximumIntegerDigits(intval);
				formmatter.setMinimumFractionDigits(scale);
				formmatter.setMaximumFractionDigits(scale);
				
				//montagem de valor com ponto
				String valorComPonto = cleanVal.substring(0, intval);
				valorComPonto += Character.toString(formmatter.getDecimalFormatSymbols().getDecimalSeparator());
				valorComPonto += in.substring(intval);
				
				try {
					return Double.valueOf(formmatter.parse(valorComPonto).doubleValue());
				} catch (ParseException e) {
					throw new MapeamentoNaoEncontrado(e);
				}
			}
		});
		
		adapters.put(CodigoMensagem.class, new AdaptadorTipo<CodigoMensagem>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {

				return escreverInt(map.getTamanho(), CodigoMensagem.class.cast(in).toInt());
			}
			
			@Override
			public CodigoMensagem ler(String in, SimpleMapper map) throws MapeamentoNaoEncontrado {
			
				return CodigoMensagem.parse(in);
			}
		});
		
		adapters.put(StatusProposta.class, new AdaptadorTipo<StatusProposta>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {
				
				return escreverInt(map.getTamanho(), StatusProposta.class.cast(in).toInt());
			}
			
			@Override
			public StatusProposta ler(String in, SimpleMapper map) throws MapeamentoNaoEncontrado {
			
				return StatusProposta.parse(in);
			}
		});

		adapters.put(FormaPagamento.class, new AdaptadorTipo<FormaPagamento>() {
			
			@Override
			public String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado {
				
				return escreverInt(map.getTamanho(), FormaPagamento.class.cast(in).getCodigo());
			}
			
			@Override
			public FormaPagamento ler(String in, SimpleMapper map) throws MapeamentoNaoEncontrado {
			
				return FormaPagamento.parse(Integer.parseInt(in));
			}
		});
	}
	
	private Class<?> tipoEnum;
	
	public Class<?> getTipoEnum() {
		return tipoEnum;
	}
	
	public void setTipoEnum(Class<?> tipoEnum) {
		this.tipoEnum = tipoEnum;
	}
	
	protected static String escreverInt(int tamanho, Integer input) {
		return String.format("%0" + tamanho + "d", input);
	}
	
	/**
	 * método responsável por gerar uma string com valor informado e completar até o tamanho com espaços em branco.
	 */
	public static String escreverString(int tamanho, String input) {
		return String.format("%-" + tamanho + "s" , input);
	}
	
	/**
	 * Função que fará a leitura do trecho definido da string sem remoção dos espaços em branco no início e fim
	 * 
	 * @param input String para leitura
	 * @param inicio posição onde inicia a leitura
	 * @param tamanho tamanho a ser lido
	 * @return Intervalo lido da string de entrada
	 */
	public static String lerStringCheia(String input, int inicio, int tamanho) {
		return input.substring(inicio, inicio + tamanho);
	}
	
	public abstract String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado;
	
	public abstract T ler(String in, SimpleMapper map)  throws MapeamentoNaoEncontrado;

}
