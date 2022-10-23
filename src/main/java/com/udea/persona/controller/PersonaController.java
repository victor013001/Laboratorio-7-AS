package com.udea.persona.controller;

import com.udea.persona.exception.ModelNotFoundException;
import com.udea.persona.model.Persona;
import com.udea.persona.service.PersonaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
//La ruta
@RequestMapping("/persona")
@CrossOrigin("*")

public class PersonaController {

    @Autowired
    PersonaService personaService;

    @PostMapping("/save")
    //Post manda en body
    public long save(@RequestBody Persona person) {
        personaService.save(person);
        return person.getIdPerson();
    }

    @GetMapping("/listAll")
    public Iterable<Persona> listAllPersons() {
        return personaService.list();
    }

    @GetMapping("/list/{id}")
    //Get en query o params
    public Persona listPersonById(@PathVariable("id") int id) {
        Optional<Persona> person = personaService.listId(id);
        if (person.isPresent()) {
            return person.get();
        }

        throw new ModelNotFoundException("ID de persona invalido");
    }
}
