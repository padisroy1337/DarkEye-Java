package org.spray.darkeye.utils;

import java.util.Locale;

public class OSTools {

	public enum OSType {
		Windows, MacOS, Linux, Other
	};

	private static OSType detectedOS;

	public static OSType getType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
				detectedOS = OSType.MacOS;
			} else if (OS.indexOf("win") >= 0) {
				detectedOS = OSType.Windows;
			} else if (OS.indexOf("nux") >= 0) {
				detectedOS = OSType.Linux;
			} else {
				detectedOS = OSType.Other;
			}
		}
		return detectedOS;
	}

	public static boolean isOSWindows() {
		return getType().equals(OSType.Windows);
	}

	public static boolean isOSMac() {
		return getType().equals(OSType.MacOS);
	}

	public static boolean isOSLinux() {
		return getType().equals(OSType.Linux);
	}

	public static boolean is64Bit() {
		boolean is64bit;
		if (isOSWindows()) {
			is64bit = (System.getenv("ProgramFiles(x86)") != null);
		} else {
			is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
		}
		return is64bit;
	}

}
