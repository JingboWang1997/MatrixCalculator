package com.example.wangjingbo.matrix_calculator_10;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by wangjingbo on 9/30/16.
 */


public class Matrix implements Serializable{

    private double[][] matrix;
    private Scanner input;
    private int rows;
    private int cols;
    public static ArrayList<Matrix> theBook = new ArrayList<Matrix>();

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void setRow(double[] row, int rowNum) {
        matrix[rowNum-1] = row;
    }

    public double[] getRow(int rowNum) {
        return matrix[rowNum-1];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setEntry(int row, int col, double entry) {
        matrix[row-1][col-1] = entry;
    }

    public double getEntry(int row, int col) {
        return matrix[row-1][col-1];
    }

    public Matrix multiply(Matrix other) {
        Matrix result = new Matrix(getRows(), getCols());
        for(int i = 0; i < getRows(); i++) {
            for(int j = 0; j < other.getRows(); j++) {
                double num = 0;
                for(int k = 0; k < this.getCols(); k++) {
                    num += matrix[i][k] * other.matrix[k][j];
                }
                result.matrix[i][j] = num;
            }
        }
        return result;
    }

    public Matrix inverse() {
        Matrix result = new Matrix(getRows(), getCols());
        Matrix manipulated = new Matrix(getRows(), getCols() * 2);
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                manipulated.setEntry(i + 1, j + 1, getEntry(i + 1, j + 1));
            }
        }

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (i == j) {
                    manipulated.setEntry(i + 1, j + 4, 1);
                }
            }
        }

        manipulated = manipulated.RRE();


        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                result.setEntry(i + 1, j + 1, manipulated.getEntry(i + 1, j + 4));
            }
        }

        return result;
    }

    public Matrix rowEchelon() {
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                result.setEntry(i+1, j+1, getEntry(i+1, j+1));
            }
        }

        for (int i = 0; i < getCols(); i++) {
            int Vdist = 1;
            for (int j = i+1; j < getRows(); j++) {
                if (result.getEntry(j+1,i+1) != 0) {
                    double mult = (-1)*(result.getEntry(j+1,i+1)/result.getEntry(j+1-Vdist,i+1));
                    double[] newRow = new double[getCols()];
                    for (int k = 0; k < getCols(); k++) {
                        double[] pivotRow = result.getRow(j+1-Vdist);
                        double[] multRow = result.getRow(j+1);
                        newRow[k] = (pivotRow[k]*mult)+ multRow[k];
                    }
                    result.setRow(newRow, j+1);
                }
                Vdist++;
            }
        }
        return result;
    }

    public Matrix RRE() {
        Matrix Ematrix = new Matrix(getRows(), getCols());
        Ematrix = this.rowEchelon();

        for(int i = 1; i < Ematrix.getRows() + 1; i++) {
            int j = i;
            while (Ematrix.getEntry(i,j)==0) {
                if (j == getCols()) {
                    break;
                }
                j++;
            }
            if (Ematrix.getEntry(i,j)!=0 && Ematrix.getEntry(i,i)!=1) {
                double div = Ematrix.getEntry(i,j);
                for (int k = 1; k < Ematrix.getCols()+1; k++) {
                    Ematrix.setEntry(i,k,Ematrix.getEntry(i,k)/div);
                }
            }

        }

        for (int i = 2; i < getRows()+1; i++) {
            int numAbove = i-1;
            while (numAbove != 0) {
                int above = i - numAbove;
                double mult = -Ematrix.getEntry(above, i);
                for (int j = i; j < getCols()+1; j++) {
                    double add = Ematrix.getEntry(i,j)*mult;
                    Ematrix.setEntry(above, j, Ematrix.getEntry(above, j)+add);
                }
                numAbove--;
            }
        }
        return Ematrix;
    }

    public Matrix[] LU() {
        Matrix[] result = new Matrix[2];
        result[1] = rowEchelon();
        result[0] = this.multiply(result[1].inverse());

        return result;
    }



    public String toString() {
        String result = "";
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                result += getEntry(i+1,j+1) + " ";
            }
            result += "\n";
        }
        return result;
    }
}

