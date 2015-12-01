package com.stefanini.mav.mensagem;

import java.text.ParseException;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.util.UtilsDate;

public class ContextoSolicitacaoCapturaSimplificada extends ContextoMensagem<SolicitacaoCapturaSimplificada> {
	
	public ContextoSolicitacaoCapturaSimplificada() {
		super(CodigoMensagem.C0450, SolicitacaoCapturaSimplificada.class);
	}

	/**
	 * 0084 a 0094	CPF	11	N	CPF do Cliente		X
	 * 0095 a 0102	Data de Nascimento	8	N	Data de nascimento do cliente (ddmmaaaa). 		X
	 * 0103 a 0142	Filler	40	A	Filler
	 * 
	 * @throws ParseException
	 * 
	 * @param input
	 * @return 
	 */
	/*private void lerDadoPessoais(String input, SolicitacaoCapturaSimplificada m) throws ParseException {
		
		m.setDadosPessoais(new DadoPessoalBasico());
		m.getDadosPessoais().setCpf(lerString(input, 83, 11));
		m.getDadosPessoais().setDataNascimento(UtilsDate.parse(input.substring(94, 102)));
		m.getDadosPessoais().setFiller(input.substring(102, 142).trim());
	}*/
	
	private void escreverDadoPessoais(StringBuilder b, SolicitacaoCapturaSimplificada m) {
		
		escreverString(b, 11, m.getDadosPessoais().getCpf());
		escreverString(b, 8, UtilsDate.format(m.getDadosPessoais().getDataNascimento()));
		escreverString(b, 40, m.getDadosPessoais().getFiller());
	}
	
	/**
	 * 
	 * 0143 a 0145	codigoOrg	3	A	Códifo do Org		X, se for uma operação de Cartão
	 * 0146 a 0148	codigoLogo	3	A	Código do LOGO		X, se for uma operação de Cartão
	 * 0149 a 0151	codigoCampanha	3	A	Código da Campanha		X, se for uma operação de Cartão
	 * 0152 a 0154	codigoModalidade	3	A	Código da Modalidade	
	 * 001 - Modalidade Proposta Eletronica
	 * 002 - Modalidade Primeira Compra"	X, se for uma operação de Cartão
	 * 0155 a 0182	Filler	28	A	Filler
	 * 
	 * @param input
	 */
	/*private void lerOperacaoCartao(String input, SolicitacaoCapturaSimplificada mensagem) {
		
		mensagem.setDadosOperacaoCartao(new DadoOperacaoCartao());
		mensagem.getDadosOperacaoCartao().setCodigoOrg(input.substring(142, 145).trim());
		mensagem.getDadosOperacaoCartao().setCodigoLogo(input.substring(145, 148).trim());
		mensagem.getDadosOperacaoCartao().setCodigoCampanha(input.substring(148, 151).trim());
		mensagem.getDadosOperacaoCartao().setCodigoModalidade(input.substring(151, 154).trim());
		mensagem.getDadosOperacaoCartao().setFiller(input.substring(154, 182).trim());
	}*/
	
	private void escreverOperacaoCartao(StringBuilder b, SolicitacaoCapturaSimplificada mensagem) {
		
		escreverString(b, 3, mensagem.getDadosOperacaoCartao().getCodigoOrg());
		escreverString(b, 3, mensagem.getDadosOperacaoCartao().getCodigoLogo());
		escreverString(b, 3, mensagem.getDadosOperacaoCartao().getCodigoCampanha());
		escreverString(b, 3, mensagem.getDadosOperacaoCartao().getCodigoModalidade());
		escreverString(b, 28, mensagem.getDadosOperacaoCartao().getFiller());
	}
	
	@Override
	public void ler(String input, SolicitacaoCapturaSimplificada mensagem) throws MensagemNaoEncontradaException {
		
		//configuração mensagem de entrada
		mensagem.getCabecalho().setSentidoFluxo(Fluxo.ENTRADA);
		
		/*
		//dados pessoais
		try {
			lerDadoPessoais(input, mensagem);
		} catch (ParseException e) {
			
			throw new MensagemNaoEncontradaException(e);
		}

		//dados oepração cartão
		lerOperacaoCartao(input, mensagem);
		
		//dados complementares
		//0183 a 0183	Flag Cliente Emancipado	1	A	Cliente Emancipado	"0"	X
		//0184 a 0185	Produto	2	A	Código do Produto	Ver tabela de dominio TOPxProduto	X
		mensagem.setComplemento(new DadoComplementar());
		mensagem.getComplemento().setEmancipado(lerBoolean(input, 182));
		mensagem.getComplemento().setCodigoProduto(input.substring(183, 185).trim());
		
		//indicadores
		//0186 a 0186	Identificação do Canal	1	A	Dados Losango	T	X
		//0187 a 0196	Versão do Canal	10	A	Uso exclusivo da Losango		
		//0197 a 0197	Política	1	A	Uso exclusivo da Losango		
		//0198 a 0199	Ambiente	2	A	Uso exclusivo da Losango
		mensagem.setIndicadores(new Indicador());
		mensagem.getIndicadores().setIdentificadorCanal(input.substring(185, 186).trim());
		mensagem.getIndicadores().setVersaoCanal(input.substring(187, 196).trim());
		mensagem.getIndicadores().setPolitica(input.substring(196, 197).trim());
		mensagem.getIndicadores().setAmbiente(input.substring(197, 199).trim());*/
	}
	
	@Override
	public void escrever(StringBuilder b, SolicitacaoCapturaSimplificada mensagem) throws MensagemNaoEncontradaException {

		//dados pessoais
		escreverDadoPessoais(b, mensagem);

		//dados oepração cartão
		escreverOperacaoCartao(b, mensagem);
		
		//dados complementares
		//0183 a 0183	Flag Cliente Emancipado	1	A	Cliente Emancipado	"0"	X
		//0184 a 0185	Produto	2	A	Código do Produto	Ver tabela de dominio TOPxProduto	X
		escreverBoolean(b, 1, mensagem.getComplemento().isEmancipado());
		escreverString(b, 2, mensagem.getComplemento().getCodigoProduto());
		
		//indicadores
		//0186 a 0186	Identificação do Canal	1	A	Dados Losango	T	X
		//0187 a 0196	Versão do Canal	10	A	Uso exclusivo da Losango		
		//0197 a 0197	Política	1	A	Uso exclusivo da Losango		
		//0198 a 0199	Ambiente	2	A	Uso exclusivo da Losango
		escreverString(b, 1, mensagem.getIndicadores().getIdentificadorCanal());
		escreverString(b, 10, mensagem.getIndicadores().getVersaoCanal());
		escreverString(b, 1, mensagem.getIndicadores().getPolitica());
		escreverString(b, 2, mensagem.getIndicadores().getAmbiente());		
	}
}
