package ac.cn.saya.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Title: Consumer
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/12/5 0005 17:47
 * @Description:
 */
@Service
public class Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Consumer consumer = applicationContext.getBean(Consumer.class);
        String message = (String) consumer.jmsTemplate.receiveAndConvert();
        System.out.println("消费者收到"+message);
    }

}
