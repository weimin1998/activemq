package com.weimin.activemq.broker;

import org.apache.activemq.broker.BrokerService;

public class Broker {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);

        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();

    }
}
