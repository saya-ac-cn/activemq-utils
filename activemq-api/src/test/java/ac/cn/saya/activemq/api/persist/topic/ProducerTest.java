package ac.cn.saya.activemq.api.persist.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @Title: ProducerTest
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/12/5 0005 16:03
 * @Description:
 * 使用主题发布消息（持久化）
 */

public class ProducerTest {

    public static final String ACTIVEMQ_URL = "tcp://172.20.1.91:61616";
    public static final String TOPIC_NAME = "topic-persist";

    @Test
    public void send(){
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {
            //1、创建连接工程，按照给定的URL地址，采用默认用户名和密码
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

            //2、通过连接工程，获得连接Connection并启动访问
            connection = activeMQConnectionFactory.createConnection();

            //3、创建会话session
            //两个参数，第一个事务，第二个签收
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地（是具体的主题还是队列）
            Topic topic = session.createTopic(TOPIC_NAME);

            //5、创建消息的生产者
            messageProducer = session.createProducer(topic);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

            connection.start();

            //6、通过使用messageProducer生产3条消息发送到MQ的队列里面
            for (int i = 1;i <= 3; i++){
                //7、创建消息，
                TextMessage textMessage = session.createTextMessage("msg"+i);
                //8、通过messageProducer发送给MQ
                messageProducer.send(textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            //9、关闭资源
            try {
                messageProducer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("消息发送完毕");
        }
    }

}
