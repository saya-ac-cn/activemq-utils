package ac.cn.saya.activemq.api.unit1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @Title: ConsumerListener
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/12/5 0005 14:56
 * @Description: 使用消息监听器
 */

public class ConsumerListener {

    public static final String ACTIVEMQ_URL = "tcp://172.20.1.91:61616";
    public static final String QUEUE_NAME = "queue01";

    @Test
    public void listener() {
        Connection connection = null;
        Session session = null;
        MessageConsumer messageConsumer = null;
        try {
            //1、创建连接工程，按照给定的URL地址，采用默认用户名和密码
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

            //2、通过连接工程，获得连接Connection并启动访问
            connection = activeMQConnectionFactory.createConnection();
            connection.start();

            //3、创建会话session
            //两个参数，第一个事务，第二个签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地（是具体的主题还是队列）
            Queue queue = session.createQueue(QUEUE_NAME);

            //5、使用监听器的方式来消费消息
            messageConsumer = session.createConsumer(queue);
            //异步非阻塞方式（监听器onMessage（））
            //订阅者或消费者通过MessageConsumer的setMessageListener（MessageListener（queue））注册一个消息监听器
            //当消息到达之后，系统自动调用监听器MessageListener（Message message）方法
            messageConsumer.setMessageListener(message -> {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接收到消息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            // 等待
            System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6、关闭资源
            try {
                messageConsumer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }

}
