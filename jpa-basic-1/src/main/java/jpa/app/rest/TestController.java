package jpa.app.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.security.Principal;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/auth")
    public String checkIsAuthenticated(Principal principal) {
        return principal != null ?
                "authenticated" : "not authenticated";
    }

    @GetMapping("/{value}")
    public String getPathVariable(@PathVariable Integer value) {

        return "got this -> " + value;
    }

    @GetMapping("/send")
    public String getRequestParam(@RequestParam("value") Integer value) {

        return "got this -> " + value;
    }

    @GetMapping("/header")
    public String getRequestHeader(@RequestHeader("user-agent") String value) {
        return "got this -> " + value;
    }

    @GetMapping("/cookie")
    public String getCookieValue(@CookieValue("sessionId") String cookie) {
        return "got this -> s" + cookie;
    }

    @PostMapping("/update")
    public String sendSomeData(@RequestBody String someData ) {
        return "got this -> " + someData;
    }
}
