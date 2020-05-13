package org.developer.learn.common.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName ClassInfo
 * @Description:输入类名获取并打印Class信息（方法、域、访问修饰符）
 * @Author lfq
 * @Date 2020/5/13
 **/
public class ClassInfoDisplay {
    public static void display(String className) {
        try {
            Class clazz = Class.forName(className);
            Class superCl = clazz.getSuperclass();
            String modifiers = Modifier.toString(clazz.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + clazz.getSimpleName());
            if (superCl != null && superCl != Object.class) {
                System.out.print(" extends " + superCl.getSimpleName());
            }
            Class[] interfaces = clazz.getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                System.out.print(" implements ");
                for (int i = 0; i < interfaces.length; i++) {
                    if (i < interfaces.length - 1) {
                        System.out.print(" " + interfaces[i].getSimpleName() + ",");
                    } else {
                        System.out.print(" " + interfaces[i].getSimpleName());
                    }
                }
            }
            System.out.println("\n{");
            printFields(clazz);
            System.out.println();
            printConstructors(clazz);
            System.out.println();
            printMethods(clazz);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printFields(Class c) {
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            Class type = f.getType();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getSimpleName() + " " + f.getName() + ";");
        }
    }

    private static void printMethods(Class c) {
        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods) {
            Class retType = m.getReturnType();
            System.out.print("  ");
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getSimpleName() + " " + m.getName() + "(");
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getSimpleName());
            }
            System.out.println(");");
        }
    }

    private static void printConstructors(Class clazz) {
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            String name = c.getName();
            System.out.print("  ");
            String modifier = Modifier.toString(c.getModifiers());
            if (modifier.length() > 0) {
                System.out.print(modifier + "  ");
            }
            System.out.print(name + "(");
            // 打印构造参数
            Class[] paramTypes = c.getParameterTypes();
            if (paramTypes.length > 0) {
                for (int i = 0; i < paramTypes.length; i++) {
                    if (i > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(paramTypes[i].getSimpleName());
                }
            }
            System.out.println(");");
        }
    }
}
