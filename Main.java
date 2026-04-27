import java.util.Scanner;

class Main {
    record Beam(int r, int c, int l, char d) {}

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
  
        // Number of test cases.
        int T = input.nextInt();

        for (int t = 0; t < T; t++) {
            // The number of rows and the number of columns of the rune grid
            // Rows and columns are identified by integers, ranging from 0 to, respectively, R − 1 and C − 1
            // The cell in the upper left corner is in row 0 and column 0.
            int R = input.nextInt();
            int C = input.nextInt();

            //The stabilising corridor comprises N columns and L is the leftmost (westernmost) chosen column
            int N = input.nextInt();
            int L = input.nextInt();

            // Number of magic beams
            int B = input.nextInt();

            // Magic beam, identified by i, occupies the cell in row ri and column ci, has length li, starting from (ri, ci), 
            // extends in the direction specified by di and travels in that direction.
            Beam[] beams = new Beam[B];
            for (int i = 0; i < B; i++) {
                beams[i] = new Beam(input.nextInt(), input.nextInt(), input.nextInt(), input.next().charAt(0));
            }

            //String output = Solver();
            //System.out.println(output);
        }
    }
}