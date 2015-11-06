package com.stefanini.mav.mensagem;

import java.text.ParseException;

import com.stefanini.mav.util.UtilDate;

public class ContextoRespostaCapturaSimplificada extends ContextoMensagem<RespostaCapturaSimplificada> {
	
	
	private DadoPessoal montarDadoPessoais(String input) throws ParseException {
		
		DadoPessoal dadosPessoais = new DadoPessoal();
		dadosPessoais.setCpf(input.substring(269, 280));
		dadosPessoais.setDataNascimento(UtilDate.parse(input.substring(280, 288)));
		
		//TODO: falta implementar os demais campos
		dadosPessoais.setFiller(input.substring(303, 350).trim());
		return dadosPessoais;
	}
	
	@Override
	public RespostaCapturaSimplificada montar(String input, Cabecalho cabecalho) throws MensagemNaoEncontradaException {
		
		RespostaCapturaSimplificada m = new RespostaCapturaSimplificada(cabecalho);
		
		//DADOS DA CONSULTA
		//0084 a 0166	Filler	83	A	Filler
		m.setFiller(input.substring(83, 166).trim());
		
		//0167 a 0171	mensagemAutorizador	5	A	Parecer do autorizador de crédito (Política): Parecer / Msg Score / Motivo Aprov/Neg
		m.setMensagemAutorizador(input.substring(166, 171).trim());
		
		//0172 a 0179	data	8	N	Data do Sistema
		//0180 a 0185	hora	6	N	Hora do Sistema
		try {
			m.setData(UtilDate.parseDateHora(input.substring(171, 185)));
		} catch (ParseException e) {
			throw new MensagemNaoEncontradaException(e) ;
		}
		
		//0186 a 0187	codigoStatusProposta	2	A	Código do Status da Proposta (02, 03 ou 04)	
		//02 = A0062 = Elegível (bola verde)
		//03 = A0063 = Segue Fluxo, com Pendencia (bola amarela) 
		//04 = A0064 = Não Elegível (bola vermelha)
		m.setCodigoStatusProposta(input.substring(185, 187).trim());
		
		//0188 a 0267	parecer	80	A
		m.setParecer(input.substring(187, 267).trim());
		
		//0268 a 0269	produto	2	A
		m.setProduto(input.substring(267, 269).trim());


		//dados pessoais
		try {
			m.setDadosPessoais(montarDadoPessoais(input));
		} catch (ParseException e) {
			
			throw new MensagemNaoEncontradaException(e);
		}

		//dados oepração cartão
		//m.setDadosOperacaoCartao(montarOperacaoCartao(input));
		
		//dados complementares
		//m.setComplemento(new DadoComplementar());
		//m.getComplemento().setClienteEmancipado(Boolean.valueOf(input.substring(182, 183)));
		//m.getComplemento().setCodigoProduto(input.substring(183, 185).trim());
		
		//indicadores
		//m.setIndicadores(new Indicador());
		//m.getIndicadores().setIdentificadorCanal(input.substring(185, 186).trim());
		//m.getIndicadores().setVersaoCanal(input.substring(187, 196).trim());
		//m.getIndicadores().setPolitica(input.substring(196, 197).trim());
		//m.getIndicadores().setAmbiente(input.substring(197, 199).trim());
		
		return m;
		
	}

}
