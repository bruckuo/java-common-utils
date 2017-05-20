package com.mybatis.common.utils;

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:51
 */
public class XMLUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLUtils.class);

    public static String obj2XML(Object object) {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.ignoreUnknownElements();
        return xStream.toXML(object);
    }

    public static Object XML2Obj(String xml, Class tClass) {
        XStream xStream = new XStream();
        xStream.processAnnotations(tClass);
        xStream.ignoreUnknownElements();
        return xStream.fromXML(xml);
    }

    public static void reflect(Object o) throws Exception {
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();

        for(int i = 0; i < fields.length; ++i) {
            Field f = fields[i];
            f.setAccessible(true);
        }

    }

    public static byte[] readInput(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int len;
        while((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        out.close();
        in.close();
        return out.toByteArray();
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int i;
        while((i = is.read()) != -1) {
            baos.write(i);
        }

        return baos.toString();
    }

    public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if(sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
        }

        return tInputStringStream;
    }

    /** @deprecated */
    @Deprecated
    public static Object getObjectFromXML(String xml, Class tClass) {
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();
        return xStreamForResponseData.fromXML(xml);
    }

    public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
        if(key != "" && key != null) {
            String result = (String)map.get(key);
            return result == null?defaultValue:result;
        } else {
            return defaultValue;
        }
    }

    public static int getIntFromMap(Map<String, Object> map, String key) {
        return key != "" && key != null?(map.get(key) == null?0:Integer.parseInt((String)map.get(key))):0;
    }

    public static String log(Object log) {
        LOGGER.info(log.toString());
        return log.toString();
    }

    public static Map<String, Object> modelToMap(Object obj) {
        Map<String, Object> result = new HashMap();

        for(Class clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fs = clazz.getDeclaredFields();
            StringBuffer sb = null;

            for(int i = 0; i < fs.length; ++i) {
                if(!Modifier.isStatic(fs[i].getModifiers())) {
                    fs[i].setAccessible(true);

                    try {
                        Object value = fs[i].get(obj);
                        if(value != null) {
                            result.put(fs[i].getName(), String.valueOf(value));
                        }
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }
                }
            }
        }

        return result;
    }
}

