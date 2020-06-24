package platformbackend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ResponseEntity getMain() {
        return ResponseEntity.ok("Hello world");
    }
}
