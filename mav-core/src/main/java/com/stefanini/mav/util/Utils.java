package com.stefanini.mav.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public class Utils {

	private static Logger _LOGGER = LoggerFactory.getLogger(Utils.class);

	public Utils() {
	}

	public static IoBuffer writeBuffer(String input) throws CharacterCodingException {

		IoBuffer buf = IoBuffer.allocate(input.length()).setAutoExpand(true);
		CharsetEncoder encoder = Charset.defaultCharset().newEncoder();
		buf.putString(input, encoder);
		buf.putString(LineDelimiter.UNIX.getValue(), encoder);
		buf.flip();

		return buf;
	}
	
	public static Properties carregarPropriedades(String caminho) throws IOException {
		
		return carregarPropriedades(carrgarArquivo(caminho));
	}
	
	public static Properties carregarPropriedades(CharBuffer buffer) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		sb.append(buffer);
		
		Properties p = new Properties();
		p.load(new ByteArrayInputStream(sb.toString().getBytes()));
		return p;
	}

	public static CharBuffer carrgarArquivo(String caminho) throws IOException {

		_LOGGER.debug("Lendo: " + caminho);
		FileReader fr;
		try {
			fr = new FileReader(caminho);
		} catch (FileNotFoundException e) {
			URL file = Utils.class.getClassLoader().getResource(caminho);
			_LOGGER.debug("Lendo: " + file.getPath());
			fr = new FileReader(file.getPath());
		}

		StringBuilder sb = new StringBuilder();
		CharBuffer cb = CharBuffer.allocate(1024);
		while (fr.read(cb) > 0) {
			cb.flip();
			sb.append(cb);
			cb.clear();
		}
		fr.close();

		CharBuffer b = CharBuffer.allocate(sb.length());
		b.append(sb);
		b.flip();

		return b;
	}
	
	/**
	 * Função para criar hash da string informada  
	 * @param senha
	 * @return
	 * @throws MensagemNaoEncontradaException 
	 */
    public static String md5(String input) throws MensagemNaoEncontradaException {
    	
    	String sen = "";  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            throw new MensagemNaoEncontradaException(e); 
        }  
        BigInteger hash = new BigInteger(1, md.digest(input.getBytes()));  
        sen = hash.toString(16);              
        return sen;  
    }

}
