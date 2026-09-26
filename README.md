# Космические запуски

Каталог космических запусков на Compose Multiplatform: список, деталь запуска, экраны агентства
и стартовой площадки. Веха 1 — сети нет, данные моковые.

## Запуск

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
