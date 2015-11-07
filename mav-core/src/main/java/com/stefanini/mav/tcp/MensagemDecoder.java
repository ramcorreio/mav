package com.stefanini.mav.tcp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

public class MensagemDecoder extends ProtocolDecoderAdapter {

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws ClassNotFoundException, MensagemNaoEncontradaException {
		
		//System.out.println(in.getObject());
		/*ObjectInputStream ois = new ObjectInputStream(in.asInputStream());
		MensagemBasica m = (MensagemBasica) ois.readObject();*/
		String input = (String) in.getObject();
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.parseContexto(input);
		out.write(ctx.ler(input));
	}
}
