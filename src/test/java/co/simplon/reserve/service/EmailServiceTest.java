package co.simplon.reserve.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
//Update your configuration file with the right path
@ContextConfiguration(locations = { "/spring-config.xml", "/mail-bean.xml" , "/spring-mvc-config.xml", "/spring-security-config.xml"})
@WebAppConfiguration
public class EmailServiceTest {
	
	@Autowired
	EmailService emailAPI;
	  
	  @Test
	  @Ignore
	  public void testEmailService() {
		  String toAddress = "example@gmail.com";
		  String fromAddress = "simplon.reservation.assistance@gmail.com";
		  String subject = "testEmailService";
		  String msgBody = "Test Successed";
		  
		  emailAPI.readyToSendEmail(toAddress, fromAddress, subject, msgBody);
	  }
}
