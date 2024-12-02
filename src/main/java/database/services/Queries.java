package database.services;

import entities.Pessoa;
import lombok.*;

import java.sql.*;

@NoArgsConstructor


@Getter
@Setter
public class Queries {
    private Statement statement = null;

    private Connection connection = null;

    public Queries(Connection conn) {

        this.setConnection(conn);

        try {

            this.setStatement(this.getConnection().createStatement());
        }catch (SQLException ex){

            System.out.println("Deu ruim: " + ex.getMessage());
        }
    }

    public void insertInto(String table, String columns, String[] values) throws SQLException {

        String execult = "INSERT INTO " + table + " (" + columns + ") VALUES (?, ?, ?, ?, ?) ";

        try(PreparedStatement preparedStatement = this.getConnection().prepareStatement(execult)){

            preparedStatement.setInt(1, Integer.parseInt(values[0]));

            preparedStatement.setString(2, values[1]);

            preparedStatement.setString(3, values[2]);

            preparedStatement.setString(4, values[3]);

            preparedStatement.setInt(5, Integer.parseInt(values[4]));

            int rowsAfected = preparedStatement.executeUpdate();

            System.out.println("Linhas afetadas: " + rowsAfected);
        }catch ( Exception e){

            System.out.println("Não passou no insert into" + e.getMessage());
        }
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
