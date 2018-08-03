package com.corgiking.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MsgQueueListener implements MessageListener {

	@Override
	public void onMessage(Message message) {


		if (message instanceof ActiveMQTextMessage) {
			String jsonString = null;
			try {
				jsonString = ((ActiveMQTextMessage) message).getText();
				ObjectMapper objectMapper = new ObjectMapper();
				// 保存数据
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} else {
			throw new RuntimeException("不是ActiveMQTextMessage信息!");
		}

	}

}
