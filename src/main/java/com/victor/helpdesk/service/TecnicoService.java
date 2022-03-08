package com.victor.helpdesk.service;

import com.victor.helpdesk.domain.Tecnico;
import com.victor.helpdesk.repository.TecnicoRepository;
import com.victor.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
}
