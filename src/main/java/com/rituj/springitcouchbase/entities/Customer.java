package com.rituj.springitcouchbase.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Data
@Document
public class Customer {

  @Id
  public String id;
  public String custId;
  public String name;
  public Address address;
  public int rating;
}