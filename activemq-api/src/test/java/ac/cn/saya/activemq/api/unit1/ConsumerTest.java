package ac.cn.saya.activemq.api.unit1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @Title: ConsumerTest
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-03 22:25
 * @Description:
 */

public class ConsumerTest {

    public static final String ACTIVEMQ_URL = "tcp://172.20.1.91:61616";
    public static final String QUEUE_NAME = "queue01";

    @Test
    public void consumer1(){
        consumer("#1");
    }

    @Test
    public void consumer2(){
        consumer("#2");
    }

    public void consumer(String name){
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
            //两个参数，第一个事务(偏生产)，第二个签收(偏消费)
            //第一个参数为false表示自动提交，为true时，需要手动执行session.commit，否则会出现重复消费
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地（是具体的主题还是队列）
            Queue queue = session.createQueue(QUEUE_NAME);

            //5、创建消费者
            messageConsumer = session.createConsumer(queue);

            while (true){
                // 不加参数，死等消息
                //TextMessage textMessage = (TextMessage)messageConsumer.receive();
                //加参数后，过了时间就不等了
                TextMessage textMessage = (TextMessage)messageConsumer.receive(5000);
                if (null != textMessage){
                    System.out.println("消费者"+name+"接收到消息："+textMessage.getText());
                }else {
                    break;
                }
            }
        } catch (JMSException e) {
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
