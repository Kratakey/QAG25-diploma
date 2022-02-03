## Параметры запуска для командной строки


##Пресеты через system.properties:

* `./gradle clean test -Denvironment=local`

* `./gradle clean test -Denvironment=remote`


##Запуск с параметрами, пример:

`./gradle clean test -DbaseUrl=https://app.maxibooking.ru/ -DbrowserName=chrome`

##Задачи:
* `test` - запуск по-умолчанию
* `desktop` - запуск desktop тестов с web-драйвером
* `local` - запуск mobile тестов локально на реальном устройстве
* `emulation` - запуск mobile тестов локально на VM
* `selenoid` - запуск mobile удаленно в Selenoid
* `browserstack` - запуск mobile удаленно в Browserstack

##Доступные параметры для (использовать только для desktop запуска):

* `browserName` - название браузера, default: `chrome` 

* `browserVersion` - версия браузера; игнорируется, если не указана

* `browserSize` - размер окна браузера, default: `1920x1080`

* `headless` - headless режим браузера, default: `false`

* `timeout` - максимальная задержка до падения теста, default: `10000` ms

* `baseUrl` - основной url тестируемого сайта, default: `https://app.maxibooking.ru/`

* `remote` - адрес удаленного сервера Selenide; игнорируется, если оставить пустым

* `enableVNC` - нужно для записи видео тестов //todo, default: `true`

* `enableVideo` - нужно для записи видео тестов //todo, default: `true`

браузеры: `chrome`, `safari`, `firefox`, `legacy_firefox`, `ie`, `opera`, `edge`.
