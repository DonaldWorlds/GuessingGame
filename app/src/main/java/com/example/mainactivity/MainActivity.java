package com.example.mainactivity;

import com.example.mainactivity.R;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.mainactivity.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private int theNumber;

    private int numberOfTries;

    public void checkGuess(){
        String guessText = txtGuess.getText().toString();
        String message = "";
        try{
            int guess = Integer.parseInt(guessText);
            numberOfTries++;
            if(guess < theNumber)
                message = guess + " is too low. Try again.";
            else if(guess > theNumber)
                message = guess + " is too high. Try again";
            else{
                message = guess +
                        " is correct. You win after " + numberOfTries + " tries!";
                newGame();
            }
        } catch (Exception e){
            message = "Enter a whole number between 1 and 100";
        } finally {
            lblOutput.setText(message);
            txtGuess.requestFocus();
            txtGuess.selectAll();

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    }



    public void newGame(){
        theNumber = (int)(Math.random() * 100 + 1);
        numberOfTries = 0;
    }

    private AppBarConfiguration appBarConfiguration;
private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

     txtGuess = (EditText) findViewById(R.id.txtGuess);
     btnGuess = (Button) findViewById(R.id.btnGuess);
     lblOutput = (TextView) findViewById(R.id.lblOutput);
     setTitle("My Guessing Game");
     newGame();
     btnGuess.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             checkGuess();

         }
     });
     txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
         @Override
         public boolean onEditorAction(TextView V, int actionId, KeyEvent event) {
             checkGuess();
             return true;

         }
     });


        setSupportActionBar(binding.toolbar);

    }


@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}