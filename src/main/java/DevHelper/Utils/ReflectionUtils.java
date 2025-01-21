package DevHelper.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReflectionUtils {

    public static List<Class<?>> getClassesInPackage(String packageName) throws ClassNotFoundException {
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File directory = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
        List<Class<?>> classes = new ArrayList<>();

        searchClassesInPackage(directory, packageName, classes);

        return classes;
    }

    private static void searchClassesInPackage(File directory,String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        if (directory.exists() && directory.isDirectory()) {
            String[] files = directory.list();
            if (files == null) {
                return;
            }

            for (String file : files) {
                if (!file.endsWith(".class")) {
                    searchClassesInPackage(new File(directory, file), packageName + "." + file, classes);
                } else {
                    //Remove o .class do final do arquivo
                    String className = packageName + "." + file.substring(0, file.length() - 6);
                    classes.add(Class.forName(className));
                }
            }
        }
    }

}
