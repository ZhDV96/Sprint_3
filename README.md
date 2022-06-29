Финальный проект третьего спринта 
====
## Описание проекта
Финальный проект 3 спринта курса "Автоматизатор тестирования на Java". В проекте реализованы автотесты для API учебного сервиса Яндекс.Самокат. Его документация: qa-scooter.praktikum-services.ru/docs/.

## Перед началом работы
Необходимо, чтобы были уставновлены:
•	IntelliJ IDEA + Maven 
•	Git Bash
### Переключитесь на ветку develop.
### Развернуть Jenkins внутри докер-контейнера
1.	Перейдите в репозиторий с заготовкой кода: https://github.com/ZhDV96/Sprint_3.git
2.	Нажмите кнопку Fork для клонирования репозитория— она на панели справа и сверху. Появится выпадающий список. Выберите свой аккаунт на GitHub.
3.	Откройте терминал(Git Bash) и перейдите в папку проекта, которую удалось создать на компьютере. Для этого понадобится команда cd.
4.	Используя команду git clone https://github.com/<username>/Sprint_3.git в терминале, скачайте себе данный репозиторий. Укажите имя своего аккаунта на GitHub вместо <username>.
### Отчёт с Allure.
1.	В IntelliJ IDEA с подключенным Maven апустите выполнение автотестов командой mvn clean test.
2.	В консоли IntelliJ IDEA перейдите в папку проекта и выполните команду allure serve target/surefire-reports/. Allure отобразит отчёт, который сгенерировал плагин maven-surefire-plugin. Откроется окно браузера с отчётом.
