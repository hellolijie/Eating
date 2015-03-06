package cn.huna.lijie.eating.customView;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PopView extends FrameLayout{
	
	private ArrayList<String> visiableList;
	private ArrayList<String> invisiableList;
	
	private ArrayList<Item> items;
	
	public PopView(Context context){
		super(context);
		init();
	}

	public PopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		visiableList = new ArrayList<String>();
		invisiableList = new ArrayList<String>();
		 
		for(int i = 0;i < 40;i++){
			invisiableList.add("test"+i);
		}
	}
	
	
	
	@Override
	protected void onAttachedToWindow() {
		postDelayed(new Runnable() {
			
			@Override
			public void run() {
				measurePosition();
				for(Item item : items){
					item.show(null);
				}
				
			}
		}, 300);
		
		
		super.onAttachedToWindow();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	

	private void measurePosition(){
		items = new ArrayList<PopView.Item>();
		
		
		float width = getWidth();
		float height = getHeight();
		
		float centerX = width/2;
		float centerY = height/2;
		
		float stepX = width/3*2/3;
		float stepY = height/4;
		
		Item item1 = new Item();
		item1.x = (int) (centerX - stepX*1.5);
		item1.y = (int) (centerY - stepY);
		items.add(item1);
		
		Item item2 = new Item();
		item2.x = (int) (centerX - stepX*0.5);
		item2.y = (int) (centerY - stepY);
		items.add(item2);
		
		Item item3 = new Item();
		item3.x = (int) (centerX + stepX*0.5);
		item3.y = (int) (centerY - stepY);
		items.add(item3);
		
		Item item4 = new Item();
		item4.x = (int) (centerX - stepX);
		item4.y = (int) centerY;
		items.add(item4);
		
		Item item5 = new Item();
		item5.x = (int) (centerX);
		item5.y = (int) centerY;
		items.add(item5);
		
		
//		int stepX = (width - 100)/5;
//		int stepY = (height - 50)/4;
//		int startX = (width - 2*stepX)/2;
//		int startY = 25;
//		
//		for(int i = 0 ;i < 4;i++){
//			startX = (width - (4+i)*i/2 * stepX)/2;
//			startY += stepY;
//			
//			for(int j = 0;j < i+2;j++){
//				Item item = new Item();
//				item.x = startX + i*stepX;
//				item.y = startY;
//				
//				items.add(item);
//			}
//		}
		
		
		
//		for(int i = 0;i < 2;i++){
//			Item item = new Item();
//			item.x = 
//			positions[i][0] = startX + i*stepX;
//			positions[i][1] = startY;
//			
//			positions[ALLOW_MAXNUM - i - 1][0] = startX + i*stepX;
//			positions[ALLOW_MAXNUM - i - 1][1] = height - startY - stepY;
//		}
//		
//		startX = (width - 3*stepX)/2;
//		startY += stepY;
//		for(int i = 0;i < 3;i++){
//			positions[i+2][0] = startX + i*stepX;
//			positions[i+2][1] = startY;
//			
//			positions[ALLOW_MAXNUM - i - 1 - 2][0] = startX + i*stepX;
//			positions[ALLOW_MAXNUM - i - 1 - 2][1] = height - startY - stepY;
//		}
//		
//		startX = (width -4*stepX)/2;
//		startY += stepY;
//		for(int i = 0;i < 4;i++){
//			positions[i+5][0] = startX + i*stepX;
//			positions[i+5][1] = startY;
//			
//		}
		
//		for(int i = 0;i < positions.length;i++){
//			Loger.i("tag", positions[i][0] + "-" + positions[i][1]+"--");
//		}
	}
	
	private String getColorByRadom(){
		 //红色
	    String red; 
	    //绿色
	    String green;
	    //蓝色
	    String blue;
	    //生成随机对象
	    Random random = new Random();  
	    //生成红色颜色代码
	    red = Integer.toHexString(random.nextInt(256)).toUpperCase();
	    //生成绿色颜色代码
	    green = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
	    //生成蓝色颜色代码
	    blue = Integer.toHexString(random.nextInt(256)).toUpperCase();  
	         
	    //判断红色代码的位数
	    red = red.length()==1 ? "0" + red : red ;  
	    //判断绿色代码的位数
	    green = green.length()==1 ? "0" + green : green ; 
	    //判断蓝色代码的位数
	    blue = blue.length()==1 ? "0" + blue : blue ;
	    //生成十六进制颜色值
	    return "#"+red+green+blue;
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
		
		private View getItemView(){
			if(view == null){
				view = new TextView(getContext());
				view.setVisibility(View.INVISIBLE);
				FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp.gravity = Gravity.TOP | Gravity.LEFT;
				lp.topMargin = y;
				lp.leftMargin = x;
				view.setLayoutParams(lp);
				PopView.this.addView(view);
			}
			else{
				String oldText = (String) view.getTag();
				visiableList.remove(oldText);
				invisiableList.add(oldText);
			}
			int textIndex = (int)(Math.random() * invisiableList.size());
			String text = invisiableList.get(textIndex);
			visiableList.add(text);
			invisiableList.remove(textIndex);
			
			view.setTag(text);
			((TextView)view).setText(text);
			((TextView)view).setTextColor(Color.parseColor(getColorByRadom()));
			
			
			return view;
		}
		
		void show(View view){
			if(view == null)
				view = getItemView();
//			if(isShow)
//				return;
//			isShow = true;
			this.view = view;
			
			view.clearAnimation();
			AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1);
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
					}, (int)(Math.random() * durationShow * 3));					
				}
			});
		    
		    view.startAnimation(alphaAnimation);
		    view.setVisibility(VISIBLE);
		    
		} 
		
		void dismiss(){
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.2f);
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
					show(getItemView());
				}
			});
		    view.startAnimation(alphaAnimation);
		}
	}
}
