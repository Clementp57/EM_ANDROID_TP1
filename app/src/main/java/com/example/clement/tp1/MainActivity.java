package com.example.clement.tp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, R.string.loading, duration);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void switchLocale(String loc) {
        System.out.println("Switching locale for : "+loc);
        Locale locale = new Locale(loc);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        System.out.println("New Locale => " + getResources().getConfiguration().locale);
        finish();
        startActivity(getIntent());
    }

    public void onClick(View v) {
        String lang = "fr";
        switch (v.getId()) {
            case R.id.button_EN:
                lang = "en";
                break;

            default:
                break;
        }
        switchLocale(lang);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            askForExit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void askForExit() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.action_quit)
                .setMessage(R.string.really_exit)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Stop the activity
                        MainActivity.this.finish();
                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

}
