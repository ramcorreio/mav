package com.stefanini.mav.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.Assert;

import com.stefanini.mav.es.AdaptadorTipo;
import com.stefanini.mav.mensagem.Cabecalho;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;

public class MensagemHelper {
	
	public static final int BUFFER_SIZE = 1024;
	
	private static Integer proximaTransacao = 1;
	
	private MensagemHelper() {
	}
	
	public static String lerMensagem(int tamanho, int codigo, String nome) throws IOException, URISyntaxException {
		
		String lido = lerMensagem(codigo, nome);
		Assert.assertEquals(tamanho, lido.length());
		
		return lido;
	}
	
	public static void verificarTamanho(String input, MensagemBasica mensagem) throws IOException, URISyntaxException {
		
		Assert.assertEquals(mensagem.getCabecalho().getTamanho().intValue(), input.substring(83).length());
	}
	
	public static String lerMensagem(CodigoMensagem tipo, String nome) throws IOException, URISyntaxException {
		
		return lerMensagem(Integer.valueOf(tipo.name().replaceAll("C", "")), nome);
	}
	
	public static String lerMensagemMudaTransacao(CodigoMensagem tipo, String nome) throws IOException, URISyntaxException {
		
		return mudarTransacao(lerMensagem(tipo, nome));
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
	
	public static <T> void verificarCabecalho(Cabecalho expected, Cabecalho cabecalho) {
		
		assertThat(cabecalho.getCodigo(), equalTo(expected.getCodigo()));
		assertThat(cabecalho.getTamanho(), equalTo(expected.getTamanho()));
		assertThat(cabecalho.getNumeroTransacao(), is(greaterThan(0)));
		assertThat(cabecalho.getNumeroTransacao(), equalTo(expected.getNumeroTransacao()));
		assertThat(cabecalho.getNumeroProposta(), equalTo(expected.getNumeroProposta()));
		assertThat(cabecalho.getCodigoUsuario(), equalTo(expected.getCodigoUsuario()));
		assertThat(cabecalho.getCodigoRetorno(), equalTo(expected.getCodigoRetorno()));
		assertThat(cabecalho.getCodigoLojista(), equalTo(expected.getCodigoLojista()));
		assertThat(cabecalho.getVersao(), equalTo(expected.getVersao()));
		assertThat(cabecalho.getCampoLojista(), equalTo(expected.getCampoLojista()));
	}
	
	public static String mudarTransacao(String mensagem, int numeroTransacao) {
		
		String mensagem1 = mensagem.substring(0, 9);
		//Integer t = Integer.parseInt(mensagem.substring(9, 15)) + 1;
		String mensagem2 = mensagem.substring(15);
		
		return mensagem1.concat(AdaptadorTipo.escreverInt(6, numeroTransacao)).concat(mensagem2); 
	}
	
	public static String mudarTransacao(String mensagem) {
		
		return mudarTransacao(mensagem, proximaTransacao++); 
	}
}
