# payment-data-parser
Приложение парсинга данных о платежках и конвертирования результата в итоговый формат

### Описание задания:
Необходимо разработать приложение парсинга входящих данных и конвертирования результат парсинга в результирующий формат.
1. Приложение должно быть реализовано с использованием фреймворка Spring.
2. Исходные код приложения должен быть оформлен в виде maven проекта и размещён на GitHub. Допускается использовать зависимости только из публичных репозиториев. 
3. Сборка конечного приложения должна быть выполнена командой: 
mvn clean install
4. Приложение должно быть консольным.\
Пример команды запуска: java -jar orders_parser.jar orders1.csv orders2.json\
где orders1.csv и orders2.json файлы для парсинга.
5. Результат выполнения должен выводиться в stdout поток.
Примечание: в stdout должны попасть только выходные данные, логов там быть не должно.
6. Парсинг и конвертирование должны выполняться параллельно в несколько потоков.
7. Необходимо предусмотреть корректную обработку ошибок в исходных файлах.\
Например, вместо числа в файле может быть строковое значение в поле amount.
8. Разрешается использовать инструменты языка не выше Java 8.
9. Необходимо учесть возможность добавления новых форматов входящих данных. Например: XLSX