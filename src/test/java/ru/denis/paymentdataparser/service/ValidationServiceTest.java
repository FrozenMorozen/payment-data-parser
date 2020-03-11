package ru.denis.paymentdataparser.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.denis.paymentdataparser.service.TestDataProvider.*;

class ValidationServiceTest {

  @ParameterizedTest
  @MethodSource("correctOrderIdAndAmountDataProvider")
  void validateCorrectFields(String orderId, String amount) {
    assertEquals(validationService.validateFields(orderId, amount), env.getProperty(VALID_DATA_PROPERTY));
  }

  @ParameterizedTest
  @MethodSource("wrongOrderIdAndAmountDataProvider")
  void validateWrongFields(String orderId, String amount, String expectedResult) {
    assertEquals(validationService.validateFields(orderId, amount), expectedResult);
  }

  @Test
  void getSuccessfullyString() {
    assertEquals(validationService.getSuccessfullyString(), env.getProperty(VALID_DATA_PROPERTY));
  }

  private static Stream<Arguments> correctOrderIdAndAmountDataProvider() {
    return Stream.of(
            Arguments.of("123", "87.12"),
            Arguments.of("9", "555.28")
    );
  }

  private static Stream<Arguments> wrongOrderIdAndAmountDataProvider() {
    return Stream.of(
            Arguments.of("null", "null",
                    env.getProperty(INVALID_DATA_PROPERTY) + " " +
                            env.getProperty(WRONG_ORDERID_PROPERTY) + " " +
                            env.getProperty(WRONG_AMOUNT_PROPERTY)),
            Arguments.of("9", "/",
                    env.getProperty(INVALID_DATA_PROPERTY) + " " +
                            env.getProperty(WRONG_AMOUNT_PROPERTY)),
            Arguments.of("asa", "78",
                    env.getProperty(INVALID_DATA_PROPERTY) + " " +
                            env.getProperty(WRONG_ORDERID_PROPERTY))
    );
  }
}