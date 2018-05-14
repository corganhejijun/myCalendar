package com.android.corgan.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String equation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        equation = "";
        TextView textView = findViewById(R.id.equation);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        Button backword = findViewById(R.id.backword);
        backword.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view) {
                equation = equation.substring(0, equation.length() - 2);
            }
        });
        Button clear = findViewById(R.id.clear);
        backword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                equation = "";
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
                double result = FormulaParser.getResult(equation);
                equation += "\n=" + result + "\n";
                TextView textView = findViewById(R.id.equation);
                textView.setText(equation);
            }
        });
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            if (btn.getId() == R.id.divide)
                equation += "/";
            else if (btn.getId() == R.id.multiply)
                equation += "*";
            else
                equation += btn.getText().toString();
            TextView textView = findViewById(R.id.equation);
            textView.setText(equation);
        }
    };
}
