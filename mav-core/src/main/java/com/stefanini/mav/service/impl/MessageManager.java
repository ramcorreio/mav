package com.stefanini.mav.service.impl;


import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.xml.ws.ServiceMode;

import com.stefanini.mav.service.IMessageManager;

@ServiceMode
@Transactional(value = TxType.REQUIRED)
public class MessageManager implements IMessageManager {

}
