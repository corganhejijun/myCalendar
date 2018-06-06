package com.android.corgan.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String equation;
    ArrayList<String> history;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        equation = "";
        history = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            history.add("");
        TextView textView = findViewById(R.id.history);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        Button backward = findViewById(R.id.backword);
        backward.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view) {
                if (equation.equals(""))
                    return;
                equation = equation.substring(0, equation.length() - 1);
                TextView textView = findViewById(R.id.equation);
                textView.setText(equation);
            }
        });
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                equation = "";
                TextView textView = findViewById(R.id.equation);
                textView.setText("请输入：");
            }
        });
        Button d0 = findViewById(R.id.d0);
        d0.setOnClickListener(buttonClick);
        Button d1 = findViewById(R.id.d1);
        d1.setOnClickListener(buttonClick);
        Button d2 = findViewById(R.id.d2);
        d2.setOnClickListener(buttonClick);
        Button d3 = findViewById(R.id.d3);
        d3.setOnClickListener(buttonClick);
        Button d4 = findViewById(R.id.d4);
        d4.setOnClickListener(buttonClick);
        Button d5 = findViewById(R.id.d5);
        d5.setOnClickListener(buttonClick);
        Button d6 = findViewById(R.id.d6);
        d6.setOnClickListener(buttonClick);
        Button d7 = findViewById(R.id.d7);
        d7.setOnClickListener(buttonClick);
        Button d8 = findViewById(R.id.d8);
        d8.setOnClickListener(buttonClick);
        Button d9 = findViewById(R.id.d9);
        d9.setOnClickListener(buttonClick);
        Button point = findViewById(R.id.dPoint);
        point.setOnClickListener(buttonClick);
        Button plus = findViewById(R.id.plus);
        plus.setOnClickListener(buttonClick);
        Button minus = findViewById(R.id.minus);
        minus.setOnClickListener(buttonClick);
        Button multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(buttonClick);
        Button divide = findViewById(R.id.divide);
        divide.setOnClickListener(buttonClick);
        Button equal = findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (equation.length() == 0)
                    return;
                double result = FormulaParser.getResult(equation);
                history.add(equation + "=" + result);
                history.remove(0);
                TextView textView = findViewById(R.id.history);
                String str = "";
                for (String s : history){
                    str += s + "\n";
                }
                equation = "";
                textView.setText(str);
                TextView equationView = findViewById(R.id.equation);
                equationView.setText("请输入：");
            }
        });
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view;
                    if (btn.getId() == R.id.divide)
                        ifOper("/");
                    else if (btn.getId() == R.id.multiply)
                        ifOper("*");
                    else {
                        ifOper(btn.getText().toString());
                    }
                    TextView textView = findViewById(R.id.equation);
                    textView.setText(equation);
                }
    };
     void ifOper(String btnString){
         if (equation.length() == 0) {
             equation += btnString;
             return;
         }
         String lastChar = equation.substring(equation.length() - 1);
         if ("/*+-.".contains(lastChar)) {
             if ("/*+-".contains(btnString) == true) {
                 equation = equation.substring(0, equation.length() - 1) + btnString;
                 return;
             }
             if (".".equals(btnString)) {
                 return;
             }
         }
         equation += btnString;
     }
}
