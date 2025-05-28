package br.com.bassi.TrabalhoFaculLP1.e.A.service;


import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Funcionario;
import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Pessoa;

import br.com.bassi.TrabalhoFaculLP1.e.A.dto.PessoaDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.infra.ValidacaoException;
import br.com.bassi.TrabalhoFaculLP1.e.A.repositories.FuncionarioRepository;
import br.com.bassi.TrabalhoFaculLP1.e.A.repositories.PessoaRepository;
import br.com.bassi.TrabalhoFaculLP1.e.A.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void cadastrarPessoa(PessoaDTO dto) {
        if (Boolean.TRUE.equals(dto.isFuncionario())) {
            if (dto.cargo() == null || dto.departamento() == null) {
                throw new RuntimeException("Cargo e departamento são obrigatórios para funcionário.");
            }

            Funcionario f = new Funcionario();
            f.setNome(dto.nome());
            f.setEmail(dto.email());
            f.setSenha(passwordEncoder.encode(dto.senha()));
            f.setCargo(dto.cargo());
            f.setDepartamento(dto.departamento());

            funcionarioRepository.save(f);

        } else {
            Pessoa p = new Pessoa();
            p.setNome(dto.nome());
            p.setEmail(dto.email());
            p.setSenha(passwordEncoder.encode(dto.senha()));

            pessoaRepository.save(p);
        }
    }
}





