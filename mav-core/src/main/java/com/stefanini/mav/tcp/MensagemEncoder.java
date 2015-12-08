package com.stefanini.mav.tcp;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public class MensagemEncoder extends ProtocolEncoderAdapter {
	
	private final CharsetEncoder encoder = Charset.defaultCharset().newEncoder();

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws MensagemNaoEncontradaException, CharacterCodingException, MapeamentoNaoEncontrado {
		
		if (!(message instanceof MensagemBasica)) {
            throw new MensagemNaoEncontradaException();
        }
		
		MensagemBasica input = MensagemBasica.class.cast(message);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(input.getCabecalho().getCodigo());
		String output = ctx.escrever(input);
		
		IoBuffer buf = IoBuffer.allocate(output.length()).setAutoExpand(true);
        buf.putString(output, encoder);
        buf.putString(LineDelimiter.UNIX.getValue(), encoder);
        buf.flip();
		
		out.write(buf);
	}

}
