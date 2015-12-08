package com.example.root.kredihesaplama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class NoDataCalculate extends AppCompatActivity {

    private EditText mountOfLoan,maturity,bankRate;
    private TextView result;

    private boolean creditType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_data_calculate);


        mountOfLoan = (EditText) findViewById(R.id.etAmountOfLoan);
        maturity = (EditText) findViewById(R.id.etMaturity);
        bankRate = (EditText) findViewById(R.id.etBankRate);

        result = (TextView) findViewById(R.id.result);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbtnConsumer:
                if (checked)
                    creditType = false;
                break;
            case R.id.rbtnDwelling:
                if (checked)
                    creditType = true;
                break;
            case R.id.rbtnVehicle:
                if (checked)
                    creditType = false;
                break;
        }
    }

    public void onCalculate(View view) {
        try {
            Double mountOfLoanVar = Double.parseDouble(mountOfLoan.getText().toString());
            Double maturityVar = Double.parseDouble(maturity.getText().toString());
            Double bankRateVar = Double.parseDouble(bankRate.getText().toString());

            CreditAccount credit = new CreditAccount(mountOfLoanVar, maturityVar, bankRateVar);
            credit.setBankRate(creditType);

            result.setText("Aylık Taksit = " + String.format("%.2f", credit.calculate()));
        }catch (NumberFormatException e){
            result.setText("Değer Girilmemiş");
        }
    }
}
