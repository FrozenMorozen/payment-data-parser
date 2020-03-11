package ru.denis.paymentdataparser.logic.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.paymentdataparser.logic.model.PaymentModel;
import ru.denis.paymentdataparser.logic.model.ResultJsonModel;
import ru.denis.paymentdataparser.logic.model.converter.BusinessModelConverter;
import ru.denis.paymentdataparser.logic.model.converter.PaymentJsonDeserializer;
import ru.denis.paymentdataparser.logic.model.converter.ResultJsonConverter;
import ru.denis.paymentdataparser.service.ResultJsonService;
import ru.denis.paymentdataparser.service.ValidationService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultJsonServiceImpl implements ResultJsonService {

  private ValidationService validationService;
  private BusinessModelConverter<String[], PaymentModel> paymentConverter;
  private ResultJsonConverter<PaymentModel> resultJsonConverter;

  @Autowired
  public ResultJsonServiceImpl(BusinessModelConverter<String[], PaymentModel> paymentConverter,
                               ResultJsonConverter<PaymentModel> resultJsonConverter,
                               ValidationService validationService) {
    this.paymentConverter = paymentConverter;
    this.resultJsonConverter = resultJsonConverter;
    this.validationService = validationService;
  }

  private ResultJsonModel getResultJsonForRecordFields(String[] recordFields) {
    String orderId = recordFields[0];
    String amount = recordFields[1];
    String resultValidationString = validationService.validateFields(orderId, amount);

    PaymentModel csvModel = null;
    if (validationService.getSuccessfullyString().equals(resultValidationString)) {
      csvModel = paymentConverter.convert(recordFields);
    }
    return resultJsonConverter.convert(csvModel, resultValidationString);
  }

  @Override
  public List<ResultJsonModel> getResultJsonForCsvFile(String filename) throws IOException, CsvValidationException {
    List<ResultJsonModel> resultJsonModels = new ArrayList<>();
    CSVParser parser = new CSVParserBuilder()
            .withSeparator(',')
            .withIgnoreQuotations(true)
            .build();
    CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(filename))))
            .withSkipLines(1)
            .withCSVParser(parser)
            .build();

    String[] lineData;
    while ((lineData = csvReader.readNext()) != null) {
      ResultJsonModel resultJsonModel = getResultJsonForRecordFields(lineData);
//      resultJsonModel.setLine(); для заполнения нужно поддержать многопоточность
      resultJsonModels.add(resultJsonModel);
    }
    csvReader.close();
    return resultJsonModels;
  }

  /**
   *  Получить типизированный json для json файла
   */
  @Override
  public List<ResultJsonModel> getResultJsonForJsonFile(String filename) throws IOException {
    File file = new File(filename);

    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(String[].class, new PaymentJsonDeserializer());
    mapper.registerModule(module);
    List<String[]> recordFieldsFromFile = mapper.readValue(file, new TypeReference<List<String[]>>(){});

    return recordFieldsFromFile.stream()
            .map((s) -> getResultJsonForRecordFields(s))
            .collect(Collectors.toList());
  }
}
