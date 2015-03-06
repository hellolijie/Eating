package cn.huna.lijie.eating.customView;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class PopView extends FrameLayout{
	private ArrayList<String> data;
	
	public PopView(Context context){
		super(context);
		init();
	}

	public PopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		data = new ArrayList<String>();
		
		
		for(int i = 0;i < 40;i++){
			data.add("test"+i);
		}
	}
	
	private View getItemView(){
	
		
		View view = new TextView(getContext());
		
		
		return view;
	}
	
	class Item{
		int x,y;
		int width,height;
		int durationShow = 3000;
		int durationDismiss = 3000;
		View view;
		
		boolean isShow;
		
		Item(int x,int y,int width,int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		Item(){
		}
		
		void show(View view){
			if(!isShow)
				return;
			isShow = true;
			this.view = view;
			
			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.TOP | Gravity.LEFT;
			lp.topMargin = x;
			lp.leftMargin = y;
			
			AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		    alphaAnimation.setDuration(durationShow);
		    alphaAnimation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					postDelayed(new Runnable() {
						
						@Override
						public void run() {
							dismiss();
						}
					}, (int)(Math.random() * durationShow * 2));					
				}
			});
		    
		    PopView.this.addView(view);
		    view.startAnimation(alphaAnimation);
		    
		} 
		
		void dismiss(){
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
		    alphaAnimation.setDuration(durationDismiss);
		    alphaAnimation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					PopView.this.removeView(view);
					show(getItemView());
				}
			});
		    view.startAnimation(alphaAnimation);
		}
	}
}
