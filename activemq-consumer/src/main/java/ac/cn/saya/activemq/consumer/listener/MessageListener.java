package ac.cn.saya.activemq.consumer.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Title: QueueListener
 * @ProjectName mq
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-29 17:48
 * @Description:
 * 队列监听器
 */
@Component
public class MessageListener {

    @JmsListener(destination = "${sub-topic}")
    public void receive(TextMessage textMessage){
        try {
            System.out.println("收到主题的消息："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
