package ac.cn.saya.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Title: Producer
 * @ProjectName activemq-utils
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/12/5 0005 17:47
 * @Description:
 */
@Service
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Producer producer = applicationContext.getBean(Producer.class);
        producer.jmsTemplate.send(session ->  session.createTextMessage("*****spring和activeMQ的整合*****"));
        System.out.println("send end");
    }

}
