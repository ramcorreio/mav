package com.stefanini.mav.service;

import java.io.IOException;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.tcp.ConexaoParceira;

public class Parceira {
	
	private String nome;
	
	private String servidor;
	
	private int porta;
	
	protected Parceira(String nome, String servidor, int porta) {
		
		this.nome = nome;
		this.servidor = servidor;
		this.porta = porta;	
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPorta() {
		return porta;
	}
	
	public String getServidor() {
		return servidor;
	}
	
	public MensagemBasica processar(MensagemBasica entrada) throws IOException {
		
		ConexaoParceira conexao = new ConexaoParceira(servidor, porta);
		conexao.conectar();
		conexao.envar(entrada);
		return conexao.receber();
	}
}
