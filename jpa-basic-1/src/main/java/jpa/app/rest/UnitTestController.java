package jpa.app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unit-test")
public class UnitTestController {

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }

}
