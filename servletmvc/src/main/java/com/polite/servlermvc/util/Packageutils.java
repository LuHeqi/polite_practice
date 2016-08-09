package com.polite.servlermvc.util;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author polite
 * @date 2016-08-09 .
 */
public class Packageutils {
    /**
     * 从package中，获取所有的class
     * @param pack
     * @param recursive
     * @return
     */
    public static Set<Class<?>> getClassList(String pack, boolean recursive) {
        Set<Class<?>> classes= new LinkedHashSet<>();
            String packageDir = pack.replace('.', '/');
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().
                    getResources(packageDir);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if ( "file".equals(protocol)){
                    //TODO  是文件类型 （） handle
                } else if ("jar".equals(protocol)) {
                    // TODO  jar type handle
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }
}
