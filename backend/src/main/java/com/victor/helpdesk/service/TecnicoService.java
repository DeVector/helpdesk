package com.victor.helpdesk.service;

import com.victor.helpdesk.domain.Pessoa;
import com.victor.helpdesk.domain.Tecnico;
import com.victor.helpdesk.domain.dto.TecnicoDTO;
import com.victor.helpdesk.exception.DataIntegrityViolationException;
import com.victor.helpdesk.exception.NotFoundException;
import com.victor.helpdesk.repository.PessoaRepository;
import com.victor.helpdesk.repository.TecnicoRepository;
import com.victor.helpdesk.util.MessagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException(MessagesUtils.NOT_FOUND_TECHNICIAN + id));
    }

    @Transactional(readOnly = true)
    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null);
        objDto.setPassword(encoder.encode(objDto.getPassword()));
        objDto.setLocalDate(LocalDate.now());
        validarEmailAndCpf(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }

    @Transactional
    public Tecnico update(Integer id, TecnicoDTO dto){
        dto.setId(id);
        Tecnico oldDto = findById(id);

        if (!(dto.getPassword().equals(oldDto.getPassword()))) {
            dto.setPassword(encoder.encode(dto.getPassword()));
        }

        validarEmailAndCpf(dto);

        oldDto = new Tecnico(dto);
        return repository.save(oldDto);
    }


    public void delete(Integer id){
        repository.deleteById(id);
    }

    private void validarEmailAndCpf(TecnicoDTO objDto) {

        Optional<Pessoa> optional = pessoaRepository.findByCpf(objDto.getCpf());
        if (optional.isPresent() && optional.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException(MessagesUtils.CPF_EXIST);
        }

        optional = pessoaRepository.findByEmail(objDto.getEmail());
        if (optional.isPresent() && optional.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException(MessagesUtils.EMAIL_EXIST);
        }

    }
}
