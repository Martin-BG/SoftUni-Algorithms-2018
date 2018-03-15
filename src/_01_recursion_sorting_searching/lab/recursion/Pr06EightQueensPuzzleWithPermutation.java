package _01_recursion_sorting_searching.lab.recursion;

// https://introcs.cs.princeton.edu/java/23recursion/Queens.java.html

public class Pr06EightQueensPuzzleWithPermutation {

    private static final int BOARD_SIDE = 8;

    public static void main(String[] args) {
        enumerate(BOARD_SIDE);
    }

    /***************************************************************************
     * Return true if queen placement q[n] does not conflict with
     * other queens q[0] through q[n-1]
     ***************************************************************************/
    private static boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n]) {
                return false;   // same column
            }
            if ((q[i] - q[n]) == (n - i)) {
                return false;   // same major diagonal
            }
            if ((q[n] - q[i]) == (n - i)) {
                return false;   // same minor diagonal
            }
        }
        return true;
    }

    /***************************************************************************
     * Prints n-by-n placement of queens from permutation q in ASCII.
     ***************************************************************************/
    private static void printQueens(int[] q) {
        int n = q.length;
        for (int aQ : q) {
            for (int j = 0; j < n; j++) {
                if (aQ == j) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    /***************************************************************************
     *  Try all permutations using backtracking
     ***************************************************************************/
    private static void enumerate(int n) {
        int[] a = new int[n];
        enumerate(a, 0);
    }

    private static void enumerate(int[] q, int k) {
        int n = q.length;
        if (k == n) {
            printQueens(q);
        } else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (isConsistent(q, k)) {
                    enumerate(q, k + 1);
                }
            }
        }
    }
}
