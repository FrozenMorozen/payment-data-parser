package ru.denis.paymentdataparser.service;

import ru.denis.paymentdataparser.logic.model.ResultJsonModel;

public interface ResultJsonConverter<T extends BusinessModel> {

  ResultJsonModel convert(T t, String resultString);
}
