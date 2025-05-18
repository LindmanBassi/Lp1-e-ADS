package br.com.bassi.TrabalhoFaculLP1.e.A.service;


import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Funcionario;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.FuncionarioDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.repositories.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

        public Funcionario cadastrarFuncionario(FuncionarioDTO dto) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(dto.nome()); // método do record
            funcionario.setEmail(dto.email());
            funcionario.setSenha(passwordEncoder.encode(dto.senha())); // criptografa a senha
            funcionario.setDepartamento(dto.departamento());
            funcionario.setCargo(dto.cargo());

            return funcionarioRepository.save(funcionario);
        }

        public Funcionario buscarPorEmail(String email) {
            return funcionarioRepository.findByEmail(email).orElse(null);
        }
    }



