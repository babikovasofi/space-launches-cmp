# Космические запуски

## Предметная область

Приложение показывает каталог космических запусков. У каждого запуска есть название, дата, статус, ракета и миссия с орбитой. Запуск проводит космическое агентство и выполняет со стартовой площадки, поэтому с экрана запуска можно перейти к агентству и к площадке.

Данные пока моковые: 22 запуска, 13 агентств и 20 площадок повторяют форму ответа API [Launch Library 2](https://ll.thespacedevs.com/2.2.0/).

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
