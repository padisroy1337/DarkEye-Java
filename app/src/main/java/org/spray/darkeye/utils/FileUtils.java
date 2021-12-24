package org.spray.darkeye.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.spray.darkeye.Config;

public class FileUtils {

	public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
		Path p = Files.createFile(Paths.get(zipFilePath));
		Path pp = Paths.get(sourceDirPath);
		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p)); Stream<Path> paths = Files.walk(pp)) {
			paths.filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
				try {
					zs.putNextEntry(zipEntry);
					Files.copy(path, zs);
					zs.closeEntry();
				} catch (IOException e) {
				}
			});
		}
	}

	public static void copyDirectory(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}

			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
			}
		} else {

			InputStream in = new FileInputStream(sourceLocation);
			OutputStream out = new FileOutputStream(targetLocation);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}

	private static String getJarName() {
		return new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
	}

	public static boolean isRunningFromJAR() {
		String jarName = getJarName();
		return jarName.contains(".jar");
	}

	public static String getProgramDirectory() {
		if (isRunningFromJAR()) {
			return getCurrentJARDirectory();
		} else {
			return getCurrentProjectDirectory();
		}
	}

	private static String getCurrentProjectDirectory() {
		return new File("").getAbsolutePath();
	}

	public static String getCurrentJARDirectory() {
		try {
			return getCurrentJARFilePath().getParent();
		} catch (URISyntaxException exception) {
			exception.printStackTrace();
		}

		throw new IllegalStateException("Unexpected null JAR path");
	}

	public static File getCurrentJARFilePath() throws URISyntaxException {
		return new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
	}

	public static void deleteDir(File dir) throws IOException {
		Files.walk(dir.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
	}

	private static void selfDestructWindowsJARFile() throws Exception {
		String currentJARFilePath = getCurrentJARFilePath().toString();
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c ping localhost -n 2 > nul && del \"" + currentJARFilePath + "\"");
	}

	public static void seldDestructionJar() throws Exception {
		if (!Config.SELF_DESTRUCT)
			return;

		if (OSTools.isOSWindows()) {
			selfDestructWindowsJARFile();
		} else {
			File directoryFilePath = getCurrentJARFilePath();
			Files.delete(directoryFilePath.toPath());
		}

		System.exit(0);
	}
}
