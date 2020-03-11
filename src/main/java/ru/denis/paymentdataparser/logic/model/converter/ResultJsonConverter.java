package ru.denis.paymentdataparser.logic.model.converter;

import ru.denis.paymentdataparser.logic.model.BusinessModel;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;

public interface ResultJsonConverter<T extends BusinessModel> {

  ResultJsonModel convert(T t, String resultString);
}
