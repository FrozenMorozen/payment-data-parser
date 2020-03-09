package ru.denis.paymentdataparser.logic.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import ru.denis.paymentdataparser.service.BusinessModel;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentCsvModel implements BusinessModel {

  @CsvBindByName
  Long orderId;

  @CsvBindByName
  BigDecimal amount;

  @CsvBindByName
  String currency;

  @CsvBindByName
  String comment;

}
