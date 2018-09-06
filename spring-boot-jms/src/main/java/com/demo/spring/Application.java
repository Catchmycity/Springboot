package com.demo.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@SpringBootApplication
@EnableJms
public class Application {

    public static void main(String[] args) throws JMSException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        JmsTemplate jt = ctx.getBean(JmsTemplate.class);

        /*Post 10 message to testQueue*/
        for (int i = 0; i < 10; i++) {
            final int count = i;
            jt.send("testQueue", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage();
                    message.setText("message" + count);
                    return message;
                }
            });
        }
        /*Receive 10 message from testQueue*/
        for (int i = 0; i < 10; i++) {

            Message message = jt.receive("testQueue");
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                System.out.println(tm.getText());
            }

        }

    }

}
