package DevHelper.Config;

public class Config {
    private String pomodoroRole;
    private String adminRole;

    public Config() {
    }

    public Config(String pomodoroRole, String adminRole) {
        this.pomodoroRole = pomodoroRole;
        this.adminRole = adminRole;
    }

    public String getPomodoroRole() {
        return pomodoroRole;
    }

    public void setPomodoroRole(String pomodoroRole) {
        this.pomodoroRole = pomodoroRole;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public String toString() {
        return "Config{" +
                "pomodoroRole='" + pomodoroRole + '\'' +
                ", adminRole='" + adminRole + '\'' +
                '}';
    }
}
