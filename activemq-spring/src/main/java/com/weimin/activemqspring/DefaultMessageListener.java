package com.weimin.activemqspring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class DefaultMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message!=null){
            TextMessage textMessage = (TextMessage)message;
            try {
                String text = textMessage.getText();
                System.out.println(text);
                System.out.println("监听");
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }


    }
}
