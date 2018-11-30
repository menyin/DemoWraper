package mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class TestMq {
    @Test
    public void queueProvider() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.236:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue testqueue = session.createQueue("testqueue1");

        MessageProducer producer = session.createProducer(testqueue);

        TextMessage textMassage = session.createTextMessage("这是第一个queue消息2");

        producer.send(textMassage);

        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void queueCustomer() throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.236:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue testqueue = session.createQueue("testqueue1");

        MessageConsumer consumer = session.createConsumer(testqueue);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;
                    try {
                        System.out.println(msg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                        System.out.println("外星人来抓小孩啦");
                    }
                }
            }

        });

        System.in.read();

        session.close();
        connection.close();
    }

    @Test
    public void topicProvider() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.236:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("testTopic1");

        MessageProducer producer = session.createProducer(topic);

        TextMessage textMassage = session.createTextMessage("这是第二个topic消息");

        producer.send(textMassage);

        producer.close();
        session.close();
        connection.close();
    }
    @Test
    public void topicCustomer1() throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.236:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("testTopic1");

        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;
                    try {
                        System.out.println(msg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                        System.out.println("外星人来抓小孩啦");
                    }
                }
            }

        });
        System.out.println("testTopc赶紧出来...");

        System.in.read();
        session.close();
        connection.close();
    }
}
