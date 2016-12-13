package co.simplon.reserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mail; // MailSender interface defines a strategy for
			     // sending simple mails

    public void readyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
	SimpleMailMessage simpleMsg = new SimpleMailMessage();
	simpleMsg.setFrom(fromAddress);
	simpleMsg.setTo(toAddress);
	simpleMsg.setSubject(subject);
	simpleMsg.setText(msgBody);
	mail.send(simpleMsg);
    }
}
