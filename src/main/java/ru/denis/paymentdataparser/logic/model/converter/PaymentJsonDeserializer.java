package ru.denis.paymentdataparser.logic.model.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PaymentJsonDeserializer extends StdDeserializer<String[]> {

  public PaymentJsonDeserializer() {
    this(null);
  }

  public PaymentJsonDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public String[] deserialize(JsonParser jp, DeserializationContext ctxt)
          throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    String[] nodes = new String[4];
    nodes[0] = node.get("orderId").asText();
    nodes[1] = node.get("amount").asText();
    nodes[2] = node.get("currency").asText();
    nodes[3] = node.get("comment").asText();
    return nodes;
  }
}
