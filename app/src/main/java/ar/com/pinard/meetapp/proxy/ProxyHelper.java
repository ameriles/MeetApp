package ar.com.pinard.meetapp.proxy;

import android.net.Uri;

import com.android.volley.Request;

import java.util.Map;

/**
 * Created by agustin on 14/07/15.
 * Helper methods for proxies
 */
public class ProxyHelper {
    private static String Url = "http://192.168.1.253:8101/";
    //private static String Url = "http://192.168.1.101:55180/";

    public static String createServiceUrl(ProxyBase proxy) {
        return String.format("%1$s%2$s", Url, proxy.getBaseUrl());
    }

    public static String createServiceUrl(String path) {
        return String.format("%1$s%2$s", Url, path);
    }

    public static String buildUrl(int method, String url, Map<String, String> parameters) {
        if (method == Request.Method.GET) {
            if (parameters.size() == 1) {
                return String.format("%1$s/%2$s", url, parameters.values().iterator().next());
            } else {
                Uri.Builder builder = Uri.parse(url).buildUpon();
                for (Map.Entry<String, String> keyValue : parameters.entrySet()) {
                    builder.appendQueryParameter(keyValue.getKey(), keyValue.getValue());
                }

                return builder.toString();
            }
        }

        return url;
    }


//
//    public static String createServiceUrl(ProxyBase proxy, String parameter) {
//        return String.format("%1$s/%2$s", createServiceUrl(proxy), parameter);
//    }
}
