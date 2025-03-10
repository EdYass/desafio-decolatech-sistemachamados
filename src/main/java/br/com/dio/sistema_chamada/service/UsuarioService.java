package br.com.dio.sistema_chamada.service;

import br.com.dio.sistema_chamada.persistence.dao.UsuarioDAO;
import br.com.dio.sistema_chamada.persistence.entity.Usuario;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public Usuario criarUsuario(String nome, String email) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        return usuarioDAO.insert(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) throws SQLException {
        return usuarioDAO.findById(id);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.findAll();
    }
}
