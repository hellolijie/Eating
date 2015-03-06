/**
 * Copyright (c) www.bugull.com
 */
package cn.huna.lijie.eating;

import java.io.File;

import android.app.Application;
import cn.huna.lijie.eating.utils.ImageFileCache;




public class MyApplication extends Application { 
    
    private static MyApplication instance;  
    
    
    public static MyApplication getInstance() { 
        return instance;  
    }  
	
	@Override
	public void onCreate() {
 		super.onCreate();
        instance = this;
        
        ImageFileCache imageFileCache = new ImageFileCache();
        String dir = imageFileCache.getDirectory();
        File file=new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
 	}
}
