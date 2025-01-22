package DevHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = EnvManager.getEnv("URL_DB");

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

    String createExercisesTableSQL = """
        CREATE TABLE IF NOT EXISTS exercises (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            exercise TEXT NOT NULL,
            language TEXT NOT NULL,
            difficulty TEXT NOT NULL,
            added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

    String createConfigTableSQL = """
        CREATE TABLE IF NOT EXISTS config (
            id INTEGER PRIMARY KEY CHECK (id = 1),
            pomodoro_role TEXT,
            admin_role TEXT
        );
    """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Cria a tabela de memes
            stmt.execute(createMemesTableSQL);
            // Cria a tabela de exercícios
            stmt.execute(createExercisesTableSQL);
            // Cria a tabela de config
            stmt.execute(createConfigTableSQL);
            System.out.println("Banco de dados inicializado!");
            System.out.println("Conectado em: " + conn.getMetaData().getURL());
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
            System.out.println("Meme adicionado: " + url);
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
