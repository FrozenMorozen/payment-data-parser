package ru.denis.paymentdataparser.service;

import ru.denis.paymentdataparser.logic.model.PaymentCsvModel;
import ru.denis.paymentdataparser.logic.model.PaymentJsonModel;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;

public interface ValidationServcie {

  String createResultStringForRecord(String orderId, String amount);

  ResultJsonModel getResultJsonForRecordFields(String[] recordFields);

}
