package org.himadri.practice.java_practice.matrix;
import java.io.*;


import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



/**
 * 
 * @author 
 * You are given a 2D matrix of dimension  and a positive integer . You have to rotate the matrix  times and print the resultant matrix. Rotation should be in anti-clockwise direction.

Rotation of a  matrix is represented by the following figure. Note that in one rotation, you have to shift elements by one step only.

matrix-rotation

It is guaranteed that the minimum of m and n will be even.

As an example rotate the Start matrix by 2:

Start         First           Second
 1 2 3 4        2  3  4  5      3  4  5  6
12 1 2 5  ->   1  2  3  6 ->   2  3  4  7
11 4 3 6      12  1  4  7       1  2  1  8
10 9 8 7      11 10  9  8     12 11 10  9
Function Description

Complete the matrixRotation function in the editor below. It should print the resultant 2D integer array and return nothing.

matrixRotation has the following parameter(s):

matrix: a 2D array of integers
r: an integer that represents the rotation factor
Input Format

The first line contains three space separated integers, , , and , the number of rows and columns in , and the required rotation.
The next  lines contain  space-separated integers representing the elements of a row of .

Constraints





Output Format

Print each row of the rotated matrix as space-separated integers on separate lines.

Sample Input

Sample Input #01

4 4 2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
Sample Output #01

3 4 8 12
2 11 10 16
1 7 6 15
5 9 13 14
Explanation #01

The matrix is rotated through two rotations.

 1  2  3  4      2  3  4  8      3  4  8 12
 5  6  7  8      1  7 11 12      2 11 10 16
 9 10 11 12  ->  5  6 10 16  ->  1  7  6 15
13 14 15 16      9 13 14 15      5  9 13 14
Sample Input #02

5 4 7
1 2 3 4
7 8 9 10
13 14 15 16
19 20 21 22
25 26 27 28
Sample Output #02

28 27 26 25
22 9 15 19
16 8 21 13
10 14 20 7
4 3 2 1
Explanation 02

The various states through 7 rotations:

1  2  3  4      2  3  4 10    3  4 10 16    4 10 16 22
7  8  9 10      1  9 15 16    2 15 21 22    3 21 20 28
13 14 15 16 ->  7  8 21 22 -> 1  9 20 28 -> 2 15 14 27 ->
19 20 21 22    13 14 20 28    7  8 14 27    1  9  8 26
25 26 27 28    19 25 26 27    13 19 25 26   7 13 19 25

10 16 22 28    16 22 28 27    22 28 27 26    28 27 26 25
 4 20 14 27    10 14  8 26    16  8  9 25    22  9 15 19
 3 21  8 26 ->  4 20  9 25 -> 10 14 15 19 -> 16  8 21 13
 2 15  9 25     3 21 15 19     4 20 21 13    10 14 20  7
 1  7 13 19     2  1  7 13     3  2  1  7     4  3  2  1
Sample Input #03

2 2 3
1 1
1 1
Sample Output #03

1 1
1 1
Explanation #03

All of the elements are the same, so any rotation will repeat the same matrix.
 *
 */

public class MatrixLayerRotationHackerrank {

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int rot) {
        int R = matrix.size();
        int C = matrix.get(0).size();
        int [][]mat1 = new int[R][C];
        for (int r=0; r<R; r++){
            for (int c=0; c<C;c++){
                mat1[r][c] = matrix.get(r).get(c);
            }
        }
        int [][]mat2 = new int[R][C];
        int rs = 0; //row start
        int cs = 0; //col start
        int re = R-1; //row end
        int ce = C-1; //col end

        //we will keep all the elements of a loop in a temp array then rotate the array and again put the elements back in new mat.
        while (true){
            if (rs>=re || cs>=ce){
                break;
            }
            int te; //total elements in a loop
            te = ((re-rs+1)*2)+((ce-cs+1)*2)-4; //as corner elements will be duplicated.
            int []arr = new int[te];
            int index=0;
            //storing the elements in an array.
            //traversing top row;
            for (int k=cs; k<=ce; k++){
                arr[index++] = mat1[rs][k];
            }
            rs++;
            //traversing last col
            for (int k=rs; k<=re; k++){
                arr[index++]=mat1[k][ce];
            }
            ce--;
            //traversing last row
            if (cs<=ce){
                for (int k=ce; k>=cs; k--){
                    arr[index++] = mat1[re][k];
                }
            }
            re--;
            //traversing first col
            if (rs<=re){
                for (int k=re; k>=rs; k--){
                    arr[index++]=mat1[k][cs];
                }
            }
            cs++;
            
            //restoring the rs, re, cs, ce
            rs--;
            cs--;
            re++;
            ce++;

            //rotate the array
            arr = rotate(arr, rot);

            //start filling the new mat
            index=0;
            //traversing top row;
            for (int k=cs; k<=ce; k++){
                mat2[rs][k]=arr[index++];
            }
            rs++;
            //traversing last col
            for (int k=rs; k<=re; k++){
                mat2[k][ce]=arr[index++];
            }
            ce--;
            //traversing last row
            if (cs<=ce){
                for (int k=ce; k>=cs; k--){
                    mat2[re][k]=arr[index++];
                }
            }
            re--;
            //traversing first col
            if (rs<=re){
                for (int k=re; k>=rs; k--){
                    mat2[k][cs]=arr[index++];
                }
            }
            cs++;
            

        }
        printMat(mat2, R, C);
    }

    public static int[] rotate(int []arr, int rot){
        int len = arr.length;
        rot = rot%len;
        arr = reverse(arr, 0, rot-1);
        arr = reverse(arr, rot, len-1);
        arr = reverse(arr, 0, len-1);
        return arr;
    }

    public static int [] reverse(int []arr, int s, int e){
        while (s<e){
            int t = arr[s];
            arr[s]=arr[e];
            arr[e]=t;
            s++;
            e--;
        }
        return arr;
    }


    public static void printMat(int[][]mat, int R, int C){
        for (int r=0; r<R; r++){
            if (r!=0){
                System.out.println();
            }
            for (int c=0; c<C;c++){
                if (c!=0){
                    System.out.print(" ");
                }
                System.out.print(mat[r][c]);
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

//        IntStream.range(0, m).forEach(i -> {
//            try {
//                matrix.add(
//                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                        .map(Integer::parseInt)
//                        .collect(toList())
//                );
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
