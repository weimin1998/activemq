package com.weimin.activemq.persistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.UUID;

public class Producer {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(
                        "admin",
                        "admin",
                        "tcp://123.56.169.57:61616");

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("queue02");

        // 创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        // 消息非持久化，服务器宕机后，消息全部丢失
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        TextMessage textMessage = session.createTextMessage("文本消息:"+ UUID.randomUUID().toString());


        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();

        System.out.println("发送完成");

    }
}
