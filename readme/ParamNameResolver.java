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
    /*
    //1、names：{0=id, 1=lastName}；names是MAP，构造器的时候就确定好了

	确定流程：
	1.获取每个标了param注解的参数的@Param的值：id，lastName；  赋值给name;
	2.每次解析一个参数给map中保存信息：（key：参数索引，value：name的值）
		name的值：
			标注了param注解：注解的值
			没有标注：
				1.全局配置：useActualParamName（jdk1.8）：name=参数名
				2.name=map.size()；相当于当前元素的索引
        例如:        
        Employee getEmployeeByIdAndLastName2(@Param("id") Long id, @Param("lastName") String lastName);
        则names为: {0=id, 1=lastName}
        如果是没有标注@Param注解的，则为
        {0=0, 1=1, 2=2}
        
        String[] getNames(),  如返回结果为 [1, "Tom"]:
    
    */
    private final boolean useActualParamName;
    private final SortedMap<Integer, String> names;
    private boolean hasParamAnnotation;
    
    // 解析参数名
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
                    // 默认 useActualParamName=true，表示使用真实参数名
                    if (this.useActualParamName) {
                        name = this.getActualParamName(method, paramIndex);
                    }
                    
                    // 如果未开启获取真实参数名，则键名为序号
                    if (name == null) {
                        name = String.valueOf(map.size());
                    }
                }
                // Entity, key为参数序号，value为设置的参数名或序号形式的字符串
                map.put(paramIndex, name);
            }
        }

        this.names = Collections.unmodifiableSortedMap(map);
    }
    
    // 默认 useActualParamName=true， 表示获取真实参数名
    private String getActualParamName(Method method, int paramIndex) {
        return (String)ParamNameUtil.getParamNames(method).get(paramIndex);
    }

    private static boolean isSpecialParameter(Class<?> clazz) {
        return RowBounds.class.isAssignableFrom(clazz) || ResultHandler.class.isAssignableFrom(clazz);
    }

    public String[] getNames() {
        return (String[])this.names.values().toArray(new String[0]);
    }
    
    // 举例，传入的args为： [1, "Tom"]
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
                    // key为 entry.getValue()，即指定的参数名或序号(未指定参数名的)，
                    // value为 args[(Integer)entry.getKey()]，即真实的参数值
                    // 上面的 public ParamNameResolver(Configuration config, Method method) 方法保证了 this.names中的Entity的 key为参数序号，value为设置的参数名或序号形式的字符串
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
