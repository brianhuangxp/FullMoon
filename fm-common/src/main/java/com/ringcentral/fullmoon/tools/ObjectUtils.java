package com.ringcentral.fullmoon.tools;


import java.io.*;
import java.sql.Timestamp;

public class ObjectUtils {
    public static boolean isEmpty(Object o){
        if(o instanceof String){
           return null == o || "".equals(o);
        }
        return null == o;
    }

    public static Timestamp getTimestamp(Long time) {
        return isEmpty(time) ? null : new Timestamp(time);
    }


    public static Object cloneObject(Serializable obj) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return in.readObject();
        } catch (Exception e) {

        }
        return null;
    }
}
