package services;

import database.DatabasePreparingAndEstablishingConnection;
import database.services.Queries;
import entities.Pessoa;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainClass {

    public static DatabasePreparingAndEstablishingConnection dbpe = new DatabasePreparingAndEstablishingConnection();

    public static Statement st = dbpe.createConnectionAndGetStatement();

    public static Queries queries = new Queries(st);

    public static void main(String[] args) throws Exception {

        addPeoplesInDataBase();

        testMethodSelect();

    }

    public static void addPeoplesInDataBase() throws Exception {
        List<Pessoa> pessoas = new ReadFiles().readFiles("pessoas.json");

        String table = "pessoas";

        String columns = "id, nome, email, cpf, idade";

        pessoas.stream().forEach(p -> {
            String values = Integer.toString(p.getId()) + ", "
                    + " ' " + p.getNome() + " ' , "
                    + " ' " + p.getEmail() + " ' , "
                    + " ' " + p.getCpf() + " ' , "
                    + Integer.toString(p.getIdade());

            try {

                queries.insertInto(table, columns, values);

            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        });
    }

    public static void testMethodReadFiles() throws Exception {

        List<Pessoa> pessoas = new ReadFiles().readFiles("pessoas.json");

        pessoas.stream().forEach(p -> System.out.println(p));
    }

    public static void testMethodInsertInto() throws SQLException {

        String table = "pessoas";

        String columns = "id, nome, email, cpf, idade";

        String values = "1, 'Leonardo', 'leohencosta18@gmail.com', '34462624', 20";

        queries.insertInto(table, columns, values);
    }

    public static void testMethodDeleteFrom() throws SQLException {

        String table = "pessoas";

        String condition = "id = 1";

        queries.deleteFrom(table, condition);
    }

    public static void testMethodSelect() throws SQLException {

        String conditionOfSelect = " * ";

        String table = "pessoas";

        queries.selectFrom(conditionOfSelect, table);
    }
}
