package com.stefanini.mav.service;


import java.util.Calendar;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

@Service
@Transactional(value = TxType.REQUIRED)
public class GerenciaMensagem extends BaseManager implements IGerenciaMensagem {

	private static final long serialVersionUID = -1643744403836008193L;

	@Override
	public Mensagem salvar(MensagemBasica m) throws MensagemNaoEncontradaException, BrokerException {
	
		Mensagem dump = new Mensagem();
		dump.setCodigo(m.getCabecalho().getCodigo());
		dump.setData(Calendar.getInstance().getTime());
		dump.setNumeroTransacao(m.getCabecalho().getNumeroTransacao());
		dump.setDump(MensagemFactory.loadContexto(m.getCabecalho().getCodigo()).escrever(m));
		
		persist(dump);
		
		return dump;
		
	}
}
