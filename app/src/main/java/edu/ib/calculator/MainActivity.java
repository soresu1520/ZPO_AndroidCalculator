package edu.ib.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    double result=0;
    double temp=0;
    String operator="";
    boolean isOperatorPressed;

    CheckNumbers check = new CheckNumbers();
    Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClearClick(View view) {
        TextView display = (TextView) findViewById(R.id.result_txt);

        display.setText("");
        isOperatorPressed = false;
        operator = "";
        result = 0;
        temp = 0;
    }

    public void onNumberClick(View view) {
        TextView display = (TextView) findViewById(R.id.result_txt);
        Button buttonNumber = (Button) view;

        String buttonString = (String)buttonNumber.getText();
        boolean checkDisplayZero = check.checkIfZero((String)display.getText());
        boolean checkDisplayDot = check.checkIfDot((String)display.getText());

        if (isOperatorPressed == false) {
            if ((buttonString.equals("0") && checkDisplayZero == true)) {
                display.setText(display.getText());
            } else if (buttonString.equals(".") && checkDisplayDot == true) {
                display.setText(display.getText());
            } else if (checkDisplayZero == true && buttonString.equals(".") == false) {
                display.setText(buttonString);
            } else if (display.getText().equals("") && buttonString.equals(".")) {
                display.setText("0.");
            } else {
                display.setText(display.getText() + buttonString);
            }
        } else {
            if (display.getText().equals("") && buttonString.equals(".")) {
                display.setText("0.");
            } else {
                display.setText(buttonString);
            }
        }
        isOperatorPressed = false;
    }

    public void onOperatorClick(View view) {
        TextView display = (TextView) findViewById(R.id.result_txt);
        Button buttonOperator = (Button)view;

        String disp = (String)display.getText();

        if (disp.isEmpty() == false) {
            temp = Double.valueOf((String)display.getText());

            if(buttonOperator.getText().equals("+/-") || buttonOperator.getText().equals("%")){
                result = calculator.other((String)buttonOperator.getText(), temp);
                display.setText(String.valueOf(result));
                operator="";
            }
            else{
                result =  calculator.calculate(operator, result, temp);
            }
        }
        if (buttonOperator.getText().equals("=")) {
            display.setText(String.valueOf(result));
            operator="";
        }
        else{
            operator=(String)buttonOperator.getText();
        }

        isOperatorPressed = true;
    }

}