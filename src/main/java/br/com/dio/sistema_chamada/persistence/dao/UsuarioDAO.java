package br.com.dio.sistema_chamada.persistence.dao;

import br.com.dio.sistema_chamada.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UsuarioDAO {
    private Connection connection;

    public Usuario insert(final Usuario usuario) throws SQLException {
        var sql = "INSERT INTO USUARIOS (nome, email) VALUES (?, ?);";
        try (var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                }
            }
        }
        return usuario;
    }

    public Optional<Usuario> findById(final Long id) throws SQLException {
        var sql = "SELECT id, nome, email FROM USUARIOS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usuario usuario = mapResultSetToUsuario(resultSet);
                return Optional.of(usuario);
            }
            return Optional.empty();
        }
    }

    public List<Usuario> findAll() throws SQLException {
        var sql = "SELECT id, nome, email FROM USUARIOS;";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                Usuario usuario = mapResultSetToUsuario(resultSet);
                usuarios.add(usuario);
            }
            return usuarios;
        }
    }

    private Usuario mapResultSetToUsuario(final java.sql.ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setEmail(resultSet.getString("email"));
        return usuario;
    }
}
