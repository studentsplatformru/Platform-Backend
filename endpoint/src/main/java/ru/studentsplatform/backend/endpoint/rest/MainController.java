package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.university.util.UniversityUtilTest;


@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ResponseEntity getMain() {
        return ResponseEntity.ok("Hello world");
    }
}
