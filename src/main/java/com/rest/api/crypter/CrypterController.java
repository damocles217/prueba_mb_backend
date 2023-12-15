package com.rest.api.crypter;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix = "app")
public class CrypterController {

  private String secretKey = "secretKeyExampleNotForProduction";

  @PostMapping("/crypter")
  public ResponseEntity<ResponseDto> cryptAString(
    @RequestBody RequestDto request
  ) {
    try {
      String text = request.getText();
      String iv = "1234567891234567";

      SecretKey secretQuery = new SecretKeySpec(
        secretKey.getBytes("UTF-8"),
        "AES"
      );
      IvParameterSpec ivParameter = new IvParameterSpec(iv.getBytes("UTF-8"));

      Cipher chiper = Cipher.getInstance("AES/CBC/PKCS5Padding");

      chiper.init(Cipher.ENCRYPT_MODE, secretQuery, ivParameter);

      byte[] encryptedText = chiper.doFinal(text.getBytes("UTF-8"));

      String encryptedString = Base64
        .getEncoder()
        .encodeToString(encryptedText);

      return ResponseEntity.status(200).body(new ResponseDto(encryptedString));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new ResponseDto(e.getMessage()));
    }
  }
}
