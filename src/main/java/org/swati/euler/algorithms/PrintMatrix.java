package org.swati.euler.algorithms;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
class PrintMatrix {
    static void printMatrix(String a[][]) {
        int row = a.length;
        int col = a[0].length;

        int itr, k = 0, l = 0, m = row, n = col;

        while (k < m && l < n) {
        /*Print first row*/
            for (itr = l; itr < n; itr++) {
                System.out.println(a[k][itr]);
            }
            k++;

        /*Print last col*/
            for (itr = k; itr < m; itr++) {
                System.out.println(a[itr][n - 1]);
            }
            n--;

        /* print last row*/
            if (k < m) {
                for (itr = n - 1; itr >= l; itr--) {
                    System.out.println(a[m - 1][itr]);
                }
                m--;
            }

        /*Print first col*/
            if (l < n) {
                for (itr = m - 1; itr >= k; itr--) {
                    System.out.println(a[itr][l]);
                }
                l++;
            }

        }
    }



    public static void main(String[] args) {
        String[][] a = {
            {"1,1", "1,2", "1,3"},
            {"2,1", "2,2", "2,3"},
            {"3,1", "3,2", "3,3"},
        };
        printMatrix(a);
    }

}