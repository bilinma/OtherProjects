package com.bilin.test;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AmazonSESSample {
	// Replace sender@example.com with your "From" address.
	// This address must be verified.
	static final String FROM = "sender@example.com";
	static final String FROMNAME = "Sender Name";
	// Replace recipient@example.com with a "To" address. If your account
	// is still in the sandbox, this address must be verified.
	static final String TO = "recipient@example.com";
	// Replace smtp_username with your Amazon SES SMTP user name.
	static final String SMTP_USERNAME = "smtp_username";
	// Replace smtp_password with your Amazon SES SMTP password.
	static final String SMTP_PASSWORD = "smtp_password";
	// The name of the Configuration Set to use for this message.
	// If you comment out or remove this variable, you will also need to
	// comment out or remove the header below.
	static final String CONFIGSET = "ConfigSet";
	// Amazon SES SMTP host name. This example uses the US West (Oregon) region.
	// See
	// http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html开region-endpoints
	// for more information.
	static final String HOST = "email-smtp.us-west-2.amazonaws.com";
	// The port you will connect to on the Amazon SES SMTP endpoint.
	static final int PORT = 587;
	static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
	static final String BODY = String.join(System.getProperty("line.separator"), "<h1>Amazon SES SMTP Email Test</h1>",
			"<p>This email was sent with Amazon SES using the ",
			"<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
			" for <a href='https://www.java.com'>Java</a>.");

	public static void main(String[] args) throws Exception {
		// Create a Properties object to contain connection configuration
		// information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		// Create a Session object to represent a mail session with the specified properties.
		Session session = Session.getDefaultInstance(props);
		// Create a message with the specified information.

		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY, "text/html");
		
        // 5. 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();
        // 读取本地文件
        DataHandler dh = new DataHandler(new FileDataSource("src\\mailTestPic.png"));
        // 将图片数据添加到"节点"
        image.setDataHandler(dh);
        // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
        image.setContentID("mailTestPic");    
         
        // 6. 创建文本"节点"
        MimeBodyPart text = new MimeBodyPart();
        // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent("这是一张图片<br/><a href='http://www.cnblogs.com/ysocean/p/7666061.html'><img src='cid:mailTestPic'/></a>", "text/html;charset=UTF-8");
         
        // 7. （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related");    // 关联关系
		
        // 8. 将 文本+图片 的混合"节点"封装成一个普通"节点"
        // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mailTestPic 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);
        
        // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.setSubType("mixed");         // 混合关系
 
        // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        msg.setContent(mm);
		
		
		
		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
		// Create a transport.
		Transport transport = session.getTransport();
		// Send the message.
		try {
			System.out.println("Sending...");
			// Connect to Amazon SES using the SMTP username and password you
			// specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
	}
}