package com.stefanini.mav.service;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.tcp.ConexaoParceira;

public class Parceira {
	
	private String id;
	
	private String nome;
	
	private ConexaoParceira conexao;
	
	public Parceira(String id, String nome, ConexaoParceira conexao) {
		
		this.id = id;
		this.nome = nome;
		this.conexao = conexao;	
	}
	
	public Parceira(String id, String nome, String servidor, int porta) {
		
		this(id, nome, new ConexaoParceira(servidor, porta));	
	}
	
	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPorta() {
		return conexao.getPorta();
	}
	
	public String getServidor() {
		return conexao.getServidor();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!Parceira.class.isInstance(obj)) {
			
			return false;
		}
		
		Parceira outro = Parceira.class.cast(obj);
		return id.equals(outro.id);
	}
	
	public MensagemBasica processar(MensagemBasica entrada) {
		
		conexao.conectar();
		conexao.enviar(entrada);
		MensagemBasica retorno = conexao.receber();
		conexao.fechar();
		return retorno;
	}
}
