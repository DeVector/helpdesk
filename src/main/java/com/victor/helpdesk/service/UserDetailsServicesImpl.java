package com.victor.helpdesk.service;

import com.victor.helpdesk.domain.Pessoa;
import com.victor.helpdesk.repository.PessoaRepository;
import com.victor.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> optional = repository.findByEmail(email);
        if (optional.isPresent()){
            return new UserSS(optional.get().getId(), optional.get().getEmail(), optional.get().getPassword(), optional.get().getPerfis());
        }
        throw new UsernameNotFoundException(email);
    }
}
