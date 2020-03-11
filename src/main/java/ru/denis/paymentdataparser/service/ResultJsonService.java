package ru.denis.paymentdataparser.service;

import com.opencsv.exceptions.CsvValidationException;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;

import java.io.IOException;
import java.util.List;

/**
 * Сервис для агрегирования данными о платежах в json формат
 */
public interface ResultJsonService {

  /**
   * Получить агрегированный json для csv файла
   */
  List<ResultJsonModel> getResultJsonForCsvFile(String filename) throws IOException, CsvValidationException;

  /**
   * Получить агрегированный json для json файла
   */
  List<ResultJsonModel> getResultJsonForJsonFile(String filename) throws IOException;
}
