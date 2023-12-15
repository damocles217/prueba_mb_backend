package com.rest.api.user;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("UserFilter")
public class JwtDto {

  private String token;
  private UserEntity user;

  public JwtDto(String token, UserEntity user) {
    this.token = token;
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
