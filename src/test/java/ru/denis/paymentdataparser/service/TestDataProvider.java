package ru.denis.paymentdataparser.service;

import org.springframework.mock.env.MockEnvironment;
import ru.denis.paymentdataparser.logic.service.impl.ValidationServiceImpl;

public class TestDataProvider {
  static MockEnvironment env = new MockEnvironment();
  final static String VALID_DATA_PROPERTY = "valid.data";
  final static String INVALID_DATA_PROPERTY = "invalid.data";
  final static String WRONG_ORDERID_PROPERTY = "wrong.orderId.format";
  final static String WRONG_AMOUNT_PROPERTY = "wrong.amount.format";

  static ValidationService validationService;

  static {
    env.setProperty(WRONG_AMOUNT_PROPERTY, "wrong.amount");
    env.setProperty(WRONG_ORDERID_PROPERTY, "wrong.orderId");
    env.setProperty(INVALID_DATA_PROPERTY, "invalid");
    env.setProperty(VALID_DATA_PROPERTY, "valid");
    validationService = new ValidationServiceImpl(env);
  }



}
