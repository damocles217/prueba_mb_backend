package com.rest.api.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class UserEntity {

  @Id
  @UuidGenerator
  private String id;

  @Column
  private String email;

  @Column
  private String password;

  public UserEntity() {}

  public UserEntity(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
