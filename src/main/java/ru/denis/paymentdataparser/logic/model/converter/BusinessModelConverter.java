package ru.denis.paymentdataparser.service;

public interface BusinessModelConverter<R, T extends BusinessModel> {

  T convert(R source);
}
