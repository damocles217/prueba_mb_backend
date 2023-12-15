package com.rest.api.providerApi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProviderService {

  private final WebClient fetch;

  public ProviderService(WebClient.Builder webClientBuilder) {
    this.fetch =
      webClientBuilder
        .baseUrl("https://pokeapi.co/api/v2/pokemon/ditto")
        .build();
  }

  public String fetchPokeApi() {
    return fetch.get().retrieve().bodyToMono(String.class).block();
  }
}
