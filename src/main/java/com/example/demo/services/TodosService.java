package com.example.demo.services;

import com.example.demo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TodosService {
    private RestTemplate restTemplate;
    private String serviceUrl;

    @Autowired
    public TodosService(
            @Qualifier("apiRestTemplate") RestTemplate restTemplate,
            @Value("${todos.service.url}") String serviceUrl
    ) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public Todo getTodos(String id) {
        String uri = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/todos").path("/" + id).toUriString();

        ResponseEntity<Todo> result = this.restTemplate.getForEntity(uri, Todo.class);

        if (result.hasBody() && result.getBody().id == null) return null;

        return result.getBody();
    }
}
