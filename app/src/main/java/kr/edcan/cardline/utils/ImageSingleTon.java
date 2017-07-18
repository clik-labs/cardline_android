package kr.edcan.cardline.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by kotohana5706 on 2015. 11. 28..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class ImageSingleTon {
    private static ImageSingleTon instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private ImageSingleTon(Context context) {
        requestQueue = Volley.newRequestQueue(context);

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {

                    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
                            20);

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        // TODO Auto-generated method stub
                        cache.put(url, bitmap);

                    }

                    @Override
                    public Bitmap getBitmap(String url) {
                        // TODO Auto-generated method stub
                        return cache.get(url);
                    }
                });
    }

    public static ImageSingleTon getInstance(Context context) {
        if (instance == null) {
            instance = new ImageSingleTon(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag("App");
        getRequestQueue().add(req);
    }

}