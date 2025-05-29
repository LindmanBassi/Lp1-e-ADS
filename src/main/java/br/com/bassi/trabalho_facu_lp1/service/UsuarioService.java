package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Usuario;
import br.com.bassi.trabalho_facu_lp1.dto.UsuarioDTO;
import br.com.bassi.trabalho_facu_lp1.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void cadastrarUsuario(UsuarioDTO dto) {
        if (Boolean.TRUE.equals(dto.isFuncionario()) &&
                (dto.cargo() == null || dto.departamento() == null)) {
            throw new RuntimeException("Cargo e departamento são obrigatórios para funcionário.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setIsFuncionario(dto.isFuncionario());
        usuario.setCargo(dto.cargo());
        usuario.setDepartamento(dto.departamento());

        usuarioRepository.save(usuario);
    }
}






