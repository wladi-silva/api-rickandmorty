package externa.rickyandmorty.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import externa.rickyandmorty.response.CharacterResponse;
import reactor.core.publisher.Mono;

@Service
public class RickAndMortyClient {

    public final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id) {
        return  webClient
                .get()
                .uri("/character/" + id)
                .accept()
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros de entrada")))
                .bodyToMono(CharacterResponse.class);
                
                                
    }
}
