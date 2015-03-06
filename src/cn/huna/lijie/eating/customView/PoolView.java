package cn.huna.lijie.eating.customView;

import java.util.ArrayList;

import cn.huna.lijie.eating.utils.Loger;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PoolView extends FrameLayout{
	private static final int ALLOW_MAXNUM = 14;  //一次最多同时显示条目数
	
	private int[][] positions;
	private int stepX,stepY;
	private ArrayList<Integer> allowPosition;
	
	private ArrayList<String> data;
	private int maxNum = 10;		//一次最多显示的条目数
	private int initDuration = 500;
	private int duration = 6000;
	private int maxDuration = 5000;	//显示最长时长	毫秒
	private int minDuration = 1000;   //最短时长
	
	private boolean popFlag;
	
	private int itemCounter;
	private boolean isStartRemove;
	
	public PoolView(Context context){
		super(context);
		init();
	}

	public PoolView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		positions = new int[ALLOW_MAXNUM][2];
		allowPosition = new ArrayList<Integer>();
		for(int i = 0;i < ALLOW_MAXNUM;i++){
			allowPosition.add(i);
		}
		
		data = new ArrayList<String>();
		
		
		for(int i = 0;i < 40;i++){
			data.add("test"+i);
		}
		
	}
	
	private void measurePosition(){
		int width = getWidth();
		int height = getHeight();
		stepX = (width - 100)/4;
		stepY = (height - 50)/5;
		
		int startX = (width - 2*stepX)/2;
		int startY = 25;
		for(int i = 0;i < 2;i++){
			positions[i][0] = startX + i*stepX;
			positions[i][1] = startY;
			
			positions[ALLOW_MAXNUM - i - 1][0] = startX + i*stepX;
			positions[ALLOW_MAXNUM - i - 1][1] = height - startY - stepY;
		}
		
		startX = (width - 3*stepX)/2;
		startY += stepY;
		for(int i = 0;i < 3;i++){
			positions[i+2][0] = startX + i*stepX;
			positions[i+2][1] = startY;
			
			positions[ALLOW_MAXNUM - i - 1 - 2][0] = startX + i*stepX;
			positions[ALLOW_MAXNUM - i - 1 - 2][1] = height - startY - stepY;
		}
		
		startX = (width -4*stepX)/2;
		startY += stepY;
		for(int i = 0;i < 4;i++){
			positions[i+5][0] = startX + i*stepX;
			positions[i+5][1] = startY;
			
		}
		
//		for(int i = 0;i < positions.length;i++){
//			Loger.i("tag", positions[i][0] + "-" + positions[i][1]+"--");
//		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		measurePosition();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
	
	@Override
	protected void onAttachedToWindow() {
		popFlag = true;
		itemCounter = 0;
		isStartRemove = false;
		
		postDelayed(new Runnable() {
			
			@Override
			public void run() {
				popItem();
			}
		}, 500);
		super.onAttachedToWindow();
	}

	@Override
	protected void onDetachedFromWindow() {
		popFlag = false;
		super.onDetachedFromWindow();
	}
	
	private void popItem(){
		if(data == null || data.size() == 0)
			return;
//		for(int i = 0;i < positions.length;i++){
//			add(data.get(i),i);
//		}
		int index = (int) (Math.random() * data.size());
		String text = data.get(index);
		
		int allowIndex = (int) (Math.random() * allowPosition.size());
		int positionIndex = allowPosition.get(allowIndex);
		allowPosition.remove(allowIndex);
		
		add(text, positionIndex);
		
		int duration;
		if(itemCounter < maxNum){
			itemCounter++;
			duration = initDuration;
		}
		else{
			duration = this.duration/2;
		}
		
		if(popFlag){
			postDelayed(new Runnable() {
				
				@Override
				public void run() {
					if(itemCounter < maxNum){
						popItem();
					}
					else{
						popItem();
						if(!isStartRemove){
							isStartRemove = true;
							removeItem();
						}
					}
				}
			}, duration);
		}
	}

	private void add(String text, int positionIndex){
		if(data == null || data.size() == 0)
			return;
		
		TextView view = new TextView(getContext());
		view.setText(text);
		view.setVisibility(View.INVISIBLE);
		
		
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		lp.topMargin = positions[positionIndex][1];
		lp.leftMargin = positions[positionIndex][0];
		
		view.setTag(Integer.valueOf(positionIndex));
		view.setLayoutParams(lp);
		addView(view); 
		setItemAnimation(view);
	}
	
	private void removeItem(){
		int childCount = getChildCount();
		final View view = getChildAt((int)(Math.random() * childCount));
		
		view.clearAnimation();
		AlphaAnimation alphaAnimation2 = new AlphaAnimation(1, 0);
	    alphaAnimation2.setDuration(duration/2);
	    alphaAnimation2.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				allowPosition.add((Integer)view.getTag());
				removeView(view);
				removeItem();
			}
		});
	    view.startAnimation(alphaAnimation2);
	}
	
	private void setItemAnimation(final View view){
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0, 1);
	    alphaAnimation1.setDuration(duration);
	    alphaAnimation1.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				view.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
			}
		});
	    view.startAnimation(alphaAnimation1);
	}

	public void setData(ArrayList<String> data){
		this.data = data;
	}
	
	public ArrayList<String> getData(){
		return this.data;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		if(maxNum > ALLOW_MAXNUM)
			maxNum = ALLOW_MAXNUM;
		this.maxNum = maxNum;
	}

	public int getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

	public int getMinDuration() {
		return minDuration;
	}

	public void setMinDuration(int minDuration) {
		this.minDuration = minDuration;
	}
	
	
}
