package com.magmatart.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity{

    StringBuilder formula = new StringBuilder("");
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_add, btn_sub, btn_mul, btn_div, btn_sqr, btn_left, btn_right, btn_calc;
    TextView formulaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        formulaText = (TextView)findViewById(R.id.digit_screen);

        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_sub = (Button)findViewById(R.id.btn_sub);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_div = (Button)findViewById(R.id.btn_div);
        btn_sqr = (Button)findViewById(R.id.btn_sqr);
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_right = (Button)findViewById(R.id.btn_right);
        btn_calc = (Button)findViewById(R.id.btn_calc);

        btn_0.setOnClickListener(mClickListener);
        btn_1.setOnClickListener(mClickListener);
        btn_2.setOnClickListener(mClickListener);
        btn_3.setOnClickListener(mClickListener);
        btn_4.setOnClickListener(mClickListener);
        btn_5.setOnClickListener(mClickListener);
        btn_6.setOnClickListener(mClickListener);
        btn_7.setOnClickListener(mClickListener);
        btn_8.setOnClickListener(mClickListener);
        btn_9.setOnClickListener(mClickListener);
        btn_add.setOnClickListener(mClickListener);
        btn_sub.setOnClickListener(mClickListener);
        btn_mul.setOnClickListener(mClickListener);
        btn_div.setOnClickListener(mClickListener);
        btn_sqr.setOnClickListener(mClickListener);
        btn_left.setOnClickListener(mClickListener);
        btn_right.setOnClickListener(mClickListener);
        btn_calc.setOnClickListener(mClickListener);

        final GridLayout mGrid = (GridLayout)findViewById(R.id.grid);
        mGrid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                stretchColumns(mGrid);
                stretchRows(mGrid);
                mGrid.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_0:
                    formula.append(0);
                    break;
                case R.id.btn_1:
                    formula.append(1);
                    break;
                case R.id.btn_2:
                    formula.append(2);
                    break;
                case R.id.btn_3:
                    formula.append(3);
                    break;
                case R.id.btn_4:
                    formula.append(4);
                    break;
                case R.id.btn_5:
                    formula.append(5);
                    break;
                case R.id.btn_6:
                    formula.append(6);
                    break;
                case R.id.btn_7:
                    formula.append(7);
                    break;
                case R.id.btn_8:
                    formula.append(8);
                    break;
                case R.id.btn_9:
                    formula.append(9);
                    break;
                case R.id.btn_add:
                    formula.append('+');
                    break;
                case R.id.btn_sub:
                    formula.append('-');
                    break;
                case R.id.btn_mul:
                    formula.append('*');
                    break;
                case R.id.btn_div:
                    formula.append('/');
                    break;
                case R.id.btn_sqr:
                    formula.append('^');
                    break;
                case R.id.btn_left:
                    formula.append('(');
                    break;
                case R.id.btn_right:
                    formula.append(')');
                    break;
                case R.id.btn_calc:
                    //formula.append('=');
                    break;
            }

            formulaText.setText(formula);
        }
    };

    public void stretchColumns(GridLayout gl){
        int margin = 10 * gl.getColumnCount();
        int childWidth = (int)((gl.getWidth() - margin) / gl.getColumnCount());
        for(int i=0;i<gl.getChildCount();i++){
            View childView = gl.getChildAt(i);
            childView.setMinimumWidth(childWidth);
        }
    }

    public void stretchRows(GridLayout gl){
        int margin = 10 * gl.getRowCount();
        int childHeight = (int)((gl.getHeight() - margin) / gl.getRowCount());
        for(int i=0;i<gl.getChildCount();i++){
            View childView = gl.getChildAt(i);
            childView.setMinimumHeight(childHeight);
        }
    }
}
