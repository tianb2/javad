package com.example.demo;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "hello %s";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "me") String name) {
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));

        greeting.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GreetingController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
