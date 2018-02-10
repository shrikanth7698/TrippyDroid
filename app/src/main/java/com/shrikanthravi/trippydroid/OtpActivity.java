package com.shrikanthravi.trippydroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class OtpActivity extends AppCompatActivity {

    LinearLayout OtpLayout;
    EditText OtpET,MobileNumberET;
    Button sendContinueBtn,resendOtpBtn;
    TextWatcher textWatcherMN,textWatcherOTP;
    String mn="";
    int otpFlag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
        OtpLayout = (LinearLayout) findViewById(R.id.OtpLayout);
        OtpET = (EditText) findViewById(R.id.OTPEditText);
        MobileNumberET = (EditText) findViewById(R.id.mobileNumberEditText);
        sendContinueBtn = (Button) findViewById(R.id.sendContinueButton);
        resendOtpBtn = (Button) findViewById(R.id.resendOtpButton);
        sendContinueBtn.setText("Send");
        sendContinueBtn.setEnabled(false);
        resendOtpBtn.setVisibility(View.GONE);
        textWatcherMN = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==10){
                    sendContinueBtn.setEnabled(true);
                }
                if(s.length()<10 || s.length()>10){
                    sendContinueBtn.setEnabled(false);
                }
            }
        };

        MobileNumberET.addTextChangedListener(textWatcherMN);
        sendContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpFlag==0) {
                    MobileNumberET.setEnabled(false);
                    mn = MobileNumberET.getText().toString();
                    OtpLayout.setVisibility(View.VISIBLE);
                    sendContinueBtn.setText("Continue");
                    sendContinueBtn.setEnabled(false);
                    resendOtpBtn.setVisibility(View.VISIBLE);

                }
                else{
                    //TO DO
                    //add verification of otp when backend is done
                    Intent next = new Intent(OtpActivity.this,HomeActivity.class);
                    next.putExtra("Lang",getIntent().getStringExtra("Lang"));
                    startActivity(next);
                }
            }
        });

        textWatcherOTP = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==4){
                    sendContinueBtn.setEnabled(true);
                    otpFlag=1;
                }
                if(s.length()<4 || s.length()>4){
                    sendContinueBtn.setEnabled(false);
                }
            }
        };

        OtpET.addTextChangedListener(textWatcherOTP);
    }
}
