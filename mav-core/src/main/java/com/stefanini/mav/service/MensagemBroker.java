package com.stefanini.mav.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.mensagem.Util;
import com.stefanini.mav.service.ServiceLocator.Service;
import com.stefanini.mav.tcp.ConexaoParceira;

public class MensagemBroker {

	private static MensagemBroker broker = new MensagemBroker();
	
	private LinkedList<Parceira> parceiras = new LinkedList<>();
	
	private MensagemBroker() {
	}
	
	public static MensagemBroker getInstance() {
		return broker;
	}
	
	private void carregarParceiras() throws IOException {
		
		//cada parceira terá 3 pro
		Properties props = Util.carregarPropriedades("parceira.properties");
		for (int i = 0; i < props.size(); i++) {
			String nomeArquivo = props.getProperty("parceira." + (i + 1) + ".arquivo");
			Properties parceiraProps = Util.carregarPropriedades(nomeArquivo);
			Parceira p = new Parceira(
				parceiraProps.getProperty("nome"),
				parceiraProps.getProperty("servidor"),
				Integer.parseInt(parceiraProps.getProperty("servidor"))
			);
			
			parceiras.add(p);
		}
		
	}
	
	private void iniciarProcesso(MensagemBasica mensagemBasica) {
		
		for (Parceira parceira : parceiras) {
			
			try {
				parceira.processar(mensagemBasica);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public MensagemBasica enviarParceira(MensagemBasica mensagemBasica) throws MensagemNaoEncontradaException, BrokerException {

		IGerenciaMensagem gerente = ServiceLocator.getInstance().getService(Service.GERENTE_MENSAGEM, IGerenciaMensagem.class);
		gerente.salvar(mensagemBasica);
		
		for (Parceira parceira : parceiras) {
			
			try {
				MensagemBasica retorno = parceira.processar(mensagemBasica);
				gerente.salvar(retorno);
				
				//verificar se mensagem de erro
				if(!retorno.isOk()) {
					
					return retorno;
				}
				
				//TODO: verificar regra de negócio
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return mensagemBasica;
		
		
	}

}
