package test.com.tictactoe;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Tic-tac-toe programming exercise.
 * 
 * Concept and some code based on:
 * http://lyndonarmitage.com/making-tic-tac-toe-in-android/
 * 
 * @author Gemini
 */

public class MainActivity extends Activity implements View.OnClickListener {

	private boolean turn_x = false;
	private TextView t = null;
	private Button start = null;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// setup
		t = (TextView) findViewById(R.id.caption);
		start = (Button) findViewById(R.id.button_startgame);
		
		buttons.add((Button) findViewById(R.id.button_topleft));
		buttons.add((Button) findViewById(R.id.button_topcenter));
		buttons.add((Button) findViewById(R.id.button_topright));
		
		buttons.add((Button) findViewById(R.id.button_middleleft));
		buttons.add((Button) findViewById(R.id.button_middlecenter));
		buttons.add((Button) findViewById(R.id.button_middleright));
		
		buttons.add((Button) findViewById(R.id.button_bottomleft));
		buttons.add((Button) findViewById(R.id.button_bottomcenter));
		buttons.add((Button) findViewById(R.id.button_bottomright));
	}

	public void onClick(View view) {

	}
	
	public void gridButtonListener(View view) {
		if (view instanceof Button) {
			if (!start.getText().equals(getString(R.string.restart_game)))
				start.setText(getString(R.string.restart_game));
			Button button = (Button) view;
			button.setText(turn_x ? "X" : "O");
			button.setTextColor(turn_x ? Color.parseColor("#a95156") : Color.parseColor("#51a9a4"));
			button.setEnabled(false);			
			turn_x = !turn_x;
			checkWinner();
		}
	}
	
	public void checkWinner() {
		String winner = " ";
		
		// rows
		winner = doCheck(buttons.get(0), buttons.get(1), buttons.get(2)) + winner;
		winner = doCheck(buttons.get(3), buttons.get(4), buttons.get(5)) + winner;
		winner = doCheck(buttons.get(6), buttons.get(7), buttons.get(8)) + winner;
		
		// columns
		winner = doCheck(buttons.get(0), buttons.get(3), buttons.get(6)) + winner;
		winner = doCheck(buttons.get(1), buttons.get(4), buttons.get(7)) + winner;
		winner = doCheck(buttons.get(2), buttons.get(5), buttons.get(8)) + winner;
		
		// diagonals
		winner = doCheck(buttons.get(0), buttons.get(4), buttons.get(8)) + winner;
		winner = doCheck(buttons.get(2), buttons.get(4), buttons.get(6)) + winner;
		
		// should only have either length of 0, or length of 1
		switch (winner.toLowerCase().charAt(0)) {
			case 'x':
				for (Button b: buttons)
					b.setEnabled(false);
				t.setText("The winner is x.");
				t.setTextColor(Color.parseColor("#a95156"));
				break;
			case 'o':
				for (Button b: buttons)
					b.setEnabled(false);
				t.setText("The winner is o.");
				t.setTextColor(Color.parseColor("#51a9a4"));
				break;
			default:
				if (isDraw())
					t.setText("Draw...");
				break;
		}
	}
	
	public boolean isDraw() {
		for (Button b: buttons) {
			if (b.isEnabled())
				return false;
		}
		return true;
	}
	
	public String doCheck(Button one, Button two, Button three) {
		if (one.getText().toString() == two.getText().toString()
			&& one.getText().toString() == three.getText().toString()) {
			return one.getText().toString();
		} else {
			return "";
		}
	}
	
	public void startGameListener(View view) {
		if (view instanceof Button) {
			for (Button b: buttons) {
				b.setEnabled(true);
				b.setText(getString(R.string.default_val));
				b.setTextColor(Color.parseColor("#83a7c4"));
			}
			t.setText(getString(R.string.start_text));
			t.setTextColor(Color.parseColor("#333333"));
			start.setText(getString(R.string.restart_game));
			
			turn_x = false;
		}
	}
}
