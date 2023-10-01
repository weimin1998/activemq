package com.weimin.activemq.persistence;

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
        connection.setClientID("weimin");


        // 创建一个session 第一个参数表示是否开启事务，第二个参数表示签收方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //
        Topic topic = session.createTopic("topic02");

        // 创建消费者
        TopicSubscriber subscriber = session.createDurableSubscriber(topic,"");

        connection.start();
        // 异步监听
        Message message = subscriber.receive(1000L);
        while (message != null){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("接受到持久化消息："+textMessage.getText());
            //参数表示 等一定时间以后就不接收了
            //不写参数表示一直等待
            message = subscriber.receive(1000L);
        }

        subscriber.close();
        session.close();
        connection.close();
    }
}
