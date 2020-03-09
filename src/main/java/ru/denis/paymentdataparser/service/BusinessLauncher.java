package ru.denis.paymentdataparser.service;


import org.springframework.stereotype.Service;

import java.util.List;

public interface BisinessLauncher {
  List<String> handleInputData(String csvFilename, String jsonFileName) throws Exception;
}
