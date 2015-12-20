package com.stefanini.mav.mensagem;

import java.util.HashMap;
import java.util.Map;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;

public class MensagemFactory {
	
	private static final Map<CodigoMensagem, ContextoMensagem<?>> leitores = new HashMap<>();
	
	static {
		leitores.put(CodigoMensagem.C0100, new ContextoMensagem<PropostaFinanciamento>(CodigoMensagem.C0100, PropostaFinanciamento.class));
		leitores.put(CodigoMensagem.C0110, new ContextoMensagem<RespostaPropostaFinanciamento>(CodigoMensagem.C0110, RespostaPropostaFinanciamento.class));
		leitores.put(CodigoMensagem.C0200, new ContextoMensagem<ConsultaProposta>(CodigoMensagem.C0200, ConsultaProposta.class));
		leitores.put(CodigoMensagem.C0210, new ContextoMensagem<RespostaConsultaProposta>(CodigoMensagem.C0210, RespostaConsultaProposta.class));
		leitores.put(CodigoMensagem.C0450, new ContextoMensagem<SolicitacaoCapturaSimplificada>(CodigoMensagem.C0450, SolicitacaoCapturaSimplificada.class));
		leitores.put(CodigoMensagem.C0460, new ContextoMensagem<RespostaCapturaSimplificada>(CodigoMensagem.C0460, RespostaCapturaSimplificada.class));
		leitores.put(CodigoMensagem.C0670, new ContextoMensagem<GeracaoToken>(CodigoMensagem.C0670, GeracaoToken.class));
		leitores.put(CodigoMensagem.C0680, new ContextoMensagem<GeracaoTokenResposta>(CodigoMensagem.C0680, GeracaoTokenResposta.class));
		leitores.put(CodigoMensagem.C9100, new ContextoMensagem<RespostaErro>(CodigoMensagem.C9100, RespostaErro.class));
		leitores.put(CodigoMensagem.C9200, new ContextoMensagem<RespostaErro>(CodigoMensagem.C9200, RespostaErro.class));
		leitores.put(CodigoMensagem.C9300, new ContextoMensagem<RespostaErro>(CodigoMensagem.C9300, RespostaErro.class));
		leitores.put(CodigoMensagem.C9400, new ContextoMensagem<RespostaErro>(CodigoMensagem.C9400, RespostaErro.class));
		leitores.put(CodigoMensagem.C9450, new ContextoMensagem<RespostaErro>(CodigoMensagem.C9450, RespostaErro.class));
		leitores.put(CodigoMensagem.C9670, new ContextoMensagem<RespostaErro>(CodigoMensagem.C9670, RespostaErro.class));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends MensagemBasica> ContextoMensagem<T> loadContexto(CodigoMensagem codigo) {
		
		return (ContextoMensagem<T>) leitores.get(codigo);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends MensagemBasica> ContextoMensagem<T> parseContexto(String mensagem) throws MensagemNaoEncontradaException {
		
		if(mensagem == null || mensagem.isEmpty()) {
			throw new MensagemNaoEncontradaException();
		}
		
		CodigoMensagem codigo = CodigoMensagem.parse(mensagem.substring(5, 9));
		if(!leitores.containsKey(codigo)) {
			
			throw new MensagemNaoEncontradaException(codigo.toString());
		}
		
		return (ContextoMensagem<T>) leitores.get(codigo);
	}
	
	public static <T extends MensagemBasica> MensagemBasica parse(String mensagem) throws MensagemNaoEncontradaException {
		
		return parseContexto(mensagem).ler(mensagem);
	}
	
	public static <T extends MensagemBasica> MensagemBasica gerarErro(MensagemBasica mensagem, String descricao) throws MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
		//recuperando mensagem
		String in = loadContexto(mensagem.getCabecalho().getCodigo()).escrever(mensagem);
		
		//recuperando cabeçalho
		String cabecalho = in.substring(0, 83);
		
		//montando cabeçalho de erro
		cabecalho = cabecalho.substring(0, 5).concat("9").concat(cabecalho.substring(6, 9)).concat(cabecalho.substring(9, 83));

		//recuperando indicadores
		String indicadores = in.substring(in.length() - 14, in.length());

		//motando mensagem de erro
		if(descricao.length() > 81) {
			descricao = descricao.substring(0, 81);
		}
		else {
			descricao = String.format("%-" + 81 + "s" , descricao);
		}
		
		String erro = cabecalho.concat(descricao).concat(indicadores);
		
		//criando objeto
		return parseContexto(erro).ler(erro);
	}

}
