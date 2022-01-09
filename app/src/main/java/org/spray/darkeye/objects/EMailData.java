package org.spray.darkeye.objects;

public class EMailData {

	private String email, password;
	private EMailMethod method;

	public EMailData(String email) {
		this(email, "", EMailMethod.NONE);
	}

	public EMailData(String email, String password, EMailMethod method) {
		this.email = email;
		this.password = password;
		this.method = method;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public EMailMethod getMethod() {
		return method;
	}

	public enum EMailMethod {
		GMAIL("smtp.gmail.com", 587, true), MAIL("smtp.mail.ru", 993, true), YANDEX("smtp.yandex.ru", 587, true),
		NONE("", -1, false);

		private String host;
		private int port;
		private boolean ssl;

		EMailMethod(String host, int port, boolean ssl) {
			this.host = host;
			this.port = port;
			this.ssl = ssl;
		}

		public String getHost() {
			return host;
		}

		public int getPort() {
			return port;
		}

		public boolean isSSL() {
			return ssl;
		}
	}

}
