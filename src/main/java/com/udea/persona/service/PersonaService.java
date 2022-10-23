package com.udea.persona.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.persona.dao.IPersonaDAO;
import com.udea.persona.model.Persona;

@Service
public class PersonaService {
    @Autowired
    //Ya tiene todos los datos gracias a la notacion
    private IPersonaDAO dao;
    
    public Persona save(Persona p) {return dao.save(p);}
    public Persona update(Persona p) {return dao.save(p);}
    public void delete(Persona p) {dao.delete(p);}
    public Persona patch(Persona p) {return dao.save(p);}
    public Iterable<Persona> list() {return dao.findAll();}
    public Optional<Persona> listId(long id){return dao.findById(id);}
}
