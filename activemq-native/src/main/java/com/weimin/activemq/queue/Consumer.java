package com.weimin.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
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

        // 创建目的地
        Destination queue = session.createQueue("queue01");


        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        while (true){
            // 同步阻塞
            TextMessage message = (TextMessage) consumer.receive(1000L);
            if(message!=null){
                String text = message.getText();
                System.out.println(text);
            }
            else {
                break;
            }
        }

        consumer.close();
        session.close();
        connection.close();

        System.out.println("接收完成");

    }
}
