package controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	public void sendMail(String reciver, String content, String subject) throws Exception {
		//받는사람, 아이디, 내용, 제목
		MimeMessage msg = mailSender.createMimeMessage();

		msg.setHeader("content-type", "text/html; charset=UTF-8");//보낼형식 = text/html형식
		msg.setContent(content, "text/html; charset=UTF-8");//컨텐트 형식 = text/html형식
		msg.setSubject(subject);//제목
		msg.setRecipient(MimeMessage.RecipientType.TO , new InternetAddress(reciver));//받는사람메일주소
		mailSender.send(msg);
	}
}
