package com.demo.spring;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MyJmsListener {

    /*
     *
     * It create a JMS Listener and consume every message from inbound.queue and
     * post back the same message to outbound.queue
     *
     */
    @JmsListener(destination = "inbound.queue")
    @SendTo("outbound.queue")
    public String receiveMessage(final Message strMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + strMessage);
        String response = null;
        if (strMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) strMessage;
            messageData = textMessage.getText();
            response = "Message: " + messageData;
        }
        return response;
    }

    /*
     *
     * It create a JMS Listener and consume every message from inbound.topic and
     * post back the same message to outbound.topic
     */
    @JmsListener(destination = "inbound.topic")
    @SendTo("outbound.topic")
    public String receiveMessageFromTopic(final Message strMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + strMessage);
        String response = null;
        if (strMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) strMessage;
            messageData = textMessage.getText();
            response = "Message: " + messageData;
        }
        return response;
    }

}
