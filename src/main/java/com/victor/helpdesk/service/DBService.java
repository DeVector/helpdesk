package com.victor.helpdesk.service;

import com.victor.helpdesk.domain.Chamado;
import com.victor.helpdesk.domain.Cliente;
import com.victor.helpdesk.domain.Tecnico;
import com.victor.helpdesk.domain.enums.Perfil;
import com.victor.helpdesk.domain.enums.Prioridade;
import com.victor.helpdesk.domain.enums.Status;
import com.victor.helpdesk.repository.ChamadoRepository;
import com.victor.helpdesk.repository.ClienteRepository;
import com.victor.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instaciaDB(){
        Tecnico tecnic01 = new Tecnico("Luiz Bezerra", "52628197537", "bezerra@mail.com", encoder.encode("123"));
        tecnic01.addPerfis(Perfil.ADMIN);

        Cliente cliente01 = new Cliente("Thomas Oliveira", "02987122454", "oliveira@mail.com", encoder.encode("123"));

        Chamado c01 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tecnic01, cliente01);

        tecnicoRepository.saveAll(Arrays.asList(tecnic01));
        clienteRepository.saveAll(Arrays.asList(cliente01));
        chamadoRepository.saveAll(Arrays.asList(c01));
    }
}
