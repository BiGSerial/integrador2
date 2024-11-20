package components;

import java.sql.*;

public class MyConnectionDB {
    private String url = "jdbc:mysql://localhost:3307/meu_banco";
    private String user = "root";
    private String password = "";
    private Connection connection;


    public MyConnectionDB() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida com o banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }

    }
    public String  selectAllEspecialidades() throws SQLException {
        connection = getConnection();

        String sql = "SELECT * from especialidades";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder resultBuilder = new StringBuilder();

        while (resultSet.next()) {
            resultBuilder.append("ID: ").append(resultSet.getInt("idEspecialidades"))
                    .append(", Especialidades: ").append(resultSet.getString("Especialidades"))
                    .append("\n");
        }

        closeConnection();
        return resultBuilder.toString();
    }
    public String  selectAllPaciente() throws SQLException {
        connection = getConnection();

        String sql = "SELECT * from paciente";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder resultBuilder = new StringBuilder();

        while (resultSet.next()) {
            resultBuilder.append("ID: ").append(resultSet.getInt("idPaciente"))
                    .append(", Paciente: ").append(resultSet.getString("NomePaciente"))
                    .append(" ").append(resultSet.getString("SobrenomePaciente"))
                    .append(", Idade: ").append(resultSet.getString("Idade"))
                    .append(", CPF: ").append(resultSet.getString("CPF"))
                    .append("\n");
        }

        closeConnection();
        return resultBuilder.toString();
    }
    public String  selectAllAgenda() throws SQLException {
        connection = getConnection();

        String sql = "SELECT * from agenda";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder resultBuilder = new StringBuilder();

        while (resultSet.next()) {
            resultBuilder.append("ID: ").append(resultSet.getInt("idAgenda"))
                    .append(", Data: ").append(resultSet.getString("DataDisponivel"))
                    .append("\n");
        }

        closeConnection();
        return resultBuilder.toString();
    }
    public String  selectAllPerguntas() throws SQLException {
        connection = getConnection();

        String sql = "SELECT * from perguntas";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder resultBuilder = new StringBuilder();

        while (resultSet.next()) {
            resultBuilder.append("Pergunta: ").append(resultSet.getString("tipoPergunta")) .append("\n")
                    .append("Alternativa 1: ").append(resultSet.getString("Alternativa1")).append("\n")
                    .append("Alternativa 2: ").append(resultSet.getString("Alternativa2")).append("\n")
                    .append("Alternativa 3: ").append(resultSet.getString("Alternativa3")).append("\n")
                    .append("Alternativa 4: ").append(resultSet.getString("Alternativa4")).append("\n")
                    .append("Alternativa 5: ").append(resultSet.getString("Alternativa5"))
                    .append("\n").append("\n");
        }

        closeConnection();
        return resultBuilder.toString();
    }



    public String insert(String table, int id, int idEspecialidades,int idAgenda ,String NomePsicologo, double CRP, double Telefone, String Abordagem) throws SQLException {
        connection = getConnection();

        String sql = "INSERT INTO " + table + " (idPsicologo, idEspecialidades , idAgenda, NomePsicologo, CRP, Telefone, Abordagem) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, idEspecialidades);
        preparedStatement.setInt(3, idAgenda);
        preparedStatement.setString(4, NomePsicologo);
        preparedStatement.setDouble(5, CRP);
        preparedStatement.setDouble(6, Telefone);
        preparedStatement.setString(7, Abordagem);

        int rowsInserted = preparedStatement.executeUpdate();
        preparedStatement.close();

        if (rowsInserted > 0) {
            return "Cadastrado com sucesso!";
        } else {
            return "Falha ao cadastrar.";
        }
    }



    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados!");
            e.printStackTrace();
        }
    }
}