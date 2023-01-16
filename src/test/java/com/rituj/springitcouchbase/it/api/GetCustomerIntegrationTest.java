package com.rituj.springitcouchbase.it.api;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import com.google.gson.Gson;
import com.rituj.springitcouchbase.commons.AbstractIntegrationTest;
import com.rituj.springitcouchbase.commons.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.couchbase.DataCouchbaseTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class GetCustomerIntegrationTest extends AbstractIntegrationTest {

  private static final String BASE_URL = "/api/v1/customer";
  @Autowired private MockMvc mockMvc;

  @Test
  void test() throws Exception {

    final var filePath =
        "E:\\projects\\java-project\\spring-it-couchbase\\src\\test\\resources\\data\\customer.json";

    final var customerData = FileUtils.getObjectFromFile(filePath, Object.class);
    final var collection = createCollection();
    collection.insert("1", customerData);
    final var response =
        mockMvc
            .perform(MockMvcRequestBuilders.get(BASE_URL).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    final var data = response.getResponse().getContentAsString();
    System.out.printf("data from response: " + data);
  }
}
