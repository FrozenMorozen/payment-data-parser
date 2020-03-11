package ru.denis.paymentdataparser.service;

import java.util.List;

/**
 * Сервис для запуска бизнес логики приложения
 */
public interface BusinessLauncher {

  /**
   * Считать данные о платежах из списка файлов
   * @param filenames список файлов
   * @return список строк, представляющих результат обработанных платежей из файлов
   */
  List<String> readInputData(String ... filenames);
}
