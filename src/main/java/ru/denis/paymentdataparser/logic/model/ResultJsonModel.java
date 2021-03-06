package ru.denis.paymentdataparser.logic.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Модель для агрегирования результатов парсинга/конвертирования
 */
@Setter
@Getter
public class ResultJsonModel {
  Long id;
  BigDecimal amount;
  String comment;
  String filename;
  Long line;
  String result;
}
