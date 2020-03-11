package ru.denis.paymentdataparser.service;

/**
 * Сервис валидации
 */
public interface ValidationService {

  /**
   * Проверить поля
   * @param orderId идентификатор ордера
   * @param amount  сумма
   * @return В случае, если найдены невалидные поля - информация об этом. Иначе: "ОК"
   */
  String validateFields(String orderId, String amount);

  /**
   * Получить строку, определяющую информацию об успешной валидации.
   * Такой метод нужен, чтоб ограничить доступность конфигов валидации в одном сервисе
   */
  String getSuccessfullyString();

}
