package com.stefanini.mav.mensagem;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

public class ContextoRespostaErro extends ContextoMensagem<RespostaErro> {
	
	public ContextoRespostaErro(CodigoMensagem tipo) {
		super(tipo, RespostaErro.class);
	}
	
	@Override
	public void ler(String input, RespostaErro mensagem) throws MensagemNaoEncontradaException {
		
		//configuração mensagem de entrada
		mensagem.getCabecalho().setSentidoFluxo(Fluxo.SAIDA);
		
		mensagem.setDescricao(input.substring(83, 164));
		
		//indicadores
		//0165 a 0165	Identificação do Canal	1	A	Dados Losango
		//0166 a 0175	Versão do Canal	10	A	Uso exclusivo da Losango
		//0176 a 0176	Política	1	A	Uso exclusivo da Losango
		//0177 a 0178	Ambiente	2	A	Uso exclusivo da Losango

		mensagem.setIndicadores(new Indicador());
		mensagem.getIndicadores().setIdentificadorCanal(input.substring(164, 165).trim());
		mensagem.getIndicadores().setVersaoCanal(input.substring(164, 175).trim());
		mensagem.getIndicadores().setPolitica(input.substring(175, 176).trim());
		mensagem.getIndicadores().setAmbiente(input.substring(176, 178).trim());
	}
	
	@Override
	public void escrever(StringBuilder b, RespostaErro mensagem) throws MensagemNaoEncontradaException {

		escreverString(b, 81, mensagem.getDescricao());
		
		//indicadores
		//0165 a 0165	Identificação do Canal	1	A	Dados Losango
		//0166 a 0175	Versão do Canal	10	A	Uso exclusivo da Losango
		//0176 a 0176	Política	1	A	Uso exclusivo da Losango
		//0177 a 0178	Ambiente	2	A	Uso exclusivo da Losango
		escreverString(b, 1, mensagem.getIndicadores().getIdentificadorCanal());
		escreverString(b, 10, mensagem.getIndicadores().getVersaoCanal());
		escreverString(b, 1, mensagem.getIndicadores().getPolitica());
		escreverString(b, 2, mensagem.getIndicadores().getAmbiente());		
	}
}
