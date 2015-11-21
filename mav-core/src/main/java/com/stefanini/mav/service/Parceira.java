package com.stefanini.mav.service;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.tcp.ConexaoParceira;

public class Parceira {
	
	private String nome;
	
	private String servidor;
	
	private int porta;
	
	public Parceira(String nome, String servidor, int porta) {
		
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
	
	@Override
	public boolean equals(Object obj) {
		
		if(!Parceira.class.isInstance(obj)) {
			
			return false;
		}
		
		Parceira outro = Parceira.class.cast(obj);
		return servidor.equals(outro.servidor) && porta == outro.porta;
	}
	
	public MensagemBasica processar(MensagemBasica entrada) {
		
		ConexaoParceira conexao = new ConexaoParceira(servidor, porta);
		conexao.conectar();
		conexao.envar(entrada);
		return conexao.receber();
	}
}
