package com.stefanini.mav.tcp;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.easymock.EasyMock;
import org.junit.Test;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.MensagemHelper;
import com.stefanini.mav.util.Utils;

public class EncoderDecoderTest {
	
	@Test
	public void encode() throws IOException, URISyntaxException, MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
		IoSession session = EasyMock.mock(IoSession.class);
		ProtocolEncoderOutput out = EasyMock.mock(ProtocolEncoderOutput.class);
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		MensagemBasica m = MensagemFactory.parse(input);
		
		MensagemEncoder encoder = new MensagemEncoder();
		out.write(Utils.writeBuffer(input));
		
		EasyMock.replay(session, out);
		encoder.encode(session, m, out);
		EasyMock.verify(session, out);
	}
	
	@Test
	public void decode() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ClassNotFoundException {
		
		IoSession session = EasyMock.mock(IoSession.class);
		ProtocolDecoderOutput out = EasyMock.mock(ProtocolDecoderOutput.class);
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		MensagemBasica m = MensagemFactory.parse(input);
		MensagemDecoder decoder = new MensagemDecoder();
		out.write(m);
		
		IoBuffer buf = Utils.writeBuffer(input);
		
		EasyMock.replay(session, out);
		decoder.decode(session, buf, out);
		EasyMock.verify(session, out);
	}

}
