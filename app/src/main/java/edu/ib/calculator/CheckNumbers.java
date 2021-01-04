package edu.ib.calculator;

public class CheckNumbers {

    public boolean checkIfZero(String test){
        boolean result=false;

        if (test.equals("0")){
            result=true;
        }

        return result;
    }

    public boolean checkIfDot(String test){
        boolean result=false;

        if (test.contains(".")){
            result=true;
        }

        return result;
    }
}
