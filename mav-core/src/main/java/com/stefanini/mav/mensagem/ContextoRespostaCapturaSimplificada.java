package com.stefanini.mav.mensagem;

import java.text.ParseException;

import com.stefanini.mav.util.UtilDate;

public class ContextoRespostaCapturaSimplificada extends ContextoMensagem<RespostaCapturaSimplificada> {

	/**
	 * 0351 a 0353	codModalidade	3	A		Campos exclusivos para operação de Cartão
	 * 0354 a 0356	codOrg	3	A		Campos exclusivos para operação de Cartão
	 * 0357 a 0359	codLogo	3	A		Campos exclusivos para operação de Cartão
	 * 0360 a 0362	codCampanha	3	A		Campos exclusivos para operação de Cartão
	 * 
	 * 0363 a 0932	Filler	570	A
	 * 
	 * @param input
	 * @return
	 */
	private DadoOperacaoCartao montarOperacaoCartao(String input) {

		DadoOperacaoCartao operacaoCartao = new DadoOperacaoCartao();
		operacaoCartao.setCodigoModalidade(input.substring(350, 353).trim());
		operacaoCartao.setCodigoOrg(input.substring(353, 356).trim());
		operacaoCartao.setCodigoLogo(input.substring(356, 359).trim());
		operacaoCartao.setCodigoCampanha(input.substring(359, 362).trim());
		
		operacaoCartao.setFiller(input.substring(362, 932).trim());
		return operacaoCartao;
	}

	private DadoCliente montarDadoCliente(String input) throws ParseException {

		DadoCliente dadosPessoais = new DadoCliente();
		dadosPessoais.setCpf(input.substring(269, 280));
		dadosPessoais.setDataNascimento(UtilDate.parse(input.substring(280, 288)));
		dadosPessoais.setComplemento(new DadoComplementar());
		//0289 a 0289	ClienteEmancipado	1	A
		dadosPessoais.getComplemento().setClienteEmancipado(Boolean.valueOf(input.substring(288, 289)));
		
		//0290 a 0291	CodProduto	2	A
		dadosPessoais.getComplemento().setCodigoProduto(input.substring(289, 291));
		
		//0292 a 0292	CobraTac	1	A
		dadosPessoais.setCobraTac(Boolean.valueOf(input.substring(291, 291)));
		
		//0293 a 0293	Elegibilidade Seguro	1	A
		dadosPessoais.setElegibilidadeSSeguro(Boolean.valueOf(input.substring(292, 293)));
		
		//0294 a 0301	Codigo Produto Losango	8	A
		dadosPessoais.setCodigoProdutoLosango(input.substring(293, 301));
		
		//0302 a 0303	Qtd Numero Sorte	2	N
		dadosPessoais.setQtdNumeroSorte(Integer.valueOf(input.substring(301, 303)));

		//0304 a 0350	Filler	47	A
		dadosPessoais.setFiller(input.substring(303, 350).trim());
		return dadosPessoais;
	}

	@Override
	public RespostaCapturaSimplificada montar(String input, Cabecalho cabecalho) throws MensagemNaoEncontradaException {

		RespostaCapturaSimplificada m = new RespostaCapturaSimplificada(cabecalho);

		// DADOS DA CONSULTA
		// 0084 a 0166 Filler 83 A Filler
		m.setFiller(input.substring(83, 166).trim());

		// 0167 a 0171 mensagemAutorizador 5 A Parecer do autorizador de crédito
		// (Política): Parecer / Msg Score / Motivo Aprov/Neg
		m.setMensagemAutorizador(input.substring(166, 171).trim());

		// 0172 a 0179 data 8 N Data do Sistema
		// 0180 a 0185 hora 6 N Hora do Sistema
		try {
			m.setData(UtilDate.parseDateHora(input.substring(171, 185)));
		} catch (ParseException e) {
			throw new MensagemNaoEncontradaException(e);
		}

		// 0186 a 0187 codigoStatusProposta 2 A Código do Status da Proposta
		// (02, 03 ou 04)
		// 02 = A0062 = Elegível (bola verde)
		// 03 = A0063 = Segue Fluxo, com Pendencia (bola amarela)
		// 04 = A0064 = Não Elegível (bola vermelha)
		m.setCodigoStatusProposta(input.substring(185, 187).trim());

		// 0188 a 0267 parecer 80 A
		m.setParecer(input.substring(187, 267).trim());

		// 0268 a 0269 produto 2 A
		m.setProduto(input.substring(267, 269).trim());

		// dados pessoais
		try {
			m.setDadosPessoais(montarDadoCliente(input));
		} catch (ParseException e) {

			throw new MensagemNaoEncontradaException(e);
		}

		// dados oepração cartão
		m.setDadosOperacaoCartao(montarOperacaoCartao(input));

		//indicadores
		m.setIndicadores(new Indicador());
		m.getIndicadores().setIdentificadorCanal(input.substring(932, 933).trim());
		m.getIndicadores().setVersaoCanal(input.substring(933, 943).trim());
		m.getIndicadores().setPolitica(input.substring(934, 944).trim());
		m.getIndicadores().setAmbiente(input.substring(944, 946).trim());

		return m;

	}

}
