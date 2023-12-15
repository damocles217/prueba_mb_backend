package com.rest.api.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

  @Mock
  private JwtService jwtService;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private AuthController authController;

  @Test
  public void testRegisterUser() throws Exception {
    UserEntity userEntity = new UserEntity("test@test.com", "test");
    userEntity.setId("test");

    when(userRepository.save(userEntity)).thenReturn(userEntity);
    when(jwtService.createToken(userEntity)).thenReturn("token");

    ResponseEntity<MappingJacksonValue> responseEntity = authController.registeUser(
      userEntity
    );

    assertEquals(
      new ResponseEntity(HttpStatus.OK).getStatusCode(),
      responseEntity.getStatusCode()
    );
  }
}
