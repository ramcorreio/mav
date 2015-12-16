package com.stefanini.mav.es;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.util.ClassUtils;

import com.stefanini.mav.mensagem.Cabecalho;

public class ContextoEntradaSaida {
	
	private static class Lido {
		
		private int position;
		
		private Object object;
		
		public Lido(int position, Object object) {
			this.position = position;
			this.object = object;
		}
		
		public int getPosition() {
			return position;
		}
		
		public Object getObject() {
			return object;
		}
	}
	
	private static class AttrImpl<T extends BaseMapper> {
		
		private static final String SET_PREFIX = "set";
		
		private static final String GET_PREFIX = "get";
		
		private static final String IS_PREFIX = "is";
		
		private Field campo;
		
		private T mapper;
		
		private String getCamelCase() throws MapeamentoNaoEncontrado {
			
			try {
				return campo.getName().substring(0, 1).toUpperCase().concat(campo.getName().substring(1));	
			}
			
			catch(StringIndexOutOfBoundsException e) {
				
				throw new MapeamentoNaoEncontrado("::" + mapper.getPath() + "::[" + campo.getDeclaringClass() + "]");
			}
		}
		
		public String getNomeMetodoSet() throws MapeamentoNaoEncontrado {
			
			return SET_PREFIX.concat(getCamelCase());
		}
		
		public String getNomeMetodoGet() throws MapeamentoNaoEncontrado {
			
			String fName = getCamelCase();
			if(Boolean.class.equals(campo.getType())){
				return IS_PREFIX.concat(fName);
			}
			else {
				return GET_PREFIX.concat(fName);	
			}
		}
		
		public Field getCampo() {
			return campo;
		}
		
		public void setCampo(Field campo) {
			this.campo = campo;
		}
		
		public T getMapper() {
			return mapper;
		}
		
		public void setMapper(T mapper) {
			this.mapper = mapper;
		}
		
		@Override
		public String toString() {
			StringBuilder b = new StringBuilder();
			b.append("{");
			b.append("campo: " + campo.getName());
			b.append(", ");
			b.append("mapper: " + mapper);
			b.append("}");
			b.append(campo.getName());
			
			return b.toString();
		}
	}
	
	protected static BeanMapper criarBeanMapper(Class<?> tipo, String nome, String path) {
	
		BeanMapper impl = new BeanMapper();
		impl.setNome(nome);
		impl.setPath(path);
		impl.setTipo(tipo);
		return impl; 
	}
	
	protected static <T extends BaseMapper> ListaMapper<T> criarListaMapper(Class<?> tipo, String nome, String path, int maxSize, T mapper) {
		
		ListaMapper<T> sm = new ListaMapper<T>();
		sm.setNome(nome);
		sm.setPath(path);
		sm.setTipo(tipo);
		sm.setMaxSize(maxSize);
		sm.setMapper(mapper);
		return sm;
	}
	
	protected static SimpleMapper criarSimpleMapper(Class<?> tipo, String nome, String path, int tamanho, boolean obrigatorio, int scale, boolean trim, String formato, String comparador) {
		
		SimpleMapper sm = new SimpleMapper();
		sm.setNome(nome);
		sm.setPath(path);
		sm.setTipo(tipo);
		sm.setTamanho(tamanho);
		sm.setObrigatorio(obrigatorio);
		sm.setScale(scale);
		sm.setTrim(trim);
		sm.setFormato(formato);
		sm.setComparador(comparador);
		
		return sm;
	}
	
	protected static MapAtributo criarMapper(final Class<? extends Annotation> annotationType, final String path, final int tamanho, final boolean obrigatorio, final int scale, final boolean trim, final String formato, final String comparador) {
		
		return new MapAtributo() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return annotationType;
			}
			
			@Override
			public boolean trim() {
				return trim;
			}
			
			@Override
			public int tamanho() {

				return tamanho;
			}
			
			@Override
			public int scale() {

				return scale;
			}
			
			@Override
			public String path() {

				return path;
			}
			
			@Override
			public boolean obrigatorio() {

				return obrigatorio;
			}
			
			@Override
			public String formato() {

				return formato;
			}
			
			@Override
			public String comparador() {
				
				return comparador;
			}
		};
	}
	
	protected static <T> List<BaseMapper> getMappersFilhos(Class<T> clazz) throws MapeamentoNaoEncontrado {
		
		return getMappersFilhos("", clazz);
	}
	
	protected static <T> List<BaseMapper> getMappersFilhos(String parent, Class<T> clazz) throws MapeamentoNaoEncontrado {
		
		List<BaseMapper> mappers = new LinkedList<>();
		Collection<Field> fields = getAllFields(clazz, false).values();
		
		for (Field field : fields) {
			
			if(field.isAnnotationPresent(MapAtributo.class)) {
				
				MapAtributo map = field.getAnnotation(MapAtributo.class);
				mappers.add(criarSimpleMapper0(field.getType(), field.getName(), parent.concat(field.getName()), map));
			}
			else if(field.isAnnotationPresent(MapBean.class)) {
				
				BeanMapper impl = criarBeanMapper(field.getType(), field.getName(), parent.concat(field.getName()));
				impl.getMappers().addAll(getMappersFilhos(parent.concat(field.getName()).concat("."), field.getType()));
				mappers.add(impl);
			}
			else if(field.isAnnotationPresent(MapLista.class)) {
				
				MapLista map = field.getAnnotation(MapLista.class);
				if(map.attr().tamanho() > 0) {
					
					SimpleMapper sm = criarSimpleMapper0(getGenericClass(field), "[]", parent.concat(field.getName()).concat("[]"), map.attr());
					mappers.add(criarListaMapper(field.getType(), field.getName(), parent.concat(field.getName()), map.maxSize(), sm));
					
				}
				else if(map.bean().tamanho() > 0) {
					
					//TODO: verificar mapper bean
					BeanMapper impl = criarBeanMapper(getGenericClass(field), "[]", parent.concat(field.getName()).concat("[]"));
					impl.getMappers().addAll(getMappersFilhos(parent.concat(field.getName()).concat("."), impl.getTipo()));
					mappers.add(criarListaMapper(field.getType(), field.getName(), parent.concat(field.getName()), map.maxSize(), impl));
				}
				else {
					
					throw new MapeamentoNaoEncontrado(atributoClasse(field.getName(), clazz));
				}
			}
		}
		
		return mappers;
	}

	/**
	 * @param <T>
	 * @param field
	 * @return
	 * @throws MapeamentoNaoEncontrado 
	 */
	@SuppressWarnings("unchecked")
	private static <T> Class<T> getGenericClass(Field field) throws MapeamentoNaoEncontrado {
		
		Type type = ParameterizedType.class.cast(field.getGenericType()).getActualTypeArguments()[0];
		try {
			return (Class<T>) Class.forName(type.getTypeName());
		} catch (ClassNotFoundException e) {
			throw new MapeamentoNaoEncontrado(atributoClasse(field.getName(), field.getType()));
		}
	}

	/**
	 * @param parent
	 * @param field
	 * @param map
	 * @return
	 */
	private static SimpleMapper criarSimpleMapper0(Class<?> tipo, String nome, String path, MapAtributo map) {
		
		return criarSimpleMapper(
			tipo,
			nome,
			path, 
			map.tamanho(), 
			map.obrigatorio(), 
			map.scale(), 
			map.trim(), 
			map.formato(),
			map.comparador());
	}
	
	protected static <T> Map<String, Field> getAllFields(Class<T> clazz) {
		
		return getAllFields("", clazz, true);
	}
	
	protected static <T> Map<String, Field> getAllFields(Class<T> clazz, boolean recursively) {
		
		return getAllFields("", clazz, recursively);
	}
	
	private static boolean isBaseType(Class<?> clazz) {
		
		return clazz.isEnum()
				|| clazz.equals(Date.class) 
				|| clazz.equals(String.class) 
				|| ClassUtils.isPrimitiveOrWrapper(clazz)/* 
				|| ClassUtils.isPrimitiveArray(clazz) 
				|| ClassUtils.isPrimitiveWrapperArray(clazz)*/;
		
	}
	
	protected static <T> Map<String, Field> getAllFields(String parent, Class<T> clazz, boolean recursively) {
		
		if(clazz == null) {
			return Collections.emptyMap();
		}
		
		Map<String, Field> allFields = new LinkedHashMap<>();
		allFields.putAll(getAllFields(parent, clazz.getSuperclass(), recursively));
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			allFields.put(parent.concat(field.getName()), field);
			if(!isBaseType(field.getType()) && recursively) {
				
				allFields.putAll(getAllFields(parent.concat(field.getName()).concat("."), field.getType(), recursively));
			}	
		}
		
		return allFields;
	}
	
	protected static <T> Field findField(String path, Class<T> clazz, Map<String, Field> fields) throws MapeamentoNaoEncontrado {

		if(!fields.containsKey(path)) {
			
			throw new MapeamentoNaoEncontrado(atributoClasse(path, clazz));
		}
		
		return fields.get(path);
	}
	
	protected static <T> Field findField(String path, Class<T> clazz) throws MapeamentoNaoEncontrado {

		Map<String, Field> fields = getAllFields(clazz);
		return findField(path, clazz, fields);
	}
	
	private static <T> String atributoClasse(String nome, Class<T> tipo) {
		
		return nome + "[" + tipo.getName() + "]";
	}
	
	public static <T> String escrever(T mensagem) throws MapeamentoNaoEncontrado {
		
		List<BaseMapper> mappers = verificarMappers(mensagem.getClass());
		Map<String, Field> fields = getAllFields(mensagem.getClass());
		List<AttrImpl<BaseMapper>> attrs = montarAttrs(mappers, mensagem, fields);
		
		return escrever(attrs, mensagem);
	}
	
	private static <T> String escrever(List<AttrImpl<BaseMapper>> attrs, T mensagem) throws MapeamentoNaoEncontrado {
		
		StringBuilder b = new StringBuilder();
		
		for (AttrImpl<BaseMapper> attr : attrs) {
			
			if(AdaptadorTipo.adapters.get(attr.getCampo().getType()) == null) {
			
				b.append(escreverBean(attr, mensagem));
			}
			else {
				
				b.append(escreverSimples(attr, mensagem));	
			}
		}
		
		return b.toString();
	}
	
	private static String escreverBean(final AttrImpl<BaseMapper> attr, final Object instance) throws MapeamentoNaoEncontrado {

		Object bean = invokeGet(instance, attr.getNomeMetodoGet());
		Map<String, Field> fieldsBean = getAllFields(bean.getClass());
		
		BeanMapper mapper = BeanMapper.class.cast(attr.getMapper());
		List<AttrImpl<BaseMapper>> attrs = montarAttrs(mapper.getMappers(), bean, fieldsBean);
		return escrever(attrs, bean);
	}


	private static String escreverSimples(final AttrImpl<? extends BaseMapper> attr, final Object instance) throws MapeamentoNaoEncontrado {
		
		Object in = invokeGet(instance, attr.getNomeMetodoGet());
		return AdaptadorTipo.adapters.get(attr.getCampo().getType()).escrever(in, SimpleMapper.class.cast(attr.getMapper()));		
	}
	
	public static <T> T ler(String entrada, Class<T> tipo, Boolean configCheck, Object... args) throws MapeamentoNaoEncontrado {
		
		List<BaseMapper> mappers = verificarMappers(tipo);
		
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
		
		if(configCheck && !tipo.isAnnotationPresent(ConfiguracaoMensagem.class)) {
			
			throw new MapeamentoNaoEncontrado("Configuração não encontrada em " + tipo.getName() + ".");
		}
		
		
		int posicao = 0;
		if(configCheck) {
			ConfiguracaoMensagem config = tipo.getAnnotation(ConfiguracaoMensagem.class);
			
			posicao = config.inicio();
			Cabecalho.class.cast(invokeGet(instance, "getCabecalho")).setSentidoFluxo(config.sentido());
		}
		
		
		Map<String, Field> fields = getAllFields(instance.getClass());
		List<AttrImpl<BaseMapper>> attrs = montarAttrs(mappers, instance, fields);
		ler(attrs, instance, posicao, entrada);
		return instance;
	}

	public static <T> T ler(String entrada, Class<T> tipo, Object... args) throws MapeamentoNaoEncontrado {
	
		return ler(entrada, tipo,  true, args);
	}



	/**
	 * @param tipo
	 * @return
	 * @throws MapeamentoNaoEncontrado
	 */
	private static <T> List<BaseMapper> verificarMappers(Class<T> tipo) throws MapeamentoNaoEncontrado {
		List<BaseMapper> mappers = getMappersFilhos(tipo);
		
		if(mappers.isEmpty()) {
			
			throw new MapeamentoNaoEncontrado(tipo.getName());
		}
		return mappers;
	}

	private static List<AttrImpl<BaseMapper>> montarAttrs(List<BaseMapper> mappers, Object instance, Map<String, Field> fields) throws MapeamentoNaoEncontrado {
		
		List<AttrImpl<BaseMapper>> paraLer = new LinkedList<>();
		for (BaseMapper mapper : mappers) {
			
			AttrImpl<BaseMapper> attr = new AttrImpl<BaseMapper>();
			attr.setMapper(mapper);
			attr.setCampo(findField(mapper.getNome(), instance.getClass(), fields));
			paraLer.add(attr);
		}
		
		return paraLer;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends BaseMapper> int ler(List<AttrImpl<T>> attrs, Object instance, int position, String entrada) throws MapeamentoNaoEncontrado {
		
		for (AttrImpl<T> attr : attrs) {
			
			try {	
				Lido lido;
				if(AdaptadorTipo.adapters.get(attr.getCampo().getType()) == null) {
				
					if(BeanMapper.class.isInstance(attr.getMapper())) {
						lido = lerBean(entrada, position, attr.getCampo().getType(), (BeanMapper) attr.getMapper());	
					}
					else {
						Class<?> genericClass = getGenericClass(attr.getCampo());
						lido = lerLista(entrada, position, genericClass, (ListaMapper<BaseMapper>) attr.getMapper());
					}
				}
				else {
					lido = lerSimples(entrada, position, attr.getCampo().getType(), (SimpleMapper) attr.getMapper());
				}
				
				position = lido.getPosition();
				invokeSet(instance, attr.getNomeMetodoSet(), attr.getCampo(), lido.getObject());
			}
			catch (StringIndexOutOfBoundsException | NumberFormatException | InstantiationException | IllegalAccessException | SecurityException e) {
				
				throw new MapeamentoNaoEncontrado("Erro ao ler entrada " + atributoClasse(attr.getCampo().getName(), attr.getCampo().getDeclaringClass()) + ".", e);
			}
		}
		
		return position;
	}
	
	public static <T> List<T> criarListPorTipo(Class<T> type) {
	    return new LinkedList<T>();
	}
	
	private static boolean isEmpty(String entrada) {
		
		String val = entrada.replaceAll("0", "").trim();
		return val.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	private static <T> Lido lerLista(String entrada, int position, Class<T> clazz, ListaMapper<BaseMapper> attr) throws MapeamentoNaoEncontrado, InstantiationException, IllegalAccessException {
		
		int tamanhoItem = getTamanho(attr.getMapper());
		int tamanhoLista = attr.getMaxSize() * tamanhoItem;		
		
		List<T> newInstance = criarListPorTipo(clazz);
        
        for (int i = 0; i < attr.getMaxSize(); i++) {
		
        	int inicioItem = position + (i * tamanhoItem);
        	int fimItem = inicioItem + tamanhoItem;
        	String item = entrada.substring(inicioItem, fimItem);
        	
        	//essa verificação é necessária evitar criar item para dados em branco na entrada de mensagem
        	if(isEmpty(item)) {
        		break;
    		}
        	
        	Lido lido;
        	if(BeanMapper.class.isInstance(attr.getMapper())) {
        		
        		lido = lerBean(item, 0, clazz, (BeanMapper) attr.getMapper());
        	}
        	else {
        		
        		lido = lerSimples(item, 0, clazz, (SimpleMapper) attr.getMapper());
        	}
        	
        	newInstance.add((T)lido.getObject());
		}		

		return new Lido(position + tamanhoLista, newInstance);

	}

	private static Lido lerBean(String entrada, int position, Class<?> clazz, BeanMapper attr) throws InstantiationException, IllegalAccessException, MapeamentoNaoEncontrado {

		int tamanhoBean = getTamanho(attr);
		Object bean = clazz.newInstance();
		Map<String, Field> fieldsBean = getAllFields(bean.getClass());
		
		List<AttrImpl<BaseMapper>> attrs = montarAttrs(attr.getMappers(), bean, fieldsBean);
		ler(attrs, bean, 0, entrada.substring(position, position + tamanhoBean));
		return new Lido(position + tamanhoBean, bean);
	}

	private static Lido lerSimples(String entrada, int position, Class<?> tipo, SimpleMapper attr) throws MapeamentoNaoEncontrado {
		
		String in = entrada.substring(position, position + attr.getTamanho());
		Object valor = AdaptadorTipo.adapters.get(tipo).ler(in, attr);
		return new Lido(position + attr.getTamanho(), valor);
	}
	
	private static Object invokeGet(Object target, String metodo) throws MapeamentoNaoEncontrado {
		
		try{
			Method m = target.getClass().getMethod(metodo);
			return m.invoke(target);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			throw new MapeamentoNaoEncontrado("Metodo " + atributoClasse(metodo, target.getClass()) + " inválida");
		}
	}
	
	private static void invokeSet(Object target, String metodo, Field field, Object... args) throws MapeamentoNaoEncontrado {
		
		try{
			Method m = target.getClass().getMethod(metodo, field.getType());
			m.invoke(target, args);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			throw new MapeamentoNaoEncontrado("Metodo " + atributoClasse(field.getName(), target.getClass()) + " inválida");
		}
	}

	public static Integer getTamanho(BaseMapper contar) {
	
		int tamanhao = 0;
		if(SimpleMapper.class.isInstance(contar)) {
			
			return SimpleMapper.class.cast(contar).getTamanho();
		}
		else if(ListaMapper.class.isInstance(contar)) {
			
			ListaMapper<?> mapper = ListaMapper.class.cast(contar);
			return mapper.getMaxSize() * getTamanho(mapper.getMapper());
		}
		else {
			
			for (BaseMapper mapper : BeanMapper.class.cast(contar).getMappers()) {
				tamanhao += getTamanho(mapper);
			}
		}
		
		return tamanhao;
	}
}