package com.victor.helpdesk.service;

import com.victor.helpdesk.domain.Chamado;
import com.victor.helpdesk.domain.Cliente;
import com.victor.helpdesk.domain.Tecnico;
import com.victor.helpdesk.domain.dto.ChamadoDTO;
import com.victor.helpdesk.domain.enums.Prioridade;
import com.victor.helpdesk.domain.enums.Status;
import com.victor.helpdesk.exception.NotFoundException;
import com.victor.helpdesk.repository.ChamadoRepository;
import com.victor.helpdesk.util.MessagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    @Transactional(readOnly = true)
    public Chamado findById(Integer id){
        Optional<Chamado> optional = repository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException(MessagesUtils.NOT_FOUND_CALL));
    }

    @Transactional(readOnly = true)
    public List<Chamado> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Chamado create(ChamadoDTO dto){
        return repository.save(newChamado(dto));
    }

    @Transactional
    public Chamado update(Integer id, @Valid ChamadoDTO dto){
        dto.setId(id);
        Chamado oldDto = findById(id);
        oldDto = newChamado(dto);
        return repository.save(oldDto);
    }

    private Chamado newChamado(ChamadoDTO dto) {
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado();
        if (dto.getId() != null){
            chamado.setId(dto.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacao());

        return chamado;

    }
}
