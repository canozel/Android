package com.example.root.kredihesaplama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class DataCalculate extends AppCompatActivity {

    private EditText mountOfLoan,maturity,bankRate;
    private Spinner spnBank;
    private ArrayAdapter adapter;
    private RadioGroup rgCreditType;
    private TextView result;

    private int selection;
    private boolean creditType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_calculate);


        mountOfLoan = (EditText) findViewById(R.id.etAmountOfLoan);
        maturity = (EditText)findViewById(R.id.etMaturity);
        bankRate = (EditText)findViewById(R.id.etBankRate);

        result = (TextView) findViewById(R.id.result);

        rgCreditType = (RadioGroup) findViewById(R.id.rgCreditType);

        spnBank = (Spinner) findViewById(R.id.spnBank);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.bankName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBank.setAdapter(adapter);

        spnBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

            CreditAccount credit = new CreditAccount(mountOfLoanVar,maturityVar);
            credit.setBankRateWithBankName(selection);
            credit.setBankRate(creditType);

            result.setText("Aylık Taksit = " + String.format("%.2f",credit.calculate()));
        } catch (NumberFormatException e){
            result.setText("Değer girilmemiş");
        }
    }
}
