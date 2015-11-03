package com.stefanini.mav.mensagem;

public abstract class LeitorMensagem<T extends MensagemBasica> {
	
	private StringBuilder input;
	
	private TipoMensagem tipoMensagem;
	
	protected LeitorMensagem(StringBuilder input, TipoMensagem tipo) {
		this.input = input;
		this.tipoMensagem = tipo;
	}
	
	public T ler() throws MensagemNaoEncontradaException {
		
		Cabecalho cabecalho = Cabecalho.novo(input);
		
		if(!tipoMensagem.equals(cabecalho.getTipo())) {
			throw new MensagemNaoEncontradaException("Tipo " + tipoMensagem + " inválido");
		}
		
		return montarMensagem(cabecalho);
	}
	
	public abstract T montarMensagem(Cabecalho cabecalho);

}
