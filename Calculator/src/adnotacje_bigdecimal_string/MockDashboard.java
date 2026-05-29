package adnotacje_bigdecimal_string;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MockDashboard {

    @RequiredRole("USER")
    public void viewReports() {
        System.out.println("Raport:... ");
    }

    @RequiredRole("ADMIN")
    public void deleteUsers() {
        System.out.println("Usuwam użytkownika...");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        var md = new MockDashboard();

        System.out.println("---ADMIN---");
        checkAccess(md, "ADMIN");
        System.out.println("---USER---");
        checkAccess(md, "USER");

    }

    public static void checkAccess(Object instance, String userRole) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = instance.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequiredRole.class)) {
                RequiredRole ann = method.getAnnotation(RequiredRole.class);
                String requiredRole = ann.value();

                if (userRole.equals(requiredRole)) {
                    System.out.println("Dostęp do: " + method.getName() + " - przyznany");
                    method.invoke(instance);
                } else {
                    System.out.println("Dostęp do: " + method.getName()
                            + " - Brak dostępu. Wymagana rola: " + requiredRole);
                }
            }
        }
    }
}
