package cn.huna.lijie.eating.net;

import java.util.HashMap;
import java.util.Map;

import cn.huna.lijie.eating.utils.Constants;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

/*
 * POST����ִ����
 */

public class CustomStringRequest extends StringRequest{
	
	private String param;
	private String authorization;
	
	
	public CustomStringRequest(int method, String url,String param,String authorization,
			Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
		setRetryPolicy(new DefaultRetryPolicy(Constants.NET_TIMEOUT, 1, 1.0f));
		this.param=param;
		this.authorization = authorization;
		
	}
	
	@Override
	public byte[] getBody() throws AuthFailureError {
		return param==null?super.getBody():param.getBytes();
	}
	
	
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = new HashMap<String, String>();  
//		headers.put("Charsert", "UTF-8");  
//		headers.put("Accept", "text/plain");
//		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
//		headers.put("Accept-Encoding", "gzip,deflate");  
		headers.put("Authorization", authorization);
		return headers;
	}
}
