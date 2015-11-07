package com.stefanini.mav.service;


import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

@Service
@Transactional(value = TxType.REQUIRED)
public class MessageManager implements IMessageManager {

}
