package ru.denis.paymentdataparser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.denis.paymentdataparser.logic.service.impl.BusinessLauncherImpl;
import ru.denis.paymentdataparser.service.BusinessLauncher;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BusinessLauncher businessLauncher = context.getBean("businessLauncher", BusinessLauncherImpl.class);;
//    String csvFileName = "inputData.csv";
//    String jsonFileName = "inputData.json";
    String csvFileName = args[0];
    String jsonFileName = args[1];
    businessLauncher.readInputData(csvFileName, jsonFileName)
            .forEach(System.out::println);
  }
}
