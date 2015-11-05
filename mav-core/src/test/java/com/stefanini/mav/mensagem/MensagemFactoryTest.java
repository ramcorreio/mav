package com.stefanini.mav.mensagem;


import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.stefanini.mav.util.MensagemHelper;

/**
 * 
 * Layout das mensagens está mav/mav-core/docs/TRS_CDC-EP_V9-Layout_Completo_082015.xls
 * 
 * @author Rodrigo
 *
 */
public class MensagemFactoryTest {
	
	@Test(expected = MensagemNaoEncontradaException.class)
	public void criarMensagemVazia() throws MensagemNaoEncontradaException {
		
		MensagemFactory.parse("");
	}
	
	@Test(expected = MensagemNaoEncontradaException.class)
	public void criarMensagemNula() throws MensagemNaoEncontradaException {
		
		MensagemFactory.parse(null);
	}
	
	@Test
	public void criarCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException{
		
		String messagem = MensagemHelper.lerMensagem(186, 450, "criarCapturaSimplicada.1");
		
		SolicitacaoCapturaSimplificada m = (SolicitacaoCapturaSimplificada) MensagemFactory.parse(messagem);
		Cabecalho expected = new Cabecalho();
		expected.setTamanho(116);
		expected.setTipo(TipoMensagem.C0450);
		expected.setNumeroTransacao(980008);
		expected.setNumeroProposta("");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("");
		expected.setCodigoLojista(170894002);
		expected.setVersao("9");
		expected.setCampoLojista("");
		
		MensagemHelper.verificarCabecalho(expected, m.getCabecalho());
		assertThat(m.getDadosPessoais(), notNullValue());
		assertThat(m.getDadosPessoais().getCpf(), is(equalTo("394830984093284902")));
		/*CPF
		Data de Nascimento
		Filler
		codigoOrg
		codigoLogo
		codigoCampanha
		codigoModalidade
		Filler
		Flag Cliente Emancipado
		Produto
		Identificação do Canal
		Versão do Canal
		Política
		Ambiente*/

		
		
	}
	
	@Test
	public void criarRespostaCapturaSimplicada() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarGeracaoToken() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarRespostaGeracaoToken() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarRespostaPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
}
