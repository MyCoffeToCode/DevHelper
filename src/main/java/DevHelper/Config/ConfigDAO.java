package DevHelper.Config;

import DevHelper.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigDAO {

    public static Config getConfig() {
        String query = "SELECT * FROM config LIMIT 1";

        try (Connection conn = DatabaseManager.connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
           if (rs.next()) {
               String pomodoroRole = rs.getString("pomodoro_role");
               String adminRole = rs.getString("admin_role");
               return new Config(pomodoroRole,adminRole);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        createRow();
        return getConfig();
    }

    public static boolean saveConfig(Config config) {
        String query = "INSERT OR REPLACE INTO config (id, pomodoro_role, admin_role) VALUES (1,?,?)";

        try (Connection conn = DatabaseManager.connect()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, config.getPomodoroRole());
            stmt.setString(2, config.getAdminRole());

            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createRow() {
        String query = "INSERT INTO config VALUES (1,NULL,NULL)";

        try (Connection conn = DatabaseManager.connect()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
