package cn.huna.lijie.eating.net;

import android.graphics.Bitmap;
import android.widget.ImageView;
import cn.huna.lijie.eating.MyApplication;
import cn.huna.lijie.eating.R;
import cn.huna.lijie.eating.utils.ImageFileCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

public class LoadImageUtil {
	private ImageFileCache fileCache;
	private RequestQueue requestQueue;
	
	
	public LoadImageUtil(){
		requestQueue = Volley.newRequestQueue(MyApplication.getInstance());  
		fileCache = new ImageFileCache();
	}
	
	
	public void loadImageByVolley(String url,ImageView imageView){  
        ImageCache imageCache = new ImageCache() {  
            @Override 
            public void putBitmap(String key, Bitmap value) {  
            	fileCache.saveBitmap(value, key);
            }  
   
            @Override 
            public Bitmap getBitmap(String key) { 
            	return fileCache.getImage(key);
            }  
        };  
        try {
        	ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);  
        	ImageListener listener = ImageLoader.getImageListener(imageView, R.color.gray,R.drawable.icon_defout_image);  
        	imageLoader.get(url, listener);  
		} catch (Exception e) {
		}
	}  
	
	public void cancel(){
		requestQueue.cancelAll(new Object());
	}

}
