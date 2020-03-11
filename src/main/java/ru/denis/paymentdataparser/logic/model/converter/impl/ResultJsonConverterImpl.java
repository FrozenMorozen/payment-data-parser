package ru.denis.paymentdataparser.logic.model.converter.impl;

import org.springframework.stereotype.Service;
import ru.denis.paymentdataparser.logic.model.PaymentModel;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;
import ru.denis.paymentdataparser.logic.model.converter.ResultJsonConverter;

@Service
public class ResultJsonConverterImpl implements ResultJsonConverter<PaymentModel> {
  @Override
  public ResultJsonModel convert(PaymentModel businessModel, String resultString) {
    if (businessModel == null && resultString == null) {
      return null;
    }

    ResultJsonModel resultJsonModel = new ResultJsonModel();
    resultJsonModel.setResult(resultString);
    if (businessModel != null) {
      resultJsonModel.setId(businessModel.getOrderId());
      resultJsonModel.setAmount(businessModel.getAmount());
      resultJsonModel.setComment(businessModel.getComment());
    }
    return resultJsonModel;
  }
}
