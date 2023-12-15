package com.rest.api.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class PostEntity {

  @Id
  @UuidGenerator
  private String id;

  @Column
  private String name;

  @Column
  private String description;

  public PostEntity() {}

  public PostEntity(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
