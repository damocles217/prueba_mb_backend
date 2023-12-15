package com.rest.api.crypter;

public class ResponseDto {

  private String cipherText;

  public ResponseDto() {}

  public ResponseDto(String cipherText) {
    this.cipherText = cipherText;
  }

  public String getCipherText() {
    return this.cipherText;
  }

  public void setCipherText(String cipherText) {
    this.cipherText = cipherText;
  }
}
