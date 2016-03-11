package com.stefanini.mav.service;

import java.io.Serializable;
import java.util.List;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.core.MensagemParceira;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public interface IGerenciaMensagem extends Serializable {

	public Mensagem salvar(MensagemBasica m) throws MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado;

	MensagemParceira gravarMensagemParceira(Mensagem m, Parceira parceira) throws MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado;
	
	public boolean existe(MensagemBasica m);
	
	public Mensagem recuperarMensagem(MensagemBasica m);
	
	public List<Mensagem> listarTodas();
	
	public int contarProcessadas();
	
	public int contarMensagens();

}
