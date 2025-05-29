package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Funcionario;
import br.com.bassi.trabalho_facu_lp1.domain.Pessoa;
import br.com.bassi.trabalho_facu_lp1.repositories.FuncionarioRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final FuncionarioRepository funcionarioRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        var funcionarioOpt = funcionarioRepository.findByEmail(email);
        if (funcionarioOpt.isPresent()) {
            Funcionario f = funcionarioOpt.get();
            return new User(f.getEmail(),f.getSenha(),Collections.emptyList());
        }

        var pessoaOpt = pessoaRepository.findByEmail(email);
        if (pessoaOpt.isPresent()) {
            Pessoa p = pessoaOpt.get();
            return new User(p.getEmail(),p.getSenha(),Collections.emptyList());
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + email);
    }
}
