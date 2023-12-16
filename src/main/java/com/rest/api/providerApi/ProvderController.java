package com.rest.api.providerApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvderController {

  @Autowired
  private ProviderService providerService;

  @Async
  @GetMapping("/poke-get")
  public CompletableFuture<ResponseEntity<JsonNode>> getPokeApi()
    throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return CompletableFuture.completedFuture(
      ResponseEntity.ok(mapper.readTree(providerService.fetchPokeApi()))
    );
  }
}
