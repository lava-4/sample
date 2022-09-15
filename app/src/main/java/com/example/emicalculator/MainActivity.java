package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText principal,interest,year;
    Button calculate;
    TextView emi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principal=findViewById(R.id.principal);
        interest=findViewById(R.id.interest);
        year=findViewById(R.id.year);
        emi=findViewById(R.id.emi);
        calculate=findViewById(R.id.calculate_btn);
        calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String pri=principal.getText().toString().trim();
        String inte=interest.getText().toString().trim();
        String yr=year.getText().toString().trim();

        if(TextUtils.isEmpty(pri))
        {
           principal.setError("Enter principal amount");
           principal.requestFocus();
           return;
        }

        if(TextUtils.isEmpty(inte))
        {
            interest.setError("Enter interest");
            interest.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(yr))
        {
            year.setError("Enter year");
            year.requestFocus();
            return;
        }

        float p=Float.parseFloat(pri);
        float i=Float.parseFloat(inte);
        float y=Float.parseFloat(yr);

        float principal=calpri(p);
        float rate=calint(i);
        float months=calmon(y);

        float dvdnt=caldvdnt(rate,months);
        float finaldiv=calfinaldiv(principal,rate,dvdnt);
        float d=caldivider(dvdnt);
        float emiresult=calemi(finaldiv,d);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String result = formatter.format(emiresult);

        emi.setText("The EMI values are :"+result);

    }

    private float calemi(float finaldiv, float d) {
        return (float) (finaldiv/d);
    }

    private float caldivider(float dvdnt) {
        return (float) (dvdnt-1);
    }

    private float caldvdnt(float rate, float months) {
        return (float) (Math.pow(1+rate,months));
    }

    private float calfinaldiv(float principal, float rate, float divide) {
        return (float) (principal*rate*divide);
    }

    private float calmon(float y) {
        return (float)(y*12);
    }

    private float calint(float i) {
        return (float) (i/12/100);
    }

    private float calpri(float p) {
        return (float) (p);
    }
}