package ru.denis.paymentdataparser.logic.model.converter;

import ru.denis.paymentdataparser.logic.model.BusinessModel;

/**
 * Конвертер для обращения бизнес моделей приложения
 */
public interface BusinessModelConverter<R, T extends BusinessModel> {

  T convert(R source);
}
