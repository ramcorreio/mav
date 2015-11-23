package com.stefanini.mav.mensagem;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

public class ContextoPropostaFinanciamento extends ContextoMensagem<PropostaFinanciamento> {

	public ContextoPropostaFinanciamento() {
		super(CodigoMensagem.C0100, PropostaFinanciamento.class);
	}

	@Override
	void ler(String input, PropostaFinanciamento mensagem) throws MensagemNaoEncontradaException {

		mensagem.getCabecalho().setSentidoFluxo(Fluxo.ENTRADA);
		
		//indicadores
		//2712 a 2712	Identificador do canal	1	A	Identifica que a proposta é de procedência do TRS
		//2713 a 2722	Versão do Canal	10	A	Uso exclusivo da Losango
		//2723 a 2723	Política	1	A	Uso exclusivo da Losango
		//2724 a 2725	Ambiente	2	A	Uso exclusivo da Losango

		mensagem.setIndicadores(new Indicador());
		mensagem.getIndicadores().setIdentificadorCanal(lerStringCheia(input, 2711, 1));
		mensagem.getIndicadores().setVersaoCanal(lerStringCheia(input, 2712, 10));
		mensagem.getIndicadores().setPolitica(lerStringCheia(input, 2722, 1));
		mensagem.getIndicadores().setAmbiente(lerStringCheia(input, 2723, 2));
		
	}

	@Override
	void escrever(StringBuilder b, PropostaFinanciamento mensagem) throws MensagemNaoEncontradaException {

	}
}
