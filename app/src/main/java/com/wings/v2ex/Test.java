package com.wings.v2ex;

import java.util.Properties;

/**
 * Created by wing on 16/8/19.
 */
public class Test {
    public static void main(String[] args) {
        System.getProperties().put("proxySet", "false");
        System.getProperties().put("proxyHost", "");
        System.getProperties().put("socksProxyHost", "");
        System.getProperties().put("socks.ProxyHost", "");
        System.getProperties().put("proxyPort", "");
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("proxySet", "false");
        systemProperties.setProperty("http.proxyHost","");
        systemProperties.setProperty("socks.proxyHost","");
        String property = systemProperties.getProperty("socks.proxyHost");
        System.out.println("");

    }
}
