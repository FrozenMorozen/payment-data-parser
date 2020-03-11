package ru.denis.paymentdataparser.logic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.denis.paymentdataparser.service.ValidationService;

import java.math.BigDecimal;
import java.text.MessageFormat;

@Service
@PropertySource(value = "classpath:validation.properties", encoding = "UTF-8")
public class ValidationServiceImpl implements ValidationService {

  private Environment env;

  @Autowired
  public ValidationServiceImpl(Environment env) {
    this.env = env;
  }

  private String checkAmount(String amount) {
    try {
      new BigDecimal(amount);
    } catch (NumberFormatException nfe) {
      return MessageFormat.format(env.getRequiredProperty("wrong.amount.format"), amount);
    }
    return null;
  }

  private String checkOrderId(String orderId) {
    try {
      Long.parseLong(orderId);
    } catch (NumberFormatException nfe) {
      return MessageFormat.format(env.getRequiredProperty("wrong.orderId.format"), orderId);
    }
    return null;
  }

  @Override
  public String validateFields(String orderId, String amount) {
    StringBuilder stringBuilder = new StringBuilder();

    String orderIdValidation = checkOrderId(orderId);
    if (orderIdValidation != null) {
      stringBuilder.append(env.getProperty("invalid.data"))
              .append(" ").append(orderIdValidation);
    }

    String amountValidation = checkAmount(amount);
    if (amountValidation != null) {
      if (stringBuilder.toString().isEmpty()) {
        stringBuilder.append(env.getProperty("invalid.data"));
      }
      stringBuilder.append(" ").append(amountValidation);
    }

    return stringBuilder.toString().isEmpty() ? env.getProperty("valid.data") : stringBuilder.toString();
  }

  @Override
  public String getSuccessfullyString() {
    return env.getProperty("valid.data");
  }
}
