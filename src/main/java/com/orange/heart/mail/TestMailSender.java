/**
 * 
 */
package com.orange.heart.mail;

/**
 * @author Administrator
 * 
 */
public class TestMailSender {

	public static boolean sendMail(String mailFrom, String password, String[] mailTo, String subject, String content,
			String[] files) {
		try {
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setValidate(true);
			mailInfo.setUserName(mailFrom);
			mailInfo.setPassword(password);// 您的邮箱密码
			mailInfo.setFromAddress(mailFrom);
			mailInfo.setToAddress(mailTo);
			mailInfo.setSubject(subject);
			mailInfo.setContent(content);
			mailInfo.setAttachFileNames(files);
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			// SimpleMailSender.sendTextMail(mailInfo);// 发送文体格式
			return sms.sendHtmlMail(mailInfo);// 发送html格式
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean sendMail(String mailFrom, String password, String[] mailTo, String subject, String content) {
		return sendMail(mailFrom, password, mailTo, subject, content, null);
	}

}
