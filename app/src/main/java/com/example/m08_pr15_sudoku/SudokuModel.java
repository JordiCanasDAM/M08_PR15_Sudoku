package com.example.m08_pr15_sudoku;

import android.util.Log;

import java.util.Random;

public class SudokuModel {
    int[][] cells = new int[9][9];

    //getVal : per obtenir una dada del model.
    public int getVal(int row, int col){
        return cells[row][col];
    }

    //setVal : per intentar canviar un número de la partida de Sudoku.
    // Si no pot settejar-lo (el valor és incorrecte),
    // ens retorna el valor -1 (el 0 el reservem per a la cel·la buida.
    public int setVal(int row, int col, int val){
        int previousVal = cells[row][col];
        cells[row][col] = val;
        if (!comprovaFila(row)){cells[row][col] = previousVal; return -1;};
        if (!comprovaCol(col)){cells[row][col] = previousVal; return -1;};
        if (!comprovaQuad(row,col)){cells[row][col] = previousVal; return -1;};
        return 0;
    }

    //comprovaFila : comprova que la fila indicada als paràmetres compleix
    // les normes del Sudoku.
    public boolean comprovaFila(int row){
        Boolean valid = true;
        for (int a = 0; a<cells[row].length; a++){
            for (int b = 0; b<cells[row].length; b++){
                if (a != b && cells[row][a] != 0){
                    if (cells[row][a] == cells[row][b]){valid = false; Log.i("ROW","NO");}
                }
            }
        }
        Log.i("ROW","YES");
        return valid;
    }

    //comprovaCol : ídem per la columna.
    public boolean comprovaCol(int col){
        boolean valid = true;
        for (int a = 0; a < cells.length; a++){
            for (int b = 0; b < cells.length; b++){
                if (a != b && cells[b][col] != 0){
                    if (cells[a][col] == cells[b][col]){valid = false;Log.i("COL","NO");}
                }
            }
        }
        Log.i("COL","YES");
        return valid;
    }

    //comprovaQuad : ídem pel quadrant.
    public boolean comprovaQuad(int row, int col){
        boolean valid = true;
        int minRow = 0;
        int minCol = 0;
        //Determina la cantonada superior esquerra del quadrant
        if (row < 3){
            minRow = 0;
            if (col<3) {minCol = 0;}
            else if (col < 6){minCol = 3;}
            else {minCol = 6;}
        }else if (row < 6) {
            minRow = 3;
            if (col<3) {minCol = 0;}
            else if (col < 6){minCol = 3;}
            else {minCol = 6;}
        } else {
            minRow = 6;
            if (col<3) {minCol = 0;}
            else if (col < 6){minCol = 3;}
            else {minCol = 6;}
        }
        //Comprova els valors del quadrant
        for (int a = minRow; a < minRow+3 ; a++){
            for (int b = minCol; b < minCol + 3; b++) {
                for (int a1 = minRow; a1 < minRow + 3; a1++) {
                    for (int b1 = minCol; b1 < minCol + 3; b1++) {
                        if (a != a1 && b != b1 && cells[a][b] != 0) {
                            if (cells[a][b] == cells[a1][b1]) {
                                Log.i("Quad", "NO");
                                valid = false;
                            }
                        }
                    }
                }
            }
        }
        Log.i("Quad", "YES");
        return valid;
    }

    //creaPartida : ens crea uns quants elements aleatoris per iniciar una
    // partida. Aquests elements aleatoris han de complir amb les regles
    // del Sudoku.
    public void creaPartida(){
        Random rng = new Random();
        int numsIntroduits = rng.nextInt(100-1);
        Log.i("Inputs",String.valueOf(numsIntroduits));
        int row = 0;
        int col = 0;
        int val = 0;
        for (int a = 0; a < numsIntroduits; a++){
            row = rng.nextInt(9-1);
            col = rng.nextInt(9-1);
            val = rng.nextInt(9-1)+1;
            setVal(row,col,val);
            while (setVal(row,col,val) == -1){
                Log.i("INFO","Regenerant nombre");
                row = rng.nextInt(9-1);
                col = rng.nextInt(9-1);
            }
        }
    }

}
