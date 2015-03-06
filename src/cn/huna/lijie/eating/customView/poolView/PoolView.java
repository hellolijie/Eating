package cn.huna.lijie.eating.customView.poolView;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import cn.huna.lijie.eating.utils.Loger;

public class PoolView extends View{
	private int[][] positions;
//	private View test;
	
	private ArrayList<String> data;
	
	private ArrayList<Item> items;
	
	private int width;
	private int height;
	
	private int stepX,stepY;
	
	private int minRow = 2;
	private int maxRow = 5;
	
	public PoolView(Context context){
		super(context);
		init();
	}

	public PoolView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		data = new ArrayList<String>();
		items = new ArrayList<Item>();
		positions = new int[23][2];
		for(int i = 0;i < 23;i++){
			data.add("test"+i);
			items.add(new Item("test"+i));
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
//		test = LayoutInflater.from(getContext()).inflate(R.layout.test, null);
//		test = new TextView(getContext());
//		((TextView)test).setText("test");
//		
//		test.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//	    test.measure(MeasureSpec.makeMeasureSpec(10, MeasureSpec.UNSPECIFIED), 
//	                MeasureSpec.makeMeasureSpec(200, MeasureSpec.UNSPECIFIED));

		////设置该view的实际宽(mMeasuredWidth)高(mMeasuredHeight) 
		setMeasuredDimension(widthSize, heightSize);
	}
	
	@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//    	test.layout(0, 0, 0, 0);
		width = getWidth();
		height = getHeight();
		measurePosition();
    }

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawData(canvas);
//		if(test != null){
//			canvas.translate(20, 200);
//			test.draw(canvas);
//			Loger.i("tag", "not null------");
//		}
//		else
//			Loger.i("tag", "null------");
		
	}
	
	private void measurePosition(){
		stepX = (width - 100)/5;
		stepY = (height - 50)/7;
		
		int startX = (width - 2*stepX)/2;
		int startY = 25;
		for(int i = 0;i < 2;i++){
			positions[i][0] = startX + i*stepX;
			positions[i][1] = startY;
			
			positions[22 - i][0] = startX + i*stepX;
			positions[22 - i][1] = height - startY - stepY;
		}
		
		startX = (width - 3*stepX)/2;
		startY += stepY;
		for(int i = 0;i < 3;i++){
			positions[i+2][0] = startX + i*stepX;
			positions[i+2][1] = startY;
			
			positions[22 - 2 -i][0] = startX + i*stepX;
			positions[22 - 2 -i][1] = height - startY - stepY;
		}
		
		startX = (width -4*stepX)/2;
		startY += stepY;
		for(int i = 0;i < 4;i++){
			positions[i+5][0] = startX + i*stepX;
			positions[i+5][1] = startY;
			
			positions[22 - 5 -i][0] = startX + i*stepX;
			positions[22 - 5 -i][1] = height - startY - stepY;
		}
		
		startX = 50;
		startY +=stepY;
		for(int i = 0;i < 5;i++){
			positions[i+9][0] = startX + i*stepX;
			positions[i+9][1] = startY;
		}
		
	}
	
	private void drawData(Canvas canvas){
		if(data == null)
			return;
//		int x = 0;
//		int y = 0;
	
		for(int i = 0;i < 23;i++){
//			x += 10;
//			y += 10;
			Item item = items.get(i);
			item.x = positions[i][0];
			item.y = positions[i][1];
			item.regionX = stepX;
			item.regionY = stepY;
			item.draw(canvas);
			
		}
	}
	

	public void setData(ArrayList<String> data){
		this.data = data;
		
	}
	
	public ArrayList<String> getData(){
		return this.data;
	}
	
}
