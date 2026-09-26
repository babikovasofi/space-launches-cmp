# Космические запуски — Compose Multiplatform

Каталог космических запусков: список запусков с фотографиями ракет, деталь запуска с миссией, переходы
к агентству и к стартовой площадке. Веха 1 «Каркас»: сети нет, данные — моки по форме
[Launch Library 2](https://ll.thespacedevs.com/2.2.0/swagger/) (22 запуска, 13 агентств, 20 площадок).

Ведущий таргет вехи — desktop. Зачётные таргеты — desktop и Android.

## Команды

```bash
./gradlew :desktopApp:run                        # desktop
./gradlew :desktopApp:run -Plocale=en            # desktop на второй локали
./gradlew :androidApp:assembleDebug              # Android
./gradlew :webApp:wasmJsBrowserDevelopmentRun    # web (бонус)
./gradlew :shared:linkDebugFrameworkIosSimulatorArm64   # iOS: сборка фреймворка (бонус)
python3 tools/check-strings.py                   # паритет ключей локалей
```

Отчёт компилятора Compose (стабильность типов и пропуск рекомпозиции) появляется после сборки в
`shared/build/compose_compiler/`.

## Структура

```
shared/src/commonMain/kotlin/ru/omgtu/babikova/spacelaunches/
  App.kt                    корень: тема, репозиторий, навигатор, фабрики ViewModel
  Screen.kt                 маршруты (sealed interface)
  domain/                   модели и интерфейс репозитория
  data/                     моки и реализация репозитория
  list/ detail/ agency/ pad/ состояние, намерения, ViewModel и фабрики экранов
  ui/AppTheme.kt            светлая и тёмная палитры — единственное место с цветами
  ui/model/                 UI-модели и форматирование
  ui/navigation/            Navigator и AppNavDisplay (Navigation 3)
  ui/screens/               вёрстка экранов
  ui/components/            переиспользуемые элементы
shared/src/commonMain/composeResources/
  values/ values-en/        подписи интерфейса
  drawable/                 фотографии ракет и иконки
tools/check-strings.py
docs/lab-1/                 отчёт
```

## Таблица приёмки

| # | Делаю | Вижу |
|---|---|---|
| 1 | `./gradlew :desktopApp:run`, скроллю список | 22 карточки, фото ракет на месте, у каждой статус, дата и агентство |
| 2 | прокручиваю список вниз, открываю карточку, жму «назад» | деталь въезжает справа, назад уезжает вправо; список остаётся на той же позиции |
| 3 | на детали жму агентство, потом площадку | открываются экраны агентства и площадки, «назад» ведёт на предыдущий экран, а не в список |
| 4 | жму иконку темы в шапке | темнеют шапка, фон и карточки; цвет статуса остаётся смысловым, подписи читаемы |
| 5 | `python3 tools/check-strings.py` | `values-en: 0 расхождений, ключей 20` |
| 6 | `./gradlew :desktopApp:run -Plocale=en` | интерфейс на английском, данные каталога остаются на языке источника |
| 7 | смотрю `shared/build/compose_compiler/*composables.txt` | экраны помечены `restartable skippable` |
