package ru.lanit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainRestController {
    @Autowired
    Repository repo;

    @Autowired
    PersonSummary person;

    @GetMapping(value = "/",produces = "application/json")
    public List<PersonSummary> getAllPersonSummary() {
        return PersonSummary.toPersonSummary(repo.getAllPersons());
    }
}
