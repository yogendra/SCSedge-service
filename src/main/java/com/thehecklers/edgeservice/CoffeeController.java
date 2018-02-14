package com.thehecklers.edgeservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
@RequestMapping("/")
public class CoffeeController {
    private final RestOperations restOperations;

    @Value("${defaultcoffee:'Plain Joe'}")
    private String defaultCoffee;

    public CoffeeController(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @GetMapping
    String hi() {
        return "Greetings from your friendly neighborhood edge service!";
    }

    @HystrixCommand(fallbackMethod = "getDefaultCoffee")
    @GetMapping("/surpriseme")
    Coffee getRandomCoffee() {
        return restOperations.getForObject("http://coffee-service/coffees/random", Coffee.class);
    }

    Coffee getDefaultCoffee() {
        return new Coffee(defaultCoffee, "House choice");
    }

}
