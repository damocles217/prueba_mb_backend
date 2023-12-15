package com.rest.api.crypter;

public class RequestDto {

  private String text;

  public RequestDto() {}

  public RequestDto(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
