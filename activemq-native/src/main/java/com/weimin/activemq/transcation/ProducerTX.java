package com.weimin.activemq.transcation;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.UUID;

public class ProducerTX {

    public static void main(String[] args) throws JMSException {
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
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        // 创建目的地
        Destination queue = session.createQueue("queue03");

        // 创建消息生产者
        MessageProducer producer = session.createProducer(queue);

        //创建一条文本消息
        TextMessage textMessage = session.createTextMessage("文本消息:"+ UUID.randomUUID().toString());

        // 发送
        producer.send(textMessage);

        producer.close();

        session.commit();
        session.close();
        connection.close();

        System.out.println("发送完成");

    }
}
