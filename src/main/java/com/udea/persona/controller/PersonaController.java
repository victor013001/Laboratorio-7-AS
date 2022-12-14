package com.udea.persona.controller;

import com.udea.persona.exception.ModelNotFoundException;
import com.udea.persona.model.Persona;
import com.udea.persona.service.PersonaService;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public long save(@RequestBody Persona persona) {
        personaService.save(persona);
        return persona.getIdPerson();
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

    @PutMapping("/list/{id}/update")
    public Persona update(@PathVariable("id") int id, @RequestBody Persona persona) {
        Optional<Persona> p = personaService.listId(id);
        if (p.isPresent()) {
            persona.setIdPerson(p.get().getIdPerson());
            personaService.update(persona);
        }
        return persona;
    }

    @DeleteMapping("/list/{id}/delete")
    public void update(@PathVariable("id") int id) {
        Optional<Persona> persona = personaService.listId(id);
        if (persona.isPresent()) {
            personaService.delete(persona.get());
        }
    }

    @PatchMapping("/list/{id}/patch")
    public Persona patch(@PathVariable("id") int id) {
        Optional<Persona> persona = personaService.listId(id);
        if (persona.isPresent()) {
            Persona p = persona.get();
            int salario = (int) (p.getSalario() + (p.getSalario()*0.10)); 
            Date fechaActual = new Date();
            Date fechaIngreso = p.getFechaIngreso();
            long dias = (fechaActual.getTime() - fechaIngreso.getTime())/(1000 * 60 * 60 * 24);
            if (dias > 730) {
                p.setSalario(salario);
                personaService.patch(p);
            }

            return p;
        }

        throw new ModelNotFoundException("ID de persona invalido");
    }
}
