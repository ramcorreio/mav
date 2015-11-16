package com.stefanini.mav.service;

import java.io.Serializable;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public interface IGerenciaMensagem extends Serializable {

	public Mensagem salvar(MensagemBasica m) throws MensagemNaoEncontradaException, BrokerException;

}
