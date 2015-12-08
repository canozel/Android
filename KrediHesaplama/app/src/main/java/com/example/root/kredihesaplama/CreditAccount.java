package com.example.root.kredihesaplama;

/**
 * Created by Salih Can ÖZEL on 4.12.2015.
 */
public class CreditAccount {
    private double mountOfLoan,maturity;
    private double bankRate = 1;
    private int bankID;
    private float TAX = (float) 1.20;

    CreditAccount(double mountOfLoan,double maturity, double bankRate){
        this.mountOfLoan = mountOfLoan;
        this.maturity = maturity;
        this.bankRate = bankRate;
    }

    CreditAccount(double mountOfLoan, double maturity){
        this.mountOfLoan = mountOfLoan;
        this.maturity = maturity;
    }

    public double calculate(){
        return mountOfLoan * bankRate * Math.pow((1 + bankRate), maturity) / (Math.pow((1 + bankRate), maturity) - 1);
    }

    public void setBankRate(Boolean creditType) {
        this.bankRate = bankRate * 0.01 * (creditType ? 1 : TAX);
    }

    public void setBankRateWithBankName(int bankID) {
        this.bankID = bankID;
        switch (bankID){
            case 0:
                bankRate = 1.2 * 0.01;
                break;
            case 1:
                bankRate = 1.1 * 0.01;
                break;
            case 2:
                bankRate = 1.3 * 0.01;
                break;
            case 3:
                bankRate = 1.23 * 0.01;
                break;
            case 4:
                bankRate = 1.02 * 0.01;
                break;
            default:
                bankRate = 1 * 0.01;
                break;
        }
    }
}
