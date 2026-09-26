# Космические запуски

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
