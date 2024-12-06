package DevHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:data/bot_database.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {

    String createMemesTableSQL = """
        CREATE TABLE IF NOT EXISTS memes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            url TEXT NOT NULL UNIQUE,
            category TEXT,
            added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Cria a tabela de memes
            stmt.execute(createMemesTableSQL);
            System.out.println("Banco de dados inicializado!");
        } catch (SQLException e){
            System.err.println("Erro ao inicializar o banco: " + e.getMessage());
        }
    }

    // Adiciona um novo meme ao banco de dados
    public static void addMeme(String url, String category) {
        String checkSql = "SELECT COUNT(*) FROM memes WHERE url = ?";
        String insertSql = "INSERT INTO memes (url, category) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement((checkSql));
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            checkStmt.setString(1, url);
            ResultSet rs = checkStmt.executeQuery();
            if(rs.next() && rs.getInt(1) > 0){
                return;
            }

            // Insere o novo meme
            insertStmt.setString(1, url);
            insertStmt.setString(2, category);
            insertStmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Erro ao adicionar meme: " + e.getMessage());
        }
    }

    // lista todos os memes do banco de dados
    public static List<String> listMemes() {
    String querySQL = "SELECT id, url, category, added_at FROM memes";
    List<String> memes = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                memes.add("ID: " + rs.getInt("id") + " | URL: " + rs.getString("url") + " | Categoria: " + rs.getString("category") + " | Adicionado em: " + rs.getString("added_at"));
            }
        } catch (SQLException e){
            System.err.println("Erro ao listar memes: " + e.getMessage());
        }
        return memes;
    }
}
