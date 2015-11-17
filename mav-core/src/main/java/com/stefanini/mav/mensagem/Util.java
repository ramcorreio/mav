package com.stefanini.mav.mensagem;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Properties;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	private static Logger _LOGGER = LoggerFactory.getLogger(Util.class);

	public Util() {
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

		_LOGGER.info("Lendo: " + caminho);
		FileReader fr;
		try {
			fr = new FileReader(caminho);
		} catch (FileNotFoundException e) {
			URL file = Util.class.getClassLoader().getResource(caminho);
			_LOGGER.info("Lendo: " + file.getPath());
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

}
