package com.stefanini.mav.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.Assert;

public class MensagemHelper {
	
	public static final int BUFFER_SIZE = 1024;
	
	private static Integer numTransacao = new Integer(1);
	
	private MensagemHelper() {
	}
	
	public static String getNumTransacao() {
		
		synchronized (numTransacao) {
			
			return String.format("%6d", numTransacao++);
		}
	}
	
	public static String lerMensagem(int tamanho, int codigo, String nome) throws IOException, URISyntaxException {
		
		String lido = lerMensagem(codigo, nome);
		Assert.assertEquals(tamanho, lido.length());
		
		return lido;
	}
	
	public static String lerMensagem(int codigo, String nome) throws IOException, URISyntaxException {
		
		URL path = MensagemHelper.class.getClassLoader().getResource(nome + "-" + codigo + ".input");
		byte[] readed = Files.readAllBytes(Paths.get(path.toURI()));
		
		return new String(readed);
	}
	
	public static String readBuffer(IoBuffer buffer) throws IOException, URISyntaxException {
		
		StringBuilder b = new StringBuilder();
		b.append(new String(buffer.array()), 0, buffer.remaining());
		return b.toString();
	}
}
