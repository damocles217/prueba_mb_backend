package com.rest.api.user;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final JwtService jwtService;

  public AuthController(UserRepository userRepository, JwtService jwtService) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  public ResponseEntity<MappingJacksonValue> registeUser(
    @RequestBody UserEntity entity
  ) {
    UserEntity user = userRepository.save(entity);

    JwtDto jwt = new JwtDto(jwtService.createToken(user), user);
    MappingJacksonValue maps = new MappingJacksonValue(jwt);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(
      "user",
      "token"
    );

    SimpleFilterProvider filters = new SimpleFilterProvider()
      .addFilter("UserFilter", filter);

    maps.setFilters(filters);

    return ResponseEntity.ok(maps);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> loginUser(@RequestBody UserEntity entity) {
    UserEntity user = userRepository.findByEmail(entity.getEmail());

    if (user == null) {
      return ResponseEntity.badRequest().body("Invalid email/password");
    }

    if (!user.getPassword().equals(entity.getPassword())) {
      return ResponseEntity.badRequest().body("Invalid email/password");
    }

    JwtDto jwt = new JwtDto(jwtService.createToken(user), user);
    MappingJacksonValue maps = new MappingJacksonValue(jwt);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(
      "user",
      "token"
    );

    SimpleFilterProvider filters = new SimpleFilterProvider()
      .addFilter("UserFilter", filter);

    maps.setFilters(filters);

    return ResponseEntity.ok(maps);
  }
}
