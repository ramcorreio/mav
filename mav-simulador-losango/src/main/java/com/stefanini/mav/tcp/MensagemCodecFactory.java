package com.stefanini.mav.tcp;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MensagemCodecFactory implements ProtocolCodecFactory {
	
	private MensagemEncoder encoder = new MensagemEncoder();

	private MensagemDecoder decoder = new MensagemDecoder();

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {

		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {

		return decoder;
	}

}
