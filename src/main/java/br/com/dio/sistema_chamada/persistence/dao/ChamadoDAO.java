package br.com.dio.sistema_chamada.persistence.dao;

import br.com.dio.sistema_chamada.persistence.entity.Chamado;
import br.com.dio.sistema_chamada.persistence.entity.Status;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ChamadoDAO {
    private Connection connection;

    public Chamado insert(final Chamado chamado) throws SQLException {
        var sql = "INSERT INTO CHAMADOS (descricao, data_abertura, status, usuario_id) VALUES (?, ?, ?, ?);";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, chamado.getDescricao());
            statement.setObject(2, chamado.getDataAbertura());
            statement.setString(3, chamado.getStatus().name());
            statement.setLong(4, chamado.getUsuarioId());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chamado.setId(generatedKeys.getLong(1));
                }
            }
        }
        return chamado;
    }

    public Optional<Chamado> findById(final Long id) throws SQLException {
        var sql = "SELECT id, descricao, data_abertura, data_conclusao, status FROM CHAMADOS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Chamado chamado = mapResultSetToChamado(resultSet);
                return Optional.of(chamado);
            }
            return Optional.empty();
        }
    }

    public List<Chamado> findAll() throws SQLException {
        var sql = "SELECT id, descricao, data_abertura, data_conclusao, status FROM CHAMADOS;";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            List<Chamado> chamados = new ArrayList<>();
            while (resultSet.next()) {
                Chamado chamado = mapResultSetToChamado(resultSet);
                chamados.add(chamado);
            }
            return chamados;
        }
    }

    public void update(Chamado chamado) throws SQLException {
        var sql = "UPDATE CHAMADOS SET descricao = ?, status = ?, data_conclusao = ? WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, chamado.getDescricao());
            statement.setString(2, chamado.getStatus().name());
            statement.setObject(3, chamado.getDataConclusao());
            statement.setLong(4, chamado.getId());
            statement.executeUpdate();
        }
    }

    private Chamado mapResultSetToChamado(final java.sql.ResultSet resultSet) throws SQLException {
        Chamado chamado = new Chamado();
        chamado.setId(resultSet.getLong("id"));
        chamado.setDescricao(resultSet.getString("descricao"));
        chamado.setDataAbertura(resultSet.getTimestamp("data_abertura").toLocalDateTime());
        chamado.setDataConclusao(resultSet.getTimestamp("data_conclusao") != null ? resultSet.getTimestamp("data_conclusao").toLocalDateTime() : null);
        chamado.setStatus(Status.valueOf(resultSet.getString("status")));
        return chamado;
    }
}
