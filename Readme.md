## Параметры запуска для командной строки


###Пресеты через system.properties:

* `./gradle clean test -Denvironment=local_chrome`

* `./gradle clean test -Denvironment=local_safari`

* `./gradle clean test -Denvironment=remote_chrome`

* `./gradle clean test -Denvironment=remote_safari`


###Запуск с параметрами, пример:

`./gradle clean test -DbaseUrl=https://app.maxibooking.ru/ -DbrowserName=chrome`


###Доступные параметры: 

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


###После окончания тестов:

`./gradle allureServe` для отчетов.