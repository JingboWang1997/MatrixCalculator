package com.example.wangjingbo.matrix_calculator_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.support.v4.view.ViewPager.LayoutParams;
import android.graphics.Color;
import android.text.InputType;
import android.content.Intent;


/**
 * Created by wangjingbo on 9/30/16.
 */

public class MatrixInput extends AppCompatActivity{

    private int row;
    private int col;
    private Matrix matrix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_input);

        matrix = (Matrix) getIntent().getSerializableExtra("matrix");
        row = matrix.getRows();
        col = matrix.getCols();

        LinearLayout layoutParent = (LinearLayout) findViewById(R.id.matrix_input_changeable);
        layoutParent.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < row; i++) {
            LinearLayout layoutChild = new LinearLayout(this);
            layoutChild.setOrientation(LinearLayout.HORIZONTAL);
            layoutChild.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutChild.setGravity(Gravity.CENTER);
            layoutParent.addView(layoutChild);

            for (int j = 0; j < col; j++) {
                EditText entry = new EditText(this);
                entry.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, LayoutParams.MATCH_PARENT);
                params.setMargins(10,10,10,10);
                entry.setLayoutParams(params);
                entry.setGravity(Gravity.CENTER);
                entry.setBackgroundColor(Color.GRAY);
                entry.setId((i+1)*10 + (j+1));
                layoutChild.addView(entry);
            }
        }

        final Intent intentToMenu = new Intent(this, MainMenu.class);
        Button button = (Button) findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        int getID = (i + 1) * 10 + (j + 1);
                        EditText getEntry = (EditText) findViewById(getID);
                        String ent = getEntry.getText().toString();
                        matrix.setEntry(i + 1, j + 1, Double.parseDouble(ent));
                    }
                }
                Matrix.theBook.add(matrix);
                startActivity(intentToMenu);
            }
        });









    }
}


