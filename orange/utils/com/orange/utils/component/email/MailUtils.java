package com.orange.utils.component.email;

public class MailUtils {
	public static boolean sendMail(String mailServerHost,
			String mailServerPort, String validate, String userName,
			String fromUser, String fromPassword, String toUser, String title,
			String content) {
		// 这个类主要是设置邮件
		System.out.println("mailPort:"+ mailServerPort+",Host:" + mailServerHost + "validate:" + validate 
				+",userName:" + userName + ",fromUser:" + fromUser + ",fromPassWord:" + fromPassword 
				+",toUser:" + toUser + ",title:" + title + "content:" + content );
		MailSender mailInfo = new MailSender();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		boolean isValidate = false;
		if ("true".equals(validate.toLowerCase())) {
			isValidate = true;
		}
		mailInfo.setValidate(isValidate);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(fromPassword);// 您的邮箱密码
		mailInfo.setFromAddress(fromUser);
		mailInfo.setToAddress(toUser);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// boolean resultText = sms.sendTextMail(mailInfo);//发送文体格式
		boolean resultHtml = sms.sendHtmlMail(mailInfo);// 发送html格式
		// System.out.println("Send Mail By Text : " + resultText );
		return resultHtml;
	}
}
