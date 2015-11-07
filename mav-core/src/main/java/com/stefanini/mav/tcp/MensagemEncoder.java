package com.stefanini.mav.tcp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public class MensagemEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws MensagemNaoEncontradaException {
		
		if (!(message instanceof MensagemBasica)) {
            throw new MensagemNaoEncontradaException();
        }
		
		MensagemBasica input = MensagemBasica.class.cast(message);
		
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(input.getCabecalho().getCodigo());
		IoBuffer buf = IoBuffer.allocate(2048);
        buf.setAutoExpand(true);
        buf.putObject(ctx.escrever(input));
        buf.flip();
		
		out.write(buf);
	}

}
