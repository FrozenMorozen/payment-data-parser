package ru.denis.paymentdataparser.logic.model.converter.impl;

import org.springframework.stereotype.Service;
import ru.denis.paymentdataparser.logic.model.PaymentModel;
import ru.denis.paymentdataparser.logic.model.converter.BusinessModelConverter;

import java.math.BigDecimal;

@Service
public class SimpleConverterImpl implements BusinessModelConverter<String[], PaymentModel> {

  @Override
  public PaymentModel convert(String[] source) {
    if (source == null) {
      return null;
    }
    PaymentModel paymentModel = new PaymentModel();
    paymentModel.setOrderId(Long.valueOf(source[0]));
    paymentModel.setAmount(new BigDecimal(source[1]));
    paymentModel.setCurrency(source[2]);
    paymentModel.setComment(source[3]);
    return paymentModel;
  }
}
