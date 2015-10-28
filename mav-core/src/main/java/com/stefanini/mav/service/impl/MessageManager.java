package com.stefanini.mav.service.impl;


import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import com.stefanini.mav.service.IMessageManager;

@Service
@Transactional(value = TxType.REQUIRED)
public class MessageManager implements IMessageManager {

}
