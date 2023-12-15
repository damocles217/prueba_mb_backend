package com.rest.api.user;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Autowired
  JwtEncoder jwtEncoder;

  public String createToken(UserEntity user) {
    JwtClaimsSet claims = JwtClaimsSet
      .builder()
      .subject(user.getEmail())
      .claim("id", user.getId())
      .claim("email", user.getEmail())
      .expiresAt(Instant.now().plusSeconds(60 * 60 * 7))
      .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
