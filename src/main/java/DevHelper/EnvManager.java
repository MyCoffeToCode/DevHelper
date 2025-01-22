package DevHelper;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvManager {

    public static String getEnv(String env) {
        String result = searchEnv(env);
        if (result != null) {
            return result;
        }
        throw new IllegalArgumentException("ERROR: Variável " + env + " não encontrada");
    }

    public static String getEnvOrDefaultValue(String env, String defaultValue) {
        String result = searchEnv(env);
        if (result != null) {
            return result;
        }
        return defaultValue;
    }

    private static String searchEnv(String env) {
        if (System.getenv(env) != null && !System.getenv(env).isEmpty()) {
            return System.getenv(env);
        }
        String environmentVar = Dotenv.load().get(env);
        if (environmentVar != null && !environmentVar.isEmpty()) {
            return environmentVar;
        }
        return null;
    }
}
