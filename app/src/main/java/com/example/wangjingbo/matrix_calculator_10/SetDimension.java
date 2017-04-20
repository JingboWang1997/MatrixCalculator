package com.example.wangjingbo.matrix_calculator_10;

import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import java.io.Serializable;


public class SetDimension extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_dimension);

        final Intent intent = new Intent(this, MatrixInput.class);
        final Bundle bundle = new Bundle();

        Button button = (Button) findViewById(R.id.InitiateMatrix);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText rowText = (EditText) findViewById(R.id.row);
                EditText colText = (EditText) findViewById(R.id.column);
                String r = rowText.getText().toString();
                int row = Integer.parseInt(rowText.getText().toString());
                int col = Integer.parseInt(colText.getText().toString());
                Matrix matrix = new Matrix(row, col);
                bundle.putSerializable("matrix", (Serializable) matrix);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
