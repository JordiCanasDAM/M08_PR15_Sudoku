package com.example.m08_pr15_sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Valors possibles
        String[] nombres = {"▣","1","2","3","4","5","6","7","8","9"};

        //Creacio taulell
        TableLayout table = findViewById(R.id.table);
        boolean squareColor = true;
        //Files de quadrats
        for (int a = 0; a<3 ; a++){
            TableRow squareRow = new TableRow(this);
            //Quadrats
            for (int b = 0; b<3; b++){
                TableLayout square = new TableLayout(this);
                if (squareColor){
                    square.setBackgroundColor(Color.LTGRAY);
                    squareColor = false;
                } else {
                    square.setBackgroundColor(Color.WHITE);
                    squareColor = true;
                }
                //Files de numeros
                for (int c = 0; c<3 ; c++){
                    TableRow numRow = new TableRow(this);
                    //Numeros
                    for (int d = 0; d<3; d++){
                        //Spinner
                        Spinner spinner = new Spinner(this);
                        //Aspecte spinner
                        spinner.setBackground(null);
                        spinner.setPadding(15, 15, 15, 15);
                        //Valors spinner
                        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, nombres);
                        spinner.setAdapter(adapter);
                        //Listener spinner
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                // la posició del spinner és 'i', però també es pot buscar amb
                                String string = spinner.getSelectedItem().toString();
                                Log.i("INFO","Spinner modificat");
                            }
                            @Override public void onNothingSelected(AdapterView<?> adapterView) {}
                        });
                        //Insercio spinner
                        numRow.addView(spinner);
                    }
                    square.addView(numRow);
                }
                squareRow.addView(square);
            }
            table.addView(squareRow);
        }//Fi creacio taulell



    }
}