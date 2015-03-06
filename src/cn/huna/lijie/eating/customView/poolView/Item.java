package cn.huna.lijie.eating.customView.poolView;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Item {
	public int x,y;
	public int regionX,regionY;
	private Paint paint;
	
	private String text;
	
	public Item(String item){
		paint = new Paint();
		text = item;
	}
	
	public void draw(Canvas canvas){
		
		
		x = (int) (Math.random()*(regionX - paint.measureText(text)) + x);
		y = (int) (Math.random()*regionY/2 + y);
		
		canvas.drawText(text, x, y, paint);
	}
}
