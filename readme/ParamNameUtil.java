//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.ibatis.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParamNameUtil {
    public static List<String> getParamNames(Method method) {
        return getParameterNames(method);
    }

    public static List<String> getParamNames(Constructor<?> constructor) {
        return getParameterNames(constructor);
    }

    private static List<String> getParameterNames(Executable executable) {
        // 调用了Parameter.getName() 方法来生成List形式的 parameter names
        return (List)Arrays.stream(executable.getParameters()).map(Parameter::getName).collect(Collectors.toList());
    }

    private ParamNameUtil() {
    }
}
