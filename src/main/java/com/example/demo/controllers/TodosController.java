package com.example.demo.controllers;

import com.example.demo.models.Todo;
import com.example.demo.services.TodosService;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

@RestController
public class TodosController {
    private final TodosService service;

    @Autowired
    public TodosController(TodosService service) {
        this.service = service;
    }

    @GetMapping(value = "/todos/{id}", produces = "application/json")
    public ResponseEntity<Todo> getBook(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.getTodos(id));
        } catch (ResourceAccessException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE_503).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
