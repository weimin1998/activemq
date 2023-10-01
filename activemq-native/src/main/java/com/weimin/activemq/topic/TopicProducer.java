package com.weimin.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.UUID;

public class TopicProducer {
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
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个主题
        Destination topic = session.createTopic("topic01");

        // 生产者
        MessageProducer producer = session.createProducer(topic);

        // 生产一条消息
        TextMessage textMessage = session.createTextMessage("主题消息"+ UUID.randomUUID().toString());
        textMessage.setStringProperty("","");
        // 发送
        producer.send(textMessage);


        producer.close();
        session.close();
        connection.close();

        System.out.println("发送完成");
    }
}
