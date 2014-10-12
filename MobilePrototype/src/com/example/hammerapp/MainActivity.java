package com.example.hammerapp;


import java.util.ArrayList;
import java.util.Random;

import com.example.db.tables.Words;


import com.example.db.MyDBHander;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		MediaPlayer mp;
		Button btnPlay, btnSubmit;
		Words word_sample;
		EditText captchaTxt;
		TextView subtitle;
		boolean flag = false;
		
		public PlaceholderFragment() {
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_main, container, false);
			
			btnPlay = (Button)v.findViewById(R.id.playBtn);
			btnSubmit = (Button)v.findViewById(R.id.submitBtn);
			captchaTxt = (EditText)v.findViewById(R.id.captchaTxt);
			subtitle = (TextView)v.findViewById(R.id.subtitle);
			
			btnPlay.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Random r = new Random();
					int rNo = r.nextInt(6 - 0) + 0;
					mp = getRandomSound(rNo, v.getContext());
					mp.start();
				
					
				}
			});
			
			btnSubmit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String word = captchaTxt.getText().toString();
					MyDBHander dbHandler = new MyDBHander(v.getContext(), null, null, 1);
			    	Words product = new Words(word, chooseFingerP(word));
			    	dbHandler.addProduct(product);
			    	Toast.makeText(v.getContext(), "YOU JUST REGISTERED THE WORD: " + word, Toast.LENGTH_SHORT).show();
					
				}
			});
			
			/*btnAUX.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					flag = true;
					Toast.makeText(v.getContext(), "YOU JUST ACTIVATED SUBTITLES, CLICK AGAIN ON THE AUDIO FILE! " , Toast.LENGTH_SHORT).show();
					MyDBHander dbHandler = new MyDBHander(v.getContext(), null, null, 1);
					word_sample = new Words();
					word_sample = dbHandler.findFingerPrint("eJzV1MltZSEQBdCUgJrDoab8QzDe0983ml0v");
					System.out.println("Tamaño del objeto Word: " + word_sample.getWord());
				}
			});*/
			return v;
		}
		
		public String chooseFingerP(String word){
			String fp;
			switch(word) {
				case "are":
					fp = "eJzV1MltZSEQBdCUgJrDoab8QzDeGOu3ml0v";
				case "friend":
					fp = "eJzV1MltZSEQBdCUgJrDoab8Qz567Ou3ml0v";
				break;
			    case "hello":
			    	fp = "eJzV1MltZSEQBdCUgJrDoab8QzDe0983ml0v";
			    break;
			    case "how":
			    	fp = "eJzV1MltZSEQBdCUgJrDoab8QzDe4443ml0v";
				break;
			    case "my":
			    	fp = "eJzV1MltZSEQBdCUgJrDoab8QzDe2223ml0v";
				break;
			    default:
			    	fp = "eJzV1MltZSEQBdCUgJrDoab8QzDeG111ml0v";
			}
			return fp;
		}
		
		public MediaPlayer getRandomSound(int index, Context ctx){
			MediaPlayer mp_aux;
			MyDBHander dbHandler = new MyDBHander(ctx, null, null, 1);
			word_sample = new Words();
			switch(index) {
				case 0:
					mp_aux = MediaPlayer.create(ctx, R.raw.hello);
					word_sample = dbHandler.findWord("hello");
				break;
				case 1:
					mp_aux = MediaPlayer.create(ctx, R.raw.are);
					word_sample = dbHandler.findWord("are");
				break;
			    case 2:
			    	mp_aux = MediaPlayer.create(ctx, R.raw.friend);
			    	word_sample = dbHandler.findWord("friend");
			    break;
			    case 3:
			    	mp_aux = MediaPlayer.create(ctx, R.raw.how);
			    	word_sample = dbHandler.findWord("how");
				break;
			    case 4:
			    	mp_aux = MediaPlayer.create(ctx, R.raw.you);
			    	word_sample = dbHandler.findWord("you");
				break;
			    default:
			    	mp_aux = MediaPlayer.create(ctx, R.raw.my);
			    	word_sample = dbHandler.findWord("my");
			}
			if(word_sample  != null){
				subtitle.setText(word_sample.getWord());
			}
			return mp_aux;
		}
		
	}

}
