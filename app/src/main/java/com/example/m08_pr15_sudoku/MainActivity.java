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
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Taulell spinners
    static Spinner[][] spinners = new Spinner[9][9];

    //Creacio del model
    SudokuModel model = new SudokuModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Valors possibles
        String[] nombres = {"▣","1","2","3","4","5","6","7","8","9"};

        //Creacio taulell
        int rowNum = 0;
        int colNum = 0;
        TableLayout table = findViewById(R.id.table);
        //Files
        for (int a = 0; a<9; a++){
            TableRow row = new TableRow(this);
            colNum = 0;
            for (int b = 0; b<9; b++){
                //Spinner
                Spinner spinner = new Spinner(this);
                //Aspecte spinner
                if ((a > 2 && a < 6 || b > 2 && b < 6)) { //Si es centre de fila o columna
                    if (a > 2 && a < 6 && b > 2 && b < 6) { //Si es centre de taulell
                        spinner.setBackgroundColor(Color.LTGRAY);
                    } else {
                        spinner.setBackgroundColor(Color.WHITE);
                    }
                } else { //Si es cantonada
                    spinner.setBackgroundColor(Color.LTGRAY);
                }
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
                        int fila = (int) adapterView.getTag(R.id.fila);
                        int col = (int) adapterView.getTag(R.id.col);
                        //Toast.makeText(getApplicationContext(),"Spinner "+fila+","+col+" modificat",Toast.LENGTH_SHORT).show();
                    }
                    @Override public void onNothingSelected(AdapterView<?> adapterView) {} //Ignore
                });
                //Tags spinner
                spinner.setTag(R.id.fila,a);
                spinner.setTag(R.id.col,b);
                //Insercio spinner a array
                spinners[rowNum][colNum] = spinner;
                colNum++;
                row.addView(spinner);
            }
            rowNum++;
            table.addView(row);
        }//Fi creacio taulell
        Log.i("INFO", "Creant partida");
        model.creaPartida();
        for (int a = 0; a < model.cells.length; a++){
            for (int b = 0; b < model.cells[a].length; b++){
                if (model.cells[a][b] != 0){
                    spinners[a][b].setEnabled(false);
                }
            }
        }
        Log.i("INFO", "Partida creada");
        Log.i("INFO", "Refrescant GUI");
        refrescaGUI(model, nombres);
        Log.i("INFO","Loading complete");

    }//onCreate

    public void refrescaGUI(SudokuModel model, String[] spinnerOpts){
        for (int a = 0; a < model.cells.length; a++){
            for (int b = 0; b < model.cells[a].length; b++) {
                if (model.cells[a][b] != 0) {
                    spinners[a][b].setSelection(Arrays.asList(spinnerOpts).indexOf(String.valueOf(model.cells[a][b])));
                    Log.i("Value", String.valueOf(Arrays.asList(spinnerOpts).indexOf(String.valueOf(model.cells[a][b]))));
                }
            }
        }
    }

}