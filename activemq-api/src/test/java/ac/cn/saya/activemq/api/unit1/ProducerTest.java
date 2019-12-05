package ac.cn.saya.activemq.api.unit1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @Title: ProducerTest
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-03 22:15
 * @Description:
 * 消息生产者
 * 1、先生产    只有#1消费者，问题：#1能消费消息么？
 *      能
 * 2、先生产    先启动#1消费者，然后启动#2消费者，问题：#2能消费么？
 *      #1能消费
 *      #2号不能消费
 * 3、先启动2个消费者，在生产6条消息，消费情况如何?
 *      消息平分
 */

public class ProducerTest {

    public static final String ACTIVEMQ_URL = "tcp://172.20.1.91:61616";
    public static final String QUEUE_NAME = "queue01";

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
            connection.start();

            //3、创建会话session
            //两个参数，第一个事务(偏生产)，第二个签收，默认自动(偏消费)
            //第一个参数为false表示自动提交，为true时，需要手动执行session.commit
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            //4、创建目的地（是具体的主题还是队列）
            Queue queue = session.createQueue(QUEUE_NAME);

            //5、创建消息的生产者
            messageProducer = session.createProducer(queue);
            // 非持久化，MQ宕机后消息要丢失
            //messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 持久化，MQ宕机后消息不会丢失（默认）
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //6、通过使用messageProducer生产3条消息发送到MQ的队列里面
            for (int i = 1;i <= 6; i++){
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
