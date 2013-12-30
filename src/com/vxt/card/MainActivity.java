package com.vxt.card;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener{
	private ArrayList<Button> buttons = new ArrayList<Button>() ;
	private int screenHeight;
	private String[] colorCode = {"#d71921", "#00bff3", "#7cc576", "#ff9000", "#8d5da3", "#f24957"};
	private Random random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		random = new Random();
		screenHeight = getResources().getDisplayMetrics().heightPixels - 400;
		
		FrameLayout holder = (FrameLayout) findViewById(R.id.holder);
		
		for (int i = 0; i < 1000; i++) {
			Button button = getCard(i);
			buttons.add(button);
			holder.addView(button);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		Log.i("tag", "screen height " + screenHeight);
		for (Button button : buttons) {
			if (button.getId() != v.getId()) {
				Log.i("tag", "view top " + button.getTop());
				ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button,
						"translationY", (screenHeight  - button.getTop()));
				objectAnimator.setDuration(200);
				objectAnimator.start();
			}
		}
	}
	
	private Button getCard(int index){
		Button card = new Button(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 200);
		params.topMargin = index * 100;
		card.setLayoutParams(params);
		card.setBackgroundColor(getColor());
		card.setOnClickListener(this);
		card.setText(Integer.toString(index));
		card.setId(9000 + index);
		card.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
		return card;
	}
	
	private int getColor(){
		int index = random.nextInt(6);
		return Color.parseColor(colorCode[index]);
	}
}
