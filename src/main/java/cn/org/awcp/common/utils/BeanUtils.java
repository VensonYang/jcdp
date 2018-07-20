package cn.org.awcp.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BeanUtils {


    public static void copyProperties(Object from, Object to) {
        copyProperties(from,to,true);
    }

    public static void copyProperties(Object from, Object to, boolean ignoreNull) {
        try {
            if (from == null || to == null) {
                return;
            }
            BeanInfo toBI = Introspector.getBeanInfo(to.getClass());
            PropertyDescriptor[] toPD = toBI.getPropertyDescriptors();
            BeanInfo fromBI = Introspector.getBeanInfo(from.getClass());
            PropertyDescriptor[] fromPD = fromBI.getPropertyDescriptors();
            HashMap<String, PropertyDescriptor> fromMap = new HashMap<>(fromPD.length);
            for (PropertyDescriptor pd : fromPD) {
                fromMap.put(pd.getName(), pd);
            }
            for (PropertyDescriptor toP : toPD) {
                PropertyDescriptor formP = fromMap.get(toP.getName());
                if (formP == null){
                    continue;
                }
                Method setMethod = toP.getWriteMethod();
                Method getMethod = formP.getReadMethod();
                if (getMethod != null && setMethod != null) {
                    Object result = getMethod.invoke(from);
                    if (ignoreNull && result == null){
                        continue;
                    }
                    setMethod.invoke(to, result);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
