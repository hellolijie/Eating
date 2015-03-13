package cn.huna.lijie.eating;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ListView;
import cn.huna.lijie.eating.adapter.RestaurantAdapter;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
	private View dropMenu;
	private View leftMenu;
	
	private ListView list;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRightMenu();
    }

    private void initView(){
    	leftMenu = findViewById(R.id.iv_left_menu);
    	list = (ListView) findViewById(R.id.list);
    	
    	
//    	Animation animation = (Animation) AnimationUtils.loadAnimation(
//    	          this, R.anim.list_anim);
//	    LayoutAnimationController lac = new LayoutAnimationController(animation);
//	    lac.setDelay(0.4f);  //设置动画间隔时间
//	    lac.setOrder(LayoutAnimationController.ORDER_NORMAL); //设置列表的显示顺序
//	    list.setLayoutAnimation(lac);  //为ListView 添加动画
//	    
	    list.setAdapter(new RestaurantAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void initRightMenu() {
        //Fragment left_menu=new Fragment();
        setBehindContentView(R.layout.left_menu);
        //getSupportFragmentManager().beginTransaction().replace(R.layout.left_menu,left_menu).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置滑动阴影特效
        //menu.setShadowWidthRes(R.dimen.shadow_width);
        //menu.setShadowDrawable(R.drawable.shadow);
        menu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
            	menuRotate(true);
//                iv_leftmenu.startAnimation(rateanimation);
            }
        });
        menu.setOnCloseListener(new SlidingMenu.OnCloseListener() {

            @Override
            public void onClose() {
            	menuRotate(false);
//                Animation rateanimation = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation
//                        .RELATIVE_TO_SELF, 0.5f);
//                rateanimation.setDuration(300);
//                rateanimation.setFillAfter(true);
//                iv_leftmenu.startAnimation(rateanimation);
            }
        });


        menu.setBehindWidth(300);
//        menu.setBehindOffset(densityDpi);
        menu.setFadeDegree(0.35f);
    }
    
    private void dropMenu(){
    	if(dropMenu == null)
    		dropMenu = findViewById(R.id.ll_drop_menu_content);
    	final boolean show = dropMenu.getVisibility() == View.INVISIBLE ? true : false;
    	ScaleAnimation scaleAnimation = new ScaleAnimation(
                show ? 0 : 1f, show ? 1f : 0,show ? 0 : 1f, show ? 1f : 0,
                Animation.RELATIVE_TO_SELF,1f,
                Animation.RELATIVE_TO_SELF,1f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				if(show)
					dropMenu.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if(!show){
					dropMenu.setVisibility(View.INVISIBLE);
				}
			}
		});
        dropMenu.clearAnimation();
        dropMenu.startAnimation(scaleAnimation);
    }
    
    private void menuRotate(boolean open){
    	RotateAnimation rotateAnimation = new RotateAnimation(open ? 0 : 90, open ? 90 : 0,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(500);
        
        leftMenu.startAnimation(rotateAnimation);
    }
    
    public void onClick(View view){
    	switch(view.getId()){
    	case R.id.iv_left_menu:
    		SlidingMenu menu = getSlidingMenu();
    		menu.showMenu();
    		break;
    	case R.id.bfs_drop_menu:
    		dropMenu();
    		break;
    	}
    }
}
