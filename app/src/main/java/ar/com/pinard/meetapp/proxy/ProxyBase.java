package ar.com.pinard.meetapp.proxy;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by agustin on 14/07/15.
 * Base class for proxies
 */
public abstract class ProxyBase {
    protected Context mContext;
    protected final RequestQueue mRequestQueue;
    protected static DefaultRetryPolicy sNoRetryPolicy = new DefaultRetryPolicy(
            (int) TimeUnit.SECONDS.toMillis(20),
            0, //NO RETRIES
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    protected ProxyBase(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public String getBaseUrl() {
        return "";
    }

//    protected Map<String, String> getAuthenticationCookie() {
//        Map<String, String> map = new HashMap<>();
//        AuthenticationHelper ah = new AuthenticationHelper(mContext);
//        map.put(AuthenticationHelper.ASPXAUTH_COOKIE, ah.getAuthCookie());
//        return map;
//    }

    protected void enqueueRequest(Request<?> request) {
        mRequestQueue.add(request);
    }

//    protected Response.ErrorListener authErrorListener(final Response.ErrorListener errorListener) {
//        return new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                AuthenticationHelper ah = new AuthenticationHelper(mContext);
//                if (ah.checkAuthenticationError(volleyError)) {
//                    return;
//                }
//
//                errorListener.onErrorResponse(volleyError);
//            }
//        };
//    }
}
