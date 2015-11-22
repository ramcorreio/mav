package com.stefanini.mav.service;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.mensagem.RespostaErro;
import com.stefanini.mav.service.ServiceLocator.Service;

public class MensagemBroker {
	
	private static Logger _LOGGER = LoggerFactory.getLogger(MensagemBroker.class);
	
	public enum MensagemErroBroker {
		
		MSG_ERRO_RETORNO("Erro ao processar retorno . servidor: %s, porta: %s"),
		MSG_ERRO_CONEXAO("Erro de conexão com a parceira. servidor: %s, porta: %s"),
		MSG_ERRO_AUSENCIA_PARCEIRA("Não há conexões disponíveis com as parceiras.");
		
		private String texto;
		
		private MensagemErroBroker(String texto) {
			
			this.texto = texto;
		}
		
		public String getTexto() {
			return texto;
		}
		
		public MensagemBasica wrap(MensagemBasica m) throws MensagemNaoEncontradaException {
			
			return wrap(m, null, new Object[0]);
		}
		
		public MensagemBasica wrap(MensagemBasica m, Throwable t, Parceira p) throws MensagemNaoEncontradaException {
			
			return wrap(m, t, p.getServidor(), p.getPorta());
		}
		
		private MensagemBasica wrap(MensagemBasica m, Throwable t, Object... args) throws MensagemNaoEncontradaException {
			
			String mensagemErro = String.format(texto, args);
			if(t != null) {
				_LOGGER.error(mensagemErro, t);	
			}
			return MensagemFactory.gerarErro(m, mensagemErro);
		}
	}


	private static final MensagemBroker broker = new MensagemBroker();
	
	private LinkedList<Parceira> parceiras = new LinkedList<>();
	
	private MensagemBroker() {
	}
	
	public static MensagemBroker getInstance() {
		return broker;
	}
	
	public LinkedList<Parceira> getParceiras() {
		return parceiras;
	}
	
	public void setParceiras(LinkedList<Parceira> parceiras) {
		this.parceiras = parceiras;
	}
	
	/*private void carregarParceiras() throws IOException {
		
		//cada parceira terá 3 pro
		Properties props = Utils.carregarPropriedades("parceira.properties");
		for (int i = 0; i < props.size(); i++) {
			String nomeArquivo = props.getProperty("parceira." + (i + 1) + ".arquivo");
			Properties parceiraProps = Utils.carregarPropriedades(nomeArquivo);
			Parceira p = new Parceira(
				parceiraProps.getProperty("nome"),
				parceiraProps.getProperty("servidor"),
				Integer.parseInt(parceiraProps.getProperty("servidor"))
			);
			
			parceiras.add(p);
		}
		
	}*/
	
	/*private void iniciarProcesso(MensagemBasica mensagemBasica) {
		
		for (Parceira parceira : parceiras) {
			
			try {
				parceira.processar(mensagemBasica);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}*/
	
	public MensagemBasica enviarParceira(MensagemBasica mensagemBasica) throws MensagemNaoEncontradaException, BrokerException {

		IGerenciaMensagem gerente = ServiceLocator.getInstance().getService(Service.GERENTE_MENSAGEM, IGerenciaMensagem.class);
		gerente.salvar(mensagemBasica);
		
		for (Parceira parceira : parceiras) {
			
			MensagemBasica retorno = null;
			try {
				_LOGGER.info("Processando conexão com " + parceira.getNome());
				retorno = parceira.processar(mensagemBasica);
				
			} catch (Throwable t) {
				
				return MensagemErroBroker.MSG_ERRO_CONEXAO.wrap(mensagemBasica, t, parceira);
			}
			
			try {
				gerente.salvar(retorno);
				
				//verificar se mensagem de erro
				if(!retorno.isOk()) {
					
					return retorno;
				}
				
				//TODO: verificar regra de negócio
				return retorno;
				
			} catch (Throwable t) {
				
				RespostaErro erro =  (RespostaErro) MensagemErroBroker.MSG_ERRO_RETORNO.wrap(mensagemBasica, t, parceira);
				return erro;
			}
		}
		
		return MensagemErroBroker.MSG_ERRO_AUSENCIA_PARCEIRA.wrap(mensagemBasica);
	}

}
