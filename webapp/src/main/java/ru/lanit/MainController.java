/*
package ru.lanit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.lanit.repository.Repository;
import ru.lanit.repository.dto.Address;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private Repository repo;

    @Autowired
    private PersonSummary person;

    @ModelAttribute("person")
    public PersonSummary getPerson() {
        return person;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/surname")
    public String surname(@RequestParam(value = "name", required = false) String name,
                          @ModelAttribute("person") PersonSummary person) {
        person.setName(name);
        return "surname";
    }

    @RequestMapping("/surname/patronymic")
    public String patronymic(@RequestParam(value = "surname", required = false) String surname,
                             @ModelAttribute("person") PersonSummary person) {
        person.setSurname(surname);
        return "patronymic";
    }

    @RequestMapping("/exit")
    public String exit(@RequestParam String patronymic,
                       @RequestParam String street,
                       @RequestParam String city,
                       Model model, @ModelAttribute("person") PersonSummary person) {

        person.setPatronymic(patronymic);
        person.setCity(city);
        person.setStreet(street);

        repo.save(person);
        List<Address> listOfAddresses = repo.getAllAddresses();

        model.addAttribute("listOfAddresses", listOfAddresses);

        return "exit";
    }

}
*/
