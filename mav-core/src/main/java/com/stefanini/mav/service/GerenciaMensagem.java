package com.stefanini.mav.service;


import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.core.MensagemParceira;
import com.stefanini.mav.es.AdaptadorTipo;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

@Service
@Transactional(value = TxType.REQUIRED)
public class GerenciaMensagem extends BaseManager implements IGerenciaMensagem {

	private static final long serialVersionUID = -1643744403836008193L;

	@Override
	public Mensagem salvar(MensagemBasica m) throws MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
	
		Mensagem dump = new Mensagem();
		dump.setCodigo(m.getCabecalho().getCodigo());
		dump.setData(Calendar.getInstance().getTime());
		dump.setNumeroTransacao(m.getCabecalho().getNumeroTransacao());
		
		String numeroProposta = m.getCabecalho().getNumeroProposta();
		if(numeroProposta.isEmpty()) {
			numeroProposta = "G" + AdaptadorTipo.escreverInt(6, m.getCabecalho().getNumeroTransacao());
		}
		
		dump.setNumeroProposta(numeroProposta);
		dump.setFluxo(m.getCabecalho().getSentidoFluxo());
		dump.setDump(MensagemFactory.loadContexto(m.getCabecalho().getCodigo()).escrever(m));
		
		persist(dump);
		
		return dump;
	}
	
	@Override
	public MensagemParceira gravarMensagemParceira(Mensagem m, Parceira parceira) throws MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
	
		Mensagem rm = get(m.getClass(), m.getId());
		
		TypedQuery<MensagemParceira> q = createNamedQuery("MensagemParceira.chaveParceira", MensagemParceira.class);
		q.setParameter("chaveParceira", parceira.getId());
		
		MensagemParceira mp;
		try {
			mp = q.getSingleResult();	
		}
		catch(NoResultException e) {
			mp = new MensagemParceira();
			mp.setChaveParceira(parceira.getId());
			mp.setMensagens(new ArrayList<Mensagem>());
		}
		
		mp.getMensagens().add(rm);
		
		if(mp.getId() == null) {
			persist(mp);	
		}
		else {
			update(mp);
		}
		
		return mp;
	}
}
