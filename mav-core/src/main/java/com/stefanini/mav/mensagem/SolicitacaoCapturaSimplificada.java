package com.stefanini.mav.mensagem;

public class SolicitacaoCapturaSimplificada extends MensagemBasica {
	
	private static final long serialVersionUID = -7402704207923363603L;
	
	private DadosPessoais dadosPessoais;
	
	/*private DadosOperacaoCartao dadosOperacao;
	
	private DadoComplementar complemento;
	
	private Indicador indicadores;*/
	
	public SolicitacaoCapturaSimplificada(Cabecalho cabecalho, DadosPessoais dadosPessoais) {
		super(cabecalho);
		this.dadosPessoais = dadosPessoais;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

}
