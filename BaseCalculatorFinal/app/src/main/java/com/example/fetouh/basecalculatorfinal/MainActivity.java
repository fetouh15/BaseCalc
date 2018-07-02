package com.example.fetouh.basecalculatorfinal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import  android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private ActionBar actionBar;
    private EditText input;
    private TextView output;
    private Button b,cB;
    private SeekBar seeki;
    private SeekBar seeko;
    private StatusBarNotification NB;




    private String output(long dec, long bin, String hex,int out)
    {
        if(dec!=0 && out ==0)
         //   return Integer.toString(dec);
        return Long.toString(dec);

        else if(dec!=0 && out ==    1)
            return Long.toHexString(dec);

        else if(dec!=0 && out ==2)
            return Long.toBinaryString(dec);

        else if(bin!=0 && out ==0)
            return Long.toString(Long.parseLong(Long.toString(bin),2));
        else if(bin!=0 && out==1)
            return Long.toString( Long.parseLong(Long.toString(bin),2), 16);
        else if(bin!=0 && out==2)
            return Long.toString(bin);
        else if(!hex.isEmpty() && out==0)
            return  Long.toString(Long.parseLong(hex,16));
        else if(!hex.isEmpty() && out==1)
            return hex;
        else if(!hex.isEmpty() && out==2)
        {   return  Long.toBinaryString(Long.parseLong(hex,16));                    }

        return "problem";




    }

    public void buttonOnclick(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
        long dec=0,bin=0;
        int out=0;
        String hex="";
        Button button=  (Button) v;
        String formathelp="";
        int maxlength=15,small=24,originalsize=30;



        output.setTextSize(originalsize);


        if(seeki.getProgress()==0)
        {

            try{  dec=Long.parseLong(input.getText().toString());}

            catch(NumberFormatException e) {
                output.setText("Invalid entry");
                return;
            }
            if(dec==0&&bin==0&&hex.isEmpty())
            {
                output.setText("0");
                return;
            }
        }
        else if(seeki.getProgress()==1)
        {    if(input.getText().toString().matches("^[0-9A-Fa-f]+$"))
            hex=input.getText().toString().toUpperCase();
        else{output.setText("Invalid entry");
            return;}

        }
        else if(seeki.getProgress()==2)
        {




            if(input.getText().toString().matches("[0-1]+"))

                bin=Long.parseLong(input.getText().toString());

            else{output.setText("Invalid entry");
                return;}
            if(dec==0&&bin==0&&hex.isEmpty())
            {
                output.setText("0");
                return;
            }




        }
        else{ return; }

        if(seeko.getProgress()==0)
        { out=0;





            formathelp=output(dec,bin,hex,out);
        if(formathelp.length()>maxlength)
output.setTextSize(small);
        output.setText(formathelp);

        }
        else if(seeko.getProgress()==1)
        {    out=1;
            formathelp=output(dec,bin,hex,out);
            if(formathelp.length()>maxlength)
                output.setTextSize(small);
            output.setText(formathelp);
        }
        else if(seeko.getProgress()==2)
        {    out=2;
            formathelp=output(dec,bin,hex,out);
            if(formathelp.length()>maxlength)
                output.setTextSize(small);
            output.setText(formathelp);
        }
        else{ return; }




    }





 public void onClick(View v) {
        switch (v.getId()) {
            case R.id.di:
// handle button A click;
                seeki.setProgress(0);

                break;
            case R.id.doh:
// handle button B click;
                seeko.setProgress(0);
                break;
            case R.id.hi:
// handle button A click;
                seeki.setProgress(1);
                break;
            case R.id.ho:
// handle button B click;
                seeko.setProgress(1);
                break;
            case R.id.bi:
// handle button A click;
                seeki.setProgress(2);
                break;
            case R.id.bo:
// handle button B click;
                seeko.setProgress(2);
                break;
            case R.id.clear:
cB.setBackground(getResources().getDrawable(R.drawable.openbintrans2));
  input.setText("");
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        cB.setBackground(getResources().getDrawable(R.drawable.closedbintrans));
                    }
                }, 400);

break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
cB=findViewById(R.id.clear);
      ActionBar AB = getSupportActionBar();

        TextView textview = new TextView(MainActivity.this);
        RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        textview.setLayoutParams(layoutparams);
       String ExternalFontPath = "res/font/binary.ttf";
        Typeface   FontLoaderTypeface = Typeface.createFromAsset(getAssets(), ExternalFontPath);
       textview.setTypeface(FontLoaderTypeface);
        textview.setText("BaseConverter");
        textview.setTextColor(Color.BLACK);
        textview.setGravity(Gravity.START);
        textview.setTextSize(20);
       AB.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        AB.setCustomView(textview);




       // AB.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#990000")));


        input=(EditText)findViewById(R.id.editText);
        output=(TextView) findViewById(R.id.textView15);
        b=(Button)findViewById(R.id.button);
        seeki=(SeekBar) findViewById(R.id.seekBar2);
        seeko=(SeekBar) findViewById(R.id.seekBar);

        seeko.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub

                //t1.setTextSize(progress);
                // Toast.makeText(getApplicationContext(), String.valueOf(progress),Toast.LENGTH_LONG).show();
                b.performClick();
            }
        });


        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    b.performClick();
                }
                return false;
            }
        });










    }



}
