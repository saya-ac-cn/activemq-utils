package ac.cn.saya.activemq.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Title: MyMQListener
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-05 22:15
 * @Description:
 */
@Component
public class MyMQListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (null != message && message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
