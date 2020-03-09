package ru.denis.paymentdataparser.logic;

import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Модель для агрегирования результатов парсинга/конвертирования
 */
@Setter
@ToString
public class ResultJsonModel {

  Long id;
  BigDecimal amount;
  String comment;
  String filename;
  Long line;
  String result;

}
