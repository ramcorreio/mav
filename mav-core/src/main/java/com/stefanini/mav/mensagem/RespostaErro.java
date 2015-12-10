package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.es.ConfiguracaoMensagem;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.SAIDA)
public class RespostaErro extends MensagemBasica {

	private static final long serialVersionUID = 9221336402909229843L;

	@MapAtributo(tamanho = 81)
	private String descricao;

	//0165 a 0165	Identificação do Canal	1
	//0166 a 0175	Versão do Canal	10
	//0176 a 0176	Política	1
	//0177 a 0178	Ambiente	2
	@MapBean
	private Indicador indicadores;

	public RespostaErro(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	} 

}
