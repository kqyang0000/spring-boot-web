package com.kqyang.web.config.util;

import java.net.InetAddress;
import java.net.Socket;

public class NetUtils {

    public static boolean isLocalPoryUsing(int port) {
        boolean flag = false;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    public static boolean isPortUsing(String host, int port) {
        boolean flag = false;
        try {
            InetAddress iNet = InetAddress.getByName(host);
            new Socket(iNet, port);
            flag = true;
        } catch (Exception e) {
        }
        return flag;
    }
}
