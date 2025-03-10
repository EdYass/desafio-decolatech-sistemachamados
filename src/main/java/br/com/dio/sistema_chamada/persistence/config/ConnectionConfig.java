package br.com.dio.sistema_chamada.persistence.config;

import static lombok.AccessLevel.PRIVATE   ;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = PRIVATE)
public final class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
        var url = "jdbc:mysql://localhost/sistemachamados";
        var user = "root";
        var password = "836363";
        var connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(true);
        return connection;
    }
}
