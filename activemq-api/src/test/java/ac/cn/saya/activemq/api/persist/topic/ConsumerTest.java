package ac.cn.saya.activemq.api.persist.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @Title: ConsumerTest
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/12/5 0005 16:01
 * @Description:
 * 主题+使用监听器(持久化)
 */

public class ConsumerTest {

    public static final String ACTIVEMQ_URL = "tcp://172.20.1.91:61616";
    public static final String TOPIC_NAME = "topic-persist";

    @Test
    public void listener(){
        Connection connection = null;
        Session session = null;
        Message message = null;
        try {
            //1、创建连接工程，按照给定的URL地址，采用默认用户名和密码
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

            //2、通过连接工程，获得连接Connection并启动访问
            connection = activeMQConnectionFactory.createConnection();
            connection.setClientID("topic-persist-client1");

            //3、创建会话session
            //两个参数，第一个事务，第二个签收
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地（是具体的主题还是队列）
            Topic topic = session.createTopic(TOPIC_NAME);
            TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "test topic persist");
            connection.start();

            message = topicSubscriber.receive();
            while (null != message){
                TextMessage textMessage = (TextMessage)message;
                System.out.println("消费者接收到消息："+textMessage.getText());
                message = topicSubscriber.receive(5000l);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            //6、关闭资源
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}
