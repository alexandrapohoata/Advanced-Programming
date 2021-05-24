import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CmdClass implements Command{
    Method[] methods;
    int modifier;
    Class clazz;

    @Override
    public void execute(String param) throws ClassNotFoundException {
        clazz = Class.forName(param);   // incarc dinamic

        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("Constructors (" + constructors.length + "):");
        for (Constructor<?> c : constructors)
            System.out.println("\t" + c.getName());

        System.out.println("\nPackage class: " + clazz.getPackageName());

        methods = clazz.getMethods();
        System.out.println("\nMethods (" + methods.length + "):");
        for (Method m : methods)
            System.out.println("\t" + m.getName());

        modifier = clazz.getModifiers();
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("Static methods:");
        for (Method m : methods) {
            if (Modifier.isStatic(m.getModifiers())) {
                if (m.getParameterCount() == 0) {
                    m.invoke(m.getName());
                    System.out.println("Executata");
                }
                System.out.println(m);
            }
        }
    }
}
