package ar.com.pinard.meetapp.proxy.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import ar.com.pinard.meetapp.proxy.ProxyHelper;

/**
 * Created by agustin on 14/07/15.
 * Request class to work with JSON data and map them to Java objects
 */
public class GsonRequest<T> extends Request<T> {
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private final Type mClazz;
    private final Response.Listener<T> mListener;
    private final Map<String, String> mParameters;

    private Gson mGson;
    private Map<String, String> mResponseCookies;
    private Map<String, String> mRequestCookies;

    public GsonRequest(Type classType, int method, String url,
                       Map<String, String> parameters,
                       Map<String, String> cookies,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, ProxyHelper.buildUrl(method, url, parameters), errorListener);
        this.mClazz = classType;
        this.mListener = listener;
        this.mParameters = parameters;
        this.mRequestCookies = cookies;

        initializeGsonDeserializer();
    }

    public GsonRequest(Type classType, int method, String url, Map<String, String> parameters,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, ProxyHelper.buildUrl(method, url, parameters), errorListener);
        this.mClazz = classType;
        this.mListener = listener;
        this.mParameters = parameters;
        this.mRequestCookies = null;
        initializeGsonDeserializer();
    }

    protected GsonBuilder initGsonBuilder(GsonBuilder builder) {
        return builder;
    }

    private void initializeGsonDeserializer() {
        mGson = initGsonBuilder(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")// default ASP.NET date format
        ).create();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mRequestCookies != null) {
            Map<String, String> map = new HashMap<>();
            map.put("Cookie", flatCookies(mRequestCookies));
            return map;
        }

        return super.getHeaders();
    }

    private String flatCookies(Map<String, String> cookies) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> c : cookies.entrySet()) {
            sb.append(String.format("%1$s=%2$s;", c.getKey(), c.getValue()));
        }
        return sb.toString();
    }

    @Override
    protected void deliverResponse(T response) {
        //mListener.onResponse(response, mResponseCookies);
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        // Carga las cookies de respuesta
        parseCookies(response.headers);

        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T parsedObject = mGson.fromJson(json, mClazz);
            return Response.success(parsedObject, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    private void parseCookies(Map<String, String> headers) {
        mResponseCookies = new HashMap<>();

        if (headers.containsKey(SET_COOKIE_KEY)) {
            String value = headers.get(SET_COOKIE_KEY);
            String[] cookies = value.split(";");
            for (String cookie : cookies) {
                String[] keyValue = cookie.trim().split("=");
                mResponseCookies.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : "");
            }
        }
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return mParameters != null ? mParameters : super.getParams();
    }
}