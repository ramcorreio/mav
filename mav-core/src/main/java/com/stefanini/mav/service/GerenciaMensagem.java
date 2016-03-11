package com.stefanini.mav.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.core.MensagemParceira;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

@Service
@Transactional(value = TxType.REQUIRED)
public class GerenciaMensagem extends BaseManager implements IGerenciaMensagem {

	private static final long serialVersionUID = -1643744403836008193L;
	
	private static Logger _LOGGER = LoggerFactory.getLogger(GerenciaMensagem.class);

	@Override
	public Mensagem salvar(MensagemBasica m) throws MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
	
		if(existe(m)) {
			
			return recuperarMensagem(m);
		}
		
		Mensagem dump = new Mensagem();
		dump.setCodigo(m.getCabecalho().getCodigo());
		dump.setData(Calendar.getInstance().getTime());
		dump.setNumeroTransacao(m.getCabecalho().getNumeroTransacao());
		dump.setNumeroProposta(m.getCabecalho().getNumeroProposta());
		
		dump.setFluxo(m.getCabecalho().getSentidoFluxo());
		dump.setDump(MensagemFactory.loadContexto(m.getCabecalho().getCodigo()).escrever(m));
		
		persist(dump);
		
		return dump;
	}
	
	@Override
	public boolean existe(MensagemBasica m) {
		
		_LOGGER.info("verificando mensagem...");
		_LOGGER.info(m.getCabecalho().getNumeroTransacao().toString());
		_LOGGER.info(m.getCabecalho().getCodigo().toString());
		_LOGGER.info(m.getCabecalho().getNumeroProposta());
		
		Mensagem mdb = recuperarMensagem(m);
		return (mdb == null) ? false : true;
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
	
	@Override
	public Mensagem recuperarMensagem(MensagemBasica m) {
		
		_LOGGER.info("recuperando mensagem...");
		_LOGGER.info(m.getCabecalho().getNumeroTransacao().toString());
		_LOGGER.info(m.getCabecalho().getCodigo().toString());
		_LOGGER.info(m.getCabecalho().getNumeroProposta());
		
		TypedQuery<Mensagem> q = createNamedQuery("Mensagem.recuperar", Mensagem.class);
		q.setParameter("nt", m.getCabecalho().getNumeroTransacao());
		q.setParameter("cd", m.getCabecalho().getCodigo());
		q.setParameter("np", m.getCabecalho().getNumeroProposta());
		try {
			return q.getSingleResult();
		}
		catch(NoResultException e) {
			
			return null;
		}
	}
	
	@Override
	public List<Mensagem> listarTodas() {
		
		TypedQuery<Mensagem> q = createNamedQuery("Mensagem.processadas", Mensagem.class);
		try {
			return q.getResultList();
		}
		catch(NoResultException e) {
			
			return Collections.emptyList();
		}
	}
	
	@Override
	public int contarMensagens() {
		
		Query q = createNamedQuery("Mensagem.contar");
		try {
			return Number.class.cast(q.getSingleResult()).intValue();
		}
		catch(NoResultException e) {
			
			return 0;
		}
	}
	
	@Override
	public int contarProcessadas() {
		
		Query q = createNamedQuery("Mensagem.processadas");
		try {
			return Number.class.cast(q.getSingleResult()).intValue();
		}
		catch(NoResultException e) {
			
			return 0;
		}
	}
	
	@Override
	public String recuperarParceira(MensagemBasica m) {

		_LOGGER.info("recuperando mensagem...");
		_LOGGER.info(m.getCabecalho().getNumeroTransacao().toString());
		_LOGGER.info(m.getCabecalho().getCodigo().toString());
		_LOGGER.info(m.getCabecalho().getNumeroProposta());
		
		TypedQuery<Mensagem> q = createNamedQuery("Mensagem.ultima.proposta", Mensagem.class);
		q.setParameter("np", m.getCabecalho().getNumeroProposta());
		try {
			LinkedList<Mensagem> mensagens = new LinkedList<Mensagem>(q.getResultList());
			LinkedList<MensagemParceira> parceiras = new LinkedList<MensagemParceira>(mensagens.getLast().getParceiras());
			return parceiras.getLast().getChaveParceira();
		}
		catch(NoResultException e) {
			
			return null;
		}
	}
	
}
