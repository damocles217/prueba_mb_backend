package com.rest.api.providerApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvderController {

  @Autowired
  private ProviderService providerService;

  @GetMapping("/poke-get")
  public ResponseEntity<JsonNode> getPokeApi() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return ResponseEntity.ok(mapper.readTree(providerService.fetchPokeApi()));
  }
}
