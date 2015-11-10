package com.stefanini.mav.tcp;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;

import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public class MensagemDecoder extends ProtocolDecoderAdapter {
	
	private final CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws CharacterCodingException, MensagemNaoEncontradaException {
		
		//localiza o LF
        in.limit(in.limit() - LineDelimiter.UNIX.getValue().length());
        
		String input = in.getString(decoder);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.parseContexto(input);
		MensagemBasica m = ctx.ler(input);
		out.write(m);
	}
}
