package ru.denis.paymentdataparser.logic.service.impl;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.denis.paymentdataparser.logic.SupportingFileExtensions;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;
import ru.denis.paymentdataparser.service.PaymentReaderService;
import ru.denis.paymentdataparser.service.ResultJsonService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.denis.paymentdataparser.logic.SupportingFileExtensions.valueOf;

@Service
@PropertySource(value = "classpath:business.properties")
public class PaymentReaderServiceImpl implements PaymentReaderService {

  private ResultJsonService resultJsonService;
  private Environment env;

  @Autowired
  public PaymentReaderServiceImpl(ResultJsonService resultJsonService, Environment env) {
    this.resultJsonService = resultJsonService;
    this.env = env;
  }

  /**
   * Получить расширение для имени файла
   */
  private SupportingFileExtensions getAllowFileExtension(String filename) {
    String fileExtension = Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".") + 1))
            .get();
    String allowFileExtensions = env.getRequiredProperty("allow.file.extensions");
    return !allowFileExtensions.isEmpty() ?
            valueOf(
            Arrays.stream(allowFileExtensions.split(" "))
            .filter((s) -> s.equalsIgnoreCase(fileExtension)).findAny().get().toUpperCase())
            : null;
  }

  @Override
  public List<ResultJsonModel> readAllPaymentRecords(String filename) {
    List<ResultJsonModel> resultJsonModels;
    SupportingFileExtensions fileExtension = getAllowFileExtension(filename);
    if (fileExtension == null) {
      return Collections.emptyList();
    }
    try {
      switch (fileExtension) {
        case CSV:
          resultJsonModels = resultJsonService.getResultJsonForCsvFile(filename);
          break;
        case JSON:
          resultJsonModels = resultJsonService.getResultJsonForJsonFile(filename);
          break;
        default:
          // Записать в экшен лог, что формат не поддерживается
          return Collections.emptyList();
      }
      resultJsonModels.forEach((model) -> model.setFilename(filename));
      return resultJsonModels;

    } catch (CsvValidationException e) {
      // Все ошибки отловить здесь и писать их в экшен лог
      return Collections.emptyList();
    } catch (IOException e) {
      // Все ошибки отловить здесь и писать их в экшен лог
      return Collections.emptyList();
    }
  }

}
