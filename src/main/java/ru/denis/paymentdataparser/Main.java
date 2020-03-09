package ru.denis.paymentdataparser;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.denis.paymentdataparser.logic.service.impl.BusinessLauncherImpl;
import ru.denis.paymentdataparser.service.BusinessLauncher;

public class Main {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "application.xml"
    );
    BusinessLauncher businessLauncher = context.getBean("businessLauncher", BusinessLauncherImpl.class);;

    String csvFileName = "inputData.csv";
    String jsonFileName = "inputData.json";
    try {
      businessLauncher.readInputData(csvFileName, jsonFileName).forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
