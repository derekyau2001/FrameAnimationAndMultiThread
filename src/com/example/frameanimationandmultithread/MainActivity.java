package com.example.frameanimationandmultithread;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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

		private ImageView imgDice;
		private TextView txtDice;
		private Button btnDice;

		private Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				int intRand = (int) (Math.random() * 6 + 1);

				String s = getString(R.string.txtDice);
				txtDice.setText(s + intRand);

				switch (intRand) {
				case 1:
					imgDice.setImageResource(R.drawable.dice01);
					break;
				case 2:
					imgDice.setImageResource(R.drawable.dice02);
					break;
				case 3:
					imgDice.setImageResource(R.drawable.dice03);
					break;
				case 4:
					imgDice.setImageResource(R.drawable.dice04);
					break;
				case 5:
					imgDice.setImageResource(R.drawable.dice05);
					break;
				case 6:
					imgDice.setImageResource(R.drawable.dice06);
					break;
				}
			}
		};

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			imgDice = (ImageView) rootView.findViewById(R.id.imgDice);
			txtDice = (TextView) rootView.findViewById(R.id.txtDice);
			btnDice = (Button) rootView.findViewById(R.id.btnDice);

			btnDice.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					String s = getString(R.string.txtDice);
					txtDice.setText(s);

					Resources res = getResources();

					final AnimationDrawable animDraw = (AnimationDrawable) res
							.getDrawable(R.drawable.animation_drawable);

					imgDice.setImageDrawable(animDraw);
					animDraw.start();

					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								Thread.sleep(5000);
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
							animDraw.stop();
							handler.sendMessage(handler.obtainMessage());
						}
					}).start();
				}
			});

			return rootView;
		}
	}

}
