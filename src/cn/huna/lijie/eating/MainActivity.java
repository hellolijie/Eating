package cn.huna.lijie.eating;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initRightMenu();
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
//                iv_leftmenu.startAnimation(rateanimation);
            }
        });
        menu.setOnCloseListener(new SlidingMenu.OnCloseListener() {

            @Override
            public void onClose() {
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
    
    public void onClick(View view){
    	switch(view.getId()){
    	case R.id.iv_left_menu:
    		SlidingMenu menu = getSlidingMenu();
    		menu.showMenu();
    		break;
    	}
    }
}
