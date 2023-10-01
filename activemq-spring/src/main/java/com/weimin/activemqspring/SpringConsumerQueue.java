package com.weimin.activemqspring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class SpringConsumerQueue {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/activemq.xml");

        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);


        String message = (String) jmsTemplate.receiveAndConvert();

        System.out.println(message);
    }
}
