package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Funcionario;
import br.com.bassi.trabalho_facu_lp1.domain.Pessoa;
import br.com.bassi.trabalho_facu_lp1.infra.JwtUtil;
import br.com.bassi.trabalho_facu_lp1.repositories.FuncionarioRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final FuncionarioRepository funcionarioRepository;
    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public String autenticar(String email, String senha) {
        var funcionarioOpt = funcionarioRepository.findByEmail(email);
        if (funcionarioOpt.isPresent()) {
            Funcionario f = funcionarioOpt.get();
            if (!encoder.matches(senha, f.getSenha())) {
                throw new RuntimeException("Senha inválida");
            }
            return jwtUtil.gerarToken(f.getEmail());
        }

        var pessoaOpt = pessoaRepository.findByEmail(email);
        if (pessoaOpt.isPresent()) {
            Pessoa p = pessoaOpt.get();
            if (!encoder.matches(senha, p.getSenha())) {
                throw new RuntimeException("Senha inválida");
            }
            return jwtUtil.gerarToken(p.getEmail());
        }
        throw new RuntimeException("Usuário não encontrado");
    }
}
