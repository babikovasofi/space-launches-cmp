# Космические запуски

## Предметная область

Приложение показывает каталог космических запусков. У каждого запуска есть название, дата, статус, ракета и миссия с орбитой. Запуск проводит космическое агентство и выполняет со стартовой площадки, поэтому с экрана запуска можно перейти к агентству и к площадке.

Список, поиск, детали, агентства, площадки и фотографии загружаются из Launch Library 2. Список подгружается по 20 записей при прокрутке.

## Запуск

Перед сборкой укажите адрес тестового сервера Launch Library 2. Ключ для него не нужен.

```bash
export ORG_GRADLE_PROJECT_launchApiUrl=https://lldev.thespacedevs.com/2.2.0/
```

Desktop:

```bash
./gradlew :desktopApp:run
```

Desktop на второй локали:

```bash
./gradlew :desktopApp:run -Plocale=en
```

Android:

```bash
./gradlew :androidApp:assembleDebug
```

Web:

```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun
```

Проверка ключей локализации:

```bash
python3 tools/check-strings.py
```

Сборка Web/Wasm:

```bash
./gradlew :webApp:wasmJsBrowserDevelopmentWebpack
```

Отчёты компилятора Compose появляются в `shared/build/compose_compiler/`.
