package ru.denis.paymentdataparser.logic.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;
import ru.denis.paymentdataparser.service.BusinessLauncher;
import ru.denis.paymentdataparser.service.PaymentReaderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service("businessLauncher")
public class BusinessLauncherImpl implements BusinessLauncher {

  private PaymentReaderService paymentReaderService;

  @Autowired
  public BusinessLauncherImpl(PaymentReaderService paymentReaderService) {
    this.paymentReaderService = paymentReaderService;
  }

  public List<String> readInputData(String ... filenames) {
    List<ResultJsonModel> recordsFromAllFiles = new ArrayList<>();
    Arrays.stream(filenames)
            .forEach((s) -> recordsFromAllFiles.addAll(paymentReaderService.readAllPaymentRecords(s)));
    return recordsFromAllFiles
            .stream()
            .map((s) -> {
              try {
                return new ObjectMapper().writeValueAsString(s);
              } catch (JsonProcessingException ignored) {
                return null;
              }
            })
            .collect(Collectors.toList());
  }

}
