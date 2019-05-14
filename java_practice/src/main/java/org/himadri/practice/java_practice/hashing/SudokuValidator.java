package org.himadri.practice.java_practice.hashing;

import java.util.List;

// Note:
//A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

public class SudokuValidator {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isValidSudoku(final List<String> A) {
        int len = A.size();
        int boxSize = (int)Math.sqrt(len);
        boolean [][] rowmat = new boolean[len][len+1];
        boolean [][] colmat = new boolean[len][len+1];
        boolean [][][] boxmat = new boolean[boxSize][boxSize][len+1];
        
        for (int r=0; r<len; r++){
            char[] arr = A.get(r).toCharArray(); //if the size of the matrix is > 9 then we can have some space pattern to separate the numbers. then we could use the split function.
            for (int c=0; c<len; c++){
                char ch = arr[c]; //for the given input we have to take it as a character. but for generic you can take it as a string
                if (ch=='.'){
                    continue;
                }
                if (rowmat[r][ch-'0']){
                    return 0;
                }
                if (colmat[c][ch-'0']){
                    return 0;
                }
                if (boxmat[r/boxSize][c/boxSize][ch-'0']){
                    return 0;
                }
                rowmat[r][ch-'0']=true;
                colmat[c][ch-'0']=true;
                boxmat[r/boxSize][c/boxSize][ch-'0']=true;
            }
        }
        
        return 1;
    }
}