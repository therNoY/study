package java_base.reflection;

import util.log.Logger;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 测试通过反射获取字段 并判断类型
 * ParameterizedType 使用测试 参数化类型
 * 即泛型；例如：List< T>、Map< K,V>等带有参数化的对象;
 *
 * @date: 2018/06/25 16:19
 */
public class ParameterizedTypeTest {


    private int myInt;
    /**
     * 1、map: 获取ParameterizedType:class java.lang.String
     * 2、map: 获取ParameterizedType:class com.wangji.demo.ParameterizedTypeTest
     * 3、map:getOwnerType is null
     * 4、map:getRawType:interface java.util.Map
     */
    private Map<String, ParameterizedTypeTest> map;
    /**
     * 1、set1: 获取ParameterizedType:class java.lang.String
     * 2、set1:getOwnerType is null
     * 3、set1:getRawType:interface java.util.Set
     */
    private Set<String> set1;
    /**
     * 1、 clz: 获取ParameterizedType:?
     * 2、 clz:getOwnerType is null
     * 3、clz:getRawType:class java.lang.Class
     */
    private Class<?> clz;
    /**
     * 1、holder: 获取ParameterizedType:class java.lang.String
     * 2、holder:getOwnerType:class com.wangji.demo.ParameterizedTypeTest
     * 3、holder:getRawType:class com.wangji.demo.ParameterizedTypeTest$Holder
     */
    private Holder<String> holder;

    /**
     * 1、list: 获取ParameterizedType:class java.lang.String
     * 2、list:getOwnerType is null
     * 3、list:getRawType:interface java.util.List
     */
    private List<String> list;
    /**
     * str:is not ParameterizedType
     */
    private String str;
    /**
     * i:is not ParameterizedType
     */
    private Integer i;
    /**
     * set:is not ParameterizedType
     */
    private Set set;
    /**
     *  aList:is not ParameterizedType
     */
    private List aList;
    /**
     * 1、entry: 获取ParameterizedType:class java.lang.String
     * 2、entry: 获取ParameterizedType:class java.lang.String
     * 3、entry:getOwnerType:interface java.util.Map
     * 4、entry:getRawType:interface java.util.Map$Entry
     */
    private Map.Entry<String, String> entry;

    private Integer[] integers;


    static class Holder<V> {

    }

    public static void testParameterizedType() {
        Field f = null;
        try {
            // 获取类的所有字段
            Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];

                // 字段是泛型类型
                if(f.getGenericType() instanceof ParameterizedType){
                    Logger.info("字段" + f.getName() + "是泛型类型");
                    ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
                    for(Type type :parameterizedType.getActualTypeArguments()){
                        Logger.info(f.getName()+": 获取ParameterizedType:"+type);
                    }
                    if(parameterizedType.getOwnerType() !=null){
                        Logger.info(f.getName()+":getOwnerType:"+parameterizedType.getOwnerType());
                    }else{
                        Logger.info(f.getName()+":getOwnerType is null");
                    }
                    if(parameterizedType.getRawType() !=null){
                        Logger.info(f.getName()+":getRawType:"+parameterizedType.getRawType());
                    }
                }else if (f.getGenericType() instanceof GenericArrayType) {
                    Logger.info("字段" + f.getName() + "是泛型数组类型");
                }else if (f.getGenericType() instanceof TypeVariable) {
                    Logger.info("字段" + f.getName() + "是变量类型");
                }else if (f.getGenericType() instanceof Class) {
                    Logger.info("字段" + f.getName() + "是基础类型");
                }
            }
        }catch (Exception e){
            Logger.error("error" + e);
        }
    }


    public static void main(String[] args) {
        testParameterizedType();
    }


}
