package com.rituj.springitcouchbase.commons;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public abstract class AbstractIntegrationTest {

  private static final String COUCHBASE_IMAGE_NAME = "couchbase";
  private static final String DEFAULT_IMAGE_NAME = "couchbase/server";

  private static Cluster cluster;

  private static final DockerImageName DEFAULT_IMAGE =
      DockerImageName.parse(COUCHBASE_IMAGE_NAME).asCompatibleSubstituteFor(DEFAULT_IMAGE_NAME);

  public static final CouchbaseContainer couchbaseContainer =
      new CouchbaseContainer(DEFAULT_IMAGE);

  static {
    couchbaseContainer
        .withCredentials("Administrator", "password")
        .withBucket(new BucketDefinition("DBTest").withPrimaryIndex(true))
        .withStartupTimeout(Duration.ofSeconds(60));
    couchbaseContainer.start();
    createCollection();
  }

  public static Collection createCollection() {
    cluster =
            Cluster.connect(
                    couchbaseContainer.getConnectionString(),
                    couchbaseContainer.getUsername(),
                    couchbaseContainer.getPassword());
    final var bucket = cluster.bucket("DBTest");
    return bucket.defaultCollection();
  }

  @DynamicPropertySource
  static void bindCouchbaseProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.couchbase.connection-string", couchbaseContainer::getConnectionString);
    registry.add("spring.couchbase.username", couchbaseContainer::getUsername);
    registry.add("spring.couchbase.password", couchbaseContainer::getPassword);
    registry.add("spring.data.couchbase.bucket-name", () -> "DBTest");
  }


}
