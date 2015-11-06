package com.stefanini.mav.mensagem;

import java.text.ParseException;

import com.stefanini.mav.util.UtilDate;

public class ContextoSolicitacaoCapturaSimplificada extends ContextoMensagem<SolicitacaoCapturaSimplificada> {
	
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
	private DadoPessoal montarDadoPessoais(String input) throws ParseException {
		
		DadoPessoal dadosPessoais = new DadoPessoal();
		dadosPessoais.setCpf(input.substring(83, 94));
		dadosPessoais.setDataNascimento(UtilDate.parse(input.substring(94, 102)));
		dadosPessoais.setFiller(input.substring(102, 142).trim());
		return dadosPessoais;
	}
	
	/**
	 * 
	 * 0143 a 0145	codigoOrg	3	A	Códifo do Org		X, se for uma operação de Cartão
	 * 0146 a 0148	codigoLogo	3	A	Código do LOGO		X, se for uma operação de Cartão
	 * 0149 a 0151	codigoCampanha	3	A	Código da Campanha		X, se for uma operação de Cartão
	 * 0152 a 0154	codigoModalidade	3	A	Código da Modalidade	"001 - Modalidade Proposta Eletronica
	 * 002 - Modalidade Primeira Compra"	X, se for uma operação de Cartão
	 * 0155 a 0182	Filler	28	A	Filler
	 * 
	 * @param input
	 * @return
	 * @throws ParseException
	 */
	private DadoOperacaoCartao montarOperacaoCartao(String input) {
		
		DadoOperacaoCartao operacaoCartao = new DadoOperacaoCartao();
		operacaoCartao.setCodigoOrg(input.substring(142, 145).trim());
		operacaoCartao.setCodigoLogo(input.substring(145, 148).trim());
		operacaoCartao.setCodigoCampanha(input.substring(148, 151).trim());
		operacaoCartao.setCodigoModalidade(input.substring(151, 154).trim());
		operacaoCartao.setFiller(input.substring(154, 182).trim());
		return operacaoCartao;
	}
	
	@Override
	public SolicitacaoCapturaSimplificada montar(String input, Cabecalho cabecalho) throws MensagemNaoEncontradaException {
		
		SolicitacaoCapturaSimplificada m = new SolicitacaoCapturaSimplificada(cabecalho);
		
		//dados pessoais
		try {
			m.setDadosPessoais(montarDadoPessoais(input));
		} catch (ParseException e) {
			
			throw new MensagemNaoEncontradaException(e);
		}

		//dados oepração cartão
		m.setDadosOperacaoCartao(montarOperacaoCartao(input));
		
		//dados complementares
		m.setComplemento(new DadoComplementar());
		m.getComplemento().setClienteEmancipado(Boolean.valueOf(input.substring(182, 183)));
		m.getComplemento().setCodigoProduto(input.substring(183, 185).trim());
		
		//indicadores
		m.setIndicadores(new Indicador());
		m.getIndicadores().setIdentificadorCanal(input.substring(185, 186).trim());
		m.getIndicadores().setVersaoCanal(input.substring(187, 196).trim());
		m.getIndicadores().setPolitica(input.substring(196, 197).trim());
		m.getIndicadores().setAmbiente(input.substring(197, 199).trim());
		
		return m;
	}
}
