package com.example.natetest;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MiniGame extends ActionBarActivity {
	private List<View> moles = new LinkedList<View>();
	private MediaPlayer mediaPlayer;
	
	private int numberClicks = 0;
	private int score = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mini_game);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		TextView textView = (TextView) findViewById(R.id.textView4);
		Intent intent = getIntent();
		textView.setText(intent.getStringExtra(MainActivity.USER_NAME));

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mini_game, menu);
		View box = findViewById(R.id.AbsoluteLayout1);
		ViewGroup boxContainer = (ViewGroup)box;
		for(int i = 0; i<7 ; i++){
			box = boxContainer.getChildAt(i);
			moles.add(box);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public void moleClicked(View clickedMole){
		numberClicks++;
		score++;
		
		if(score > 10){
			for(View v : moles){
				v.setVisibility(View.INVISIBLE);
			}
			
		}
		
		TextView scoreBoard = (TextView) findViewById(R.id.textView2);
		scoreBoard.setText(score + " points");
		Random rand = new Random();
		int turnOn = rand.nextInt(moles.size());
		
		for(int i = 0; i<=2; i++){
			while(moles.get(turnOn) == clickedMole){
				turnOn = rand.nextInt(moles.size());
			}
			moles.get(turnOn).setVisibility(View.VISIBLE);
			clickedMole.setVisibility(View.INVISIBLE);			
		}

		
		
		mediaPlayer = MediaPlayer.create(this, R.raw.punch);
		try{
			mediaPlayer.start();
		}catch (Exception e){
			System.out.println("Bad stuff " + e);
		}finally{
			//smediaPlayer.release();
		}



    }

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_mini_game,
					container, false);
			return rootView;
		}
	}

}
