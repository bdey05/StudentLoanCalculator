package com.vogella.android.studentloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText loanAmount, interestRate, loanFees, loanTerm;
    private Button Calculate;
    private TextView monthlyPayment, totalInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        loanAmount = (EditText) findViewById(R.id.loanAmountInput);
        interestRate = (EditText) findViewById(R.id.interestRateInput);
        loanTerm = (EditText) findViewById(R.id.loanTermInput);
        loanFees = (EditText) findViewById(R.id.loanFeesInput);
        Calculate = (Button) findViewById(R.id.calculate);
        totalInterest = (TextView) findViewById(R.id.totalAmountOfInterestOutput);
        monthlyPayment = (TextView) findViewById(R.id.monthlyPaymentOutput);

        Calculate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(!(loanAmount.getText().toString().isEmpty() || interestRate.getText().toString().isEmpty() || loanTerm.getText().toString().isEmpty() || loanFees.getText().toString().isEmpty())){

                    String value1=loanAmount.getText().toString();
                    String value2=interestRate.getText().toString();
                    String value3=loanTerm.getText().toString();
                    String value4=loanFees.getText().toString();
                    double a=Integer.parseInt(value1);
                    double b=Integer.parseInt(value2);
                    double intRateDec = b/100;
                    double c=Integer.parseInt(value3);
                    double d=Integer.parseInt(value4);
                    double allInterest = (a+d) * Math.pow((1 + intRateDec), c) - (a+d);
                    allInterest = Math.round(allInterest*100)/100;
                    double monthly = (a + d) * Math.pow(1 + intRateDec, c)/(12*c);
                    monthly = Math.round(monthly*100)/100;
                    totalInterest.setText(String.valueOf(allInterest));
                    monthlyPayment.setText(String.valueOf(monthly));
                }
                else{
                    Toast error = Toast.makeText(MainActivity.this, "Make sure all inputs are filled out!", Toast.LENGTH_SHORT);
                    View v = error.getView();
                    v.setBackgroundColor(Color.rgb(245, 62, 49));

                    error.show();
                }
            }
        });
    }
}