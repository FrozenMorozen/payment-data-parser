package ru.denis.paymentdataparser.service;

import ru.denis.paymentdataparser.logic.model.ResultJsonModel;

import java.util.List;

/**
 * Сервис для чтения данных о платежах
 */
public interface PaymentReaderService {

  /**
   * Считать все записи по платежам из файла
   * @param fileName  файл для чтения
   * @return результат считывания в формате json моделей
   */
  List<ResultJsonModel> readAllPaymentRecords(String fileName);
}
