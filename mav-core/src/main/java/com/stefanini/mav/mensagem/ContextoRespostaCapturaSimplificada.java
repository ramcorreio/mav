package com.stefanini.mav.mensagem;

import java.text.ParseException;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.util.UtilsDate;

public class ContextoRespostaCapturaSimplificada extends ContextoMensagem<RespostaCapturaSimplificada> {

	public ContextoRespostaCapturaSimplificada() {
		super(CodigoMensagem.C0460, RespostaCapturaSimplificada.class);
	}

	/**
	 * 0351 a 0353 codModalidade 3 A Campos exclusivos para operação de Cartão
	 * 0354 a 0356 codOrg 3 A Campos exclusivos para operação de Cartão 0357 a
	 * 0359 codLogo 3 A Campos exclusivos para operação de Cartão 0360 a 0362
	 * codCampanha 3 A Campos exclusivos para operação de Cartão
	 * 
	 * 0363 a 0932 Filler 570 A
	 * 
	 * @param input
	 * @return
	 */
	private void lerOperacaoCartao(String input, RespostaCapturaSimplificada m) {

		m.setDadosOperacaoCartao(new DadoOperacaoCartao());
		m.getDadosOperacaoCartao().setCodigoModalidade(input.substring(350, 353).trim());
		m.getDadosOperacaoCartao().setCodigoOrg(input.substring(353, 356).trim());
		m.getDadosOperacaoCartao().setCodigoLogo(input.substring(356, 359).trim());
		m.getDadosOperacaoCartao().setCodigoCampanha(input.substring(359, 362).trim());

		m.getDadosOperacaoCartao().setFiller(input.substring(362, 932).trim());
	}
	
	private void escreverOperacaoCartao(StringBuilder b, RespostaCapturaSimplificada m) {

		escreverString(b, 3, m.getDadosOperacaoCartao().getCodigoModalidade());
		escreverString(b, 3, m.getDadosOperacaoCartao().getCodigoOrg());
		escreverString(b, 3, m.getDadosOperacaoCartao().getCodigoLogo());
		escreverString(b, 3, m.getDadosOperacaoCartao().getCodigoCampanha());
		escreverString(b, 570, m.getDadosOperacaoCartao().getFiller());
	}

	private void lerDadoCliente(String input, RespostaCapturaSimplificada m) throws ParseException {

		m.setDadosPessoais(new DadoCliente());
		m.getDadosPessoais().setCpf(input.substring(269, 280));
		m.getDadosPessoais().setDataNascimento(UtilsDate.parse(input.substring(280, 288)));
		m.getDadosPessoais().setComplemento(new DadoComplementar());
		// 0289 a 0289 ClienteEmancipado 1 A
		m.getDadosPessoais().getComplemento().setClienteEmancipado(lerBoolean(input, 288));

		// 0290 a 0291 CodProduto 2 A
		m.getDadosPessoais().getComplemento().setCodigoProduto(input.substring(289, 291));

		// 0292 a 0292 CobraTac 1 A
		m.getDadosPessoais().setCobraTac(lerBoolean(input, 291));

		// 0293 a 0293 Elegibilidade Seguro 1 A
		m.getDadosPessoais().setElegibilidadeSeguro(lerBoolean(input, 292));

		// 0294 a 0301 Codigo Produto Losango 8 A
		m.getDadosPessoais().setCodigoProdutoLosango(input.substring(293, 301));

		// 0302 a 0303 Qtd Numero Sorte 2 N
		m.getDadosPessoais().setQtdNumeroSorte(Integer.valueOf(input.substring(301, 303)));

		// 0304 a 0350 Filler 47 A
		m.getDadosPessoais().setFiller(input.substring(303, 350).trim());
	}
	
	private void escreverDadoCliente(StringBuilder b, RespostaCapturaSimplificada m) {

		escreverString(b, 11, m.getDadosPessoais().getCpf());
		escreverString(b, 8, UtilsDate.format(m.getDadosPessoais().getDataNascimento()));
		// 0289 a 0289 ClienteEmancipado 1 A
		escreverBoolean(b, 1, m.getDadosPessoais().getComplemento().isClienteEmancipado());
		
		// 0290 a 0291 CodProduto 2 A
		escreverString(b, 2, m.getDadosPessoais().getComplemento().getCodigoProduto());

		// 0292 a 0292 CobraTac 1 A
		escreverBoolean(b, 1, m.getDadosPessoais().isCobraTac());

		// 0293 a 0293 Elegibilidade Seguro 1 A
		escreverBoolean(b, 1, m.getDadosPessoais().isElegibilidadeSeguro());

		// 0294 a 0301 Codigo Produto Losango 8 A
		escreverString(b, 8, m.getDadosPessoais().getCodigoProdutoLosango());

		// 0302 a 0303 Qtd Numero Sorte 2 N
		escreverInt(b, 2, m.getDadosPessoais().getQtdNumeroSorte());

		// 0304 a 0350 Filler 47 A
		escreverString(b, 47, m.getDadosPessoais().getFiller());
	}

	@Override
	public void ler(String input, RespostaCapturaSimplificada mensagem) throws MensagemNaoEncontradaException {

		mensagem.getCabecalho().setSentidoFluxo(Fluxo.SAIDA);
		
		// DADOS DA CONSULTA
		// 0084 a 0166 Filler 83 A Filler
		mensagem.setFiller(input.substring(83, 166).trim());

		// 0167 a 0171 mensagemAutorizador 5 A Parecer do autorizador de crédito
		// (Política): Parecer / Msg Score / Motivo Aprov/Neg
		mensagem.setMensagemAutorizador(input.substring(166, 171).trim());

		// 0172 a 0179 data 8 N Data do Sistema
		// 0180 a 0185 hora 6 N Hora do Sistema
		try {
			mensagem.setData(UtilsDate.parseDateHora(input.substring(171, 185)));
		} catch (ParseException e) {
			throw new MensagemNaoEncontradaException(e);
		}

		// 0186 a 0187 codigoStatusProposta 2 A Código do Status da Proposta
		// (02, 03 ou 04)
		// 02 = A0062 = Elegível (bola verde)
		// 03 = A0063 = Segue Fluxo, com Pendencia (bola amarela)
		// 04 = A0064 = Não Elegível (bola vermelha)
		mensagem.setCodigoStatusProposta(input.substring(185, 187).trim());

		// 0188 a 0267 parecer 80 A
		mensagem.setParecer(input.substring(187, 267).trim());

		// 0268 a 0269 produto 2 A
		mensagem.setProduto(input.substring(267, 269).trim());

		// dados pessoais
		try {
			lerDadoCliente(input, mensagem);
		} catch (ParseException e) {

			throw new MensagemNaoEncontradaException(e);
		}

		// dados oepração cartão
		lerOperacaoCartao(input, mensagem);

		// indicadores
		mensagem.setIndicadores(new Indicador());
		mensagem.getIndicadores().setIdentificadorCanal(input.substring(932, 933).trim());
		mensagem.getIndicadores().setVersaoCanal(input.substring(933, 943).trim());
		mensagem.getIndicadores().setPolitica(input.substring(934, 944).trim());
		mensagem.getIndicadores().setAmbiente(input.substring(944, 946).trim());
	}

	@Override
	public void escrever(StringBuilder b, RespostaCapturaSimplificada mensagem) throws MensagemNaoEncontradaException {

		// DADOS DA CONSULTA
		// 0084 a 0166 Filler 83 A Filler
		escreverString(b, 83, mensagem.getFiller());

		// 0167 a 0171 mensagemAutorizador 5 A Parecer do autorizador de crédito
		// (Política): Parecer / Msg Score / Motivo Aprov/Neg
		escreverString(b, 5, mensagem.getMensagemAutorizador());

		// 0172 a 0179 data 8 N Data do Sistema
		// 0180 a 0185 hora 6 N Hora do Sistema
		escreverString(b, 14, UtilsDate.formatDateTime(mensagem.getData()));

		// 0186 a 0187 codigoStatusProposta 2 A Código do Status da Proposta
		// (02, 03 ou 04)
		// 02 = A0062 = Elegível (bola verde)
		// 03 = A0063 = Segue Fluxo, com Pendencia (bola amarela)
		// 04 = A0064 = Não Elegível (bola vermelha)
		escreverString(b, 2, mensagem.getCodigoStatusProposta());

		// 0188 a 0267 parecer 80 A
		escreverString(b, 80, mensagem.getParecer());

		// 0268 a 0269 produto 2 A
		escreverString(b, 2, mensagem.getProduto());

		// dados pessoais
		escreverDadoCliente(b, mensagem);

		// dados oepração cartão
		escreverOperacaoCartao(b, mensagem);

		// indicadores
		escreverString(b, 1, mensagem.getIndicadores().getIdentificadorCanal());
		escreverString(b, 10, mensagem.getIndicadores().getVersaoCanal());
		escreverString(b, 1, mensagem.getIndicadores().getPolitica());
		escreverString(b, 2, mensagem.getIndicadores().getAmbiente());
	}

}
