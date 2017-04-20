package com.example.wangjingbo.matrix_calculator_10;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Color;
import java.util.ArrayList;

/**
 * Created by wangjingbo on 10/5/16.
 */

public class DisplayMatrix extends Activity{

    //private ArrayList<Integer> IDList = new ArrayList<Integer>();
    private ArrayList<Button> buttons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_matrix);

        String command = getIntent().getStringExtra("command").toString();

        if (command.equals("SHOW")) {
            display();
        }
        else if (command.equals("DELETE")) {
            display();
        }
        else if (command.equals("LU")) {
            display();
        }
        else if (command.equals("INVERSE")) {
            display();
        }
        else if (command.equals("REDUCED")) {
            display();
        }
        else if (command.equals("ROW")) {
            display();
        }
        else if (command.equals("MULT")) {
            display();
        }


    }

    public void display() {
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        parentLayout.setGravity(Gravity.CENTER);

        for (int matrixNum = 0; matrixNum < Matrix.theBook.size(); matrixNum++) {
            int row = Matrix.theBook.get(matrixNum).getRows();
            int col = Matrix.theBook.get(matrixNum).getCols();

            final LinearLayout matrixLayout = new LinearLayout(this);
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            matrixLayout.setGravity(Gravity.CENTER);
            matrixLayout.setOrientation(LinearLayout.VERTICAL);
            matrixLayout.setLayoutParams(linearLayoutParams);
            parentLayout.addView(matrixLayout);

            final Button matrixTitle = new Button(this);
            LinearLayout.LayoutParams matrixTitleParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            matrixTitleParams.setMargins(0,20,0,0);
            matrixTitle.setLayoutParams(matrixTitleParams);
            matrixTitle.setText("Matrix #" + (matrixNum + 1));
            int idSetter = matrixNum;
            matrixTitle.setId(idSetter);
            //IDList.add(idSetter);
            buttons.add(matrixTitle);
            matrixLayout.addView(matrixTitle);

            for (int i = 0; i < row; i++) {
                LinearLayout layoutRow = new LinearLayout(this);
                layoutRow.setOrientation(LinearLayout.HORIZONTAL);
                layoutRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layoutRow.setGravity(Gravity.CENTER);
                matrixLayout.addView(layoutRow);

                for (int j = 0; j < col; j++) {
                    TextView entry = new TextView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, LayoutParams.MATCH_PARENT);
                    params.setMargins(10,10,10,10);
                    entry.setLayoutParams(params);
                    entry.setGravity(Gravity.CENTER);
                    entry.setBackgroundColor(Color.GRAY);
                    String text = Double.toString(Matrix.theBook.get(matrixNum).getEntry(i+1,j+1));
                    entry.setText(text);
                    layoutRow.addView(entry);
                }
            }

        }

        final Intent back = new Intent(this, MainMenu.class);

        Button next = new Button(this);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(back);
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0,40,0,20);
        next.setLayoutParams(params);
        next.setText("Done");
        next.setBackgroundColor(Color.GRAY);
        parentLayout.addView(next);

    }
}
