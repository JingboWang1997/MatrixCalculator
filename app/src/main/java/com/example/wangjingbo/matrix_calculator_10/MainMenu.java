package com.example.wangjingbo.matrix_calculator_10;

import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;


public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TextView text = (TextView) findViewById(R.id.text);
        String inputText = "you currently have " + Matrix.theBook.size() + " matrix";
        text.setText(inputText);

        final Intent display = new Intent(this, DisplayMatrix.class); // display the next action

        final Intent makeM = new Intent(this, SetDimension.class); //create new matrix
        Button create = (Button) findViewById(R.id.makeMatrix);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(makeM);
            }
        });

        final Button show = (Button) findViewById(R.id.showAll); // show all the current existing matrix
        show.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("command", "SHOW");
                display.putExtras(b);
                startActivity(display);
            }
        });

        final Button multiply = (Button) findViewById(R.id.multiply); // multiply matrices
        multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("command", "MULT");
                display.putExtras(b);
                startActivity(display);
            }
        });

        final Button rowEchelon = (Button) findViewById(R.id.rowEchelon); // row echelon
        rowEchelon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("command", "ROW");
                display.putExtras(b);
                startActivity(display);
            }
        });

        final Button reducedRowEchelon = (Button) findViewById(R.id.reducedRowEchelon); // reduced row echelon
        reducedRowEchelon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("command", "REDUCED");
                display.putExtras(b);
                startActivity(display);
            }
        });

        final Button inverse = (Button) findViewById(R.id.inverse); // inverse matrix
        inverse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("command", "INVERSE");
                display.putExtras(b);
                startActivity(display);
            }
        });

        final Button lu = (Button) findViewById(R.id.lu); // LU factorization
        lu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("command", "LU");
                display.putExtras(b);
                startActivity(display);
            }
        });






    }
}
