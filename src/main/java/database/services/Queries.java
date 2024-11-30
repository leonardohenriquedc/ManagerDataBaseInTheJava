package database.services;

import entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class Queries {
    private Statement statement = null;

    public void insertInto(String table, String columns, String values) throws SQLException {

        String execult = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + " )";

        int rowsAfected = this.getStatement().executeUpdate(execult);

        System.out.println("Linhas afetadas: " + rowsAfected);
    }

    public void deleteFrom (String table, String condition) throws SQLException {

        String execult = "DELETE FROM " + table + " WHERE " + condition;

        int rowsAfected = this.getStatement().executeUpdate(execult);

        System.out.println("Linhas afetadas: " + rowsAfected);
    }

    public void selectFrom(String conditionOfSelect, String table) throws SQLException {

        String execult = "SELECT " + conditionOfSelect + " FROM " + table;

        ResultSet resultSet = statement.executeQuery(execult);

        while (resultSet.next()){

            Pessoa pessoa = new Pessoa();

            pessoa.setId(resultSet.getInt("id"));

            pessoa.setNome(resultSet.getString("nome"));

            pessoa.setEmail(resultSet.getString("email"));

            pessoa.setCpf(resultSet.getString("cpf"));

            pessoa.setIdade(resultSet.getInt("idade"));

            System.out.println(pessoa);
        }
    }
}
