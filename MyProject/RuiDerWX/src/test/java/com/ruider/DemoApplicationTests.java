package com.ruider;

import com.ruider.utils.ActiveMQ.ActiveMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private ActiveMQProducer activeMQProducerTest;
	@Test
	public void contextLoads() throws Exception{
		activeMQProducerTest.send();
		Thread.sleep(10000);
	}

}
