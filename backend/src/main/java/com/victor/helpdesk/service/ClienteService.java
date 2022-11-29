package com.victor.helpdesk.service;

import com.victor.helpdesk.domain.Cliente;
import com.victor.helpdesk.domain.Pessoa;
import com.victor.helpdesk.domain.dto.ClienteDTO;
import com.victor.helpdesk.exception.DataIntegrityViolationException;
import com.victor.helpdesk.exception.NotFoundException;
import com.victor.helpdesk.repository.ClienteRepository;
import com.victor.helpdesk.repository.PessoaRepository;
import com.victor.helpdesk.util.MessagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException(MessagesUtils.NOT_FOUND_CLIENT + id));
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Cliente create(ClienteDTO objDto) {
        objDto.setId(null);
        objDto.setPassword(encoder.encode(objDto.getPassword()));
        validarEmailAndCpf(objDto);
        Cliente newObj = new Cliente(objDto);
        return repository.save(newObj);
    }

    @Transactional
    public Cliente update(Integer id, ClienteDTO dto){
        dto.setId(id);
        Cliente oldDto = findById(id);

        if (!(dto.getPassword().equals(oldDto.getPassword()))) {
            dto.setPassword(encoder.encode(dto.getPassword()));
        }

        validarEmailAndCpf(dto);

        oldDto = new Cliente(dto);
        return repository.save(oldDto);
    }


    public void delete(Integer id){
        repository.deleteById(id);
    }

    private void validarEmailAndCpf(ClienteDTO objDto) {

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
