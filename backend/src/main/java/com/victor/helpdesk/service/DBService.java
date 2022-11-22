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
        Tecnico tecnic01 = new Tecnico("Luiz Bezerra", "52628197537", "luiz@mail.com", encoder.encode("123"));
        tecnic01.addPerfis(Perfil.ADMIN);
        Tecnico tc2 = new Tecnico("Tim Benners", "38714535793", "tim@mail.com", encoder.encode("123"));
        Tecnico tc3 = new Tecnico("Donald Trump", "85257362520", "donald@mail.com", encoder.encode("123"));
        Tecnico tc4 = new Tecnico("Emilio Surita", "63232548164", "emilio@mail.com", encoder.encode("123"));
        Tecnico tc5 = new Tecnico("Loren Diniz", "37661639572", "loren@mail.com", encoder.encode("123"));
        tc5.addPerfis(Perfil.ADMIN);

        Cliente cliente01 = new Cliente("Thomas Oliveira", "02987122454", "thomas@mail.com", encoder.encode("123"));
        Cliente cl2 = new Cliente("Tobby Diniz", "11587400260", "tobby@mail.com", encoder.encode("123"));
        Cliente cl3 = new Cliente("Alan Torvalds", "77539744839", "alan@mail.com", encoder.encode("123"));
        Cliente cl4 = new Cliente("Wanessa Souza", "06652693070", "wanessa@mail.com", encoder.encode("123"));
        Cliente cl5 = new Cliente("Antonio de Padua", "26105124651", "antonio@mail.com", encoder.encode("123"));

        Chamado c01 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tecnic01, cliente01);
        Chamado ch2 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Display", "Trocar o display do Notebook", tc2, cl3);
        Chamado ch3 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Notebook", "Formatar notebook", tc3, cl2);


        tecnicoRepository.saveAll(Arrays.asList(tecnic01, tc2, tc3, tc4, tc5));
        clienteRepository.saveAll(Arrays.asList(cliente01, cl2, cl3, cl4, cl5));
        chamadoRepository.saveAll(Arrays.asList(c01, ch2, ch3));

    }
}
