package com.weimin.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicListenerConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        // 创建连接工厂，指定连接到那个activemq
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(
                        "admin",
                        "admin",
                        "tcp://123.56.169.57:61616");

        // 创建一个连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 创建一个session 第一个参数表示是否开启事务，第二个参数表示签收方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //
        Destination topic = session.createTopic("topic01");

        // 创建消费者
        MessageConsumer consumer = session.createConsumer(topic);

        // 异步监听
        consumer.setMessageListener(message -> {
            if(message!=null){
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try {
                    text = textMessage.getText();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println(text);
            }

        });

        System.in.read();

        consumer.close();
        session.close();
        connection.close();
    }
}
