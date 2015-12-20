package com.stefanini.mav.mensagem;

import java.util.List;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.MapLista;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.ENTRADA)
public class GeracaoToken extends MensagemBasica {

	private static final long serialVersionUID = -3382921997674676955L;
	
	@MapLista(maxSize = 10, attr = @MapAtributo(tamanho = 6))
	private List<String> tipoServico;
	
	@MapBean
	private Indicador indicadores;
	
	public GeracaoToken(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
	
	public List<String> getTipoServico() {
		return tipoServico;
	}
	
	public Indicador getIndicadores() {
		return indicadores;
	}
	
	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}
	
	public void setTipoServico(List<String> tipoServico) {
		this.tipoServico = tipoServico;
	}
}
