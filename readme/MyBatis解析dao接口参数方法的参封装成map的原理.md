MyBatis解析dao接口参数方法的参封装成map的原理
==

<details>
<summary>Parameter类</summary>

```java
/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package java.lang.reflect;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import sun.reflect.annotation.AnnotationSupport;

/**
 * Information about method parameters.
 *
 * A {@code Parameter} provides information about method parameters,
 * including its name and modifiers.  It also provides an alternate
 * means of obtaining attributes for the parameter.
 *
 * @since 1.8
 */
public final class Parameter implements AnnotatedElement {

    private final String name;
    private final int modifiers;
    private final Executable executable;
    private final int index;
    
    // ... ...
    
    public String getName() {
        // Note: empty strings as parameter names are now outlawed.
        // The .isEmpty() is for compatibility with current JVM
        // behavior.  It may be removed at some point.
        if(name == null || name.isEmpty())
            return "arg" + index;
        else
            return name;
    }
    
    // ... ...
}
```
</details>


<details>
<summary>ParamNameUtil类</summary>

```java
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
        return (List)Arrays.stream(executable.getParameters()).map(Parameter::getName).collect(Collectors.toList());
    }

    private ParamNameUtil() {
    }
}
```
</details>


<details>
<summary>ParamNameResolver类</summary>

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.ibatis.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public class ParamNameResolver {
    public static final String GENERIC_NAME_PREFIX = "param";
    private final boolean useActualParamName;
    private final SortedMap<Integer, String> names;
    private boolean hasParamAnnotation;

    public ParamNameResolver(Configuration config, Method method) {
        this.useActualParamName = config.isUseActualParamName();
        Class<?>[] paramTypes = method.getParameterTypes();
        Annotation[][] paramAnnotations = method.getParameterAnnotations();
        SortedMap<Integer, String> map = new TreeMap();
        int paramCount = paramAnnotations.length;

        for(int paramIndex = 0; paramIndex < paramCount; ++paramIndex) {
            if (!isSpecialParameter(paramTypes[paramIndex])) {
                String name = null;
                Annotation[] var9 = paramAnnotations[paramIndex];
                int var10 = var9.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    Annotation annotation = var9[var11];
                    if (annotation instanceof Param) {
                        this.hasParamAnnotation = true;
                        name = ((Param)annotation).value();
                        break;
                    }
                }

                if (name == null) {
                    if (this.useActualParamName) {
                        name = this.getActualParamName(method, paramIndex);
                    }

                    if (name == null) {
                        name = String.valueOf(map.size());
                    }
                }

                map.put(paramIndex, name);
            }
        }

        this.names = Collections.unmodifiableSortedMap(map);
    }
    
    // 注意：useActualParamName 默认为true，开启使用当前参数名后，当未使用@Param()指定参数名的，则参数名命名规则：arg + 当前参数的index索引号
    private String getActualParamName(Method method, int paramIndex) {
        return (String)ParamNameUtil.getParamNames(method).get(paramIndex);
    }

    private static boolean isSpecialParameter(Class<?> clazz) {
        return RowBounds.class.isAssignableFrom(clazz) || ResultHandler.class.isAssignableFrom(clazz);
    }

    public String[] getNames() {
        return (String[])this.names.values().toArray(new String[0]);
    }

    public Object getNamedParams(Object[] args) {
        int paramCount = this.names.size();
        if (args != null && paramCount != 0) {
            if (!this.hasParamAnnotation && paramCount == 1) {
                Object value = args[(Integer)this.names.firstKey()];
                return wrapToMapIfCollection(value, this.useActualParamName ? (String)this.names.get(0) : null);
            } else {
                Map<String, Object> param = new ParamMap();
                int i = 0;

                for(Iterator var5 = this.names.entrySet().iterator(); var5.hasNext(); ++i) {
                    Entry<Integer, String> entry = (Entry)var5.next();
                    param.put(entry.getValue(), args[(Integer)entry.getKey()]);
                    String genericParamName = "param" + (i + 1);
                    if (!this.names.containsValue(genericParamName)) {
                        param.put(genericParamName, args[(Integer)entry.getKey()]);
                    }
                }

                return param;
            }
        } else {
            return null;
        }
    }
    
    // 参数为Collection集合类型的情况
    public static Object wrapToMapIfCollection(Object object, String actualParamName) {
        ParamMap map;
        if (object instanceof Collection) {
            map = new ParamMap();
            map.put("collection", object);
            if (object instanceof List) {
                map.put("list", object);
            }

            Optional.ofNullable(actualParamName).ifPresent((name) -> {
                map.put(name, object);
            });
            return map;
        } else if (object != null && object.getClass().isArray()) {
            map = new ParamMap();
            map.put("array", object);
            Optional.ofNullable(actualParamName).ifPresent((name) -> {
                map.put(name, object);
            });
            return map;
        } else {
            return object;
        }
    }
}
```
</details>