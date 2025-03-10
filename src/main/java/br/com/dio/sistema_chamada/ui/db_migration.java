package br.com.dio.sistema_chamada.ui;

import br.com.dio.sistema_chamada.persistence.migration.MigrationStrategy;

import java.sql.SQLException;

import static br.com.dio.sistema_chamada.persistence.config.ConnectionConfig.getConnection;

public class db_migration {
    public static void main(String[] args) {
        try (var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
