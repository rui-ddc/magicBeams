import java.util.Scanner;

class Main {
    enum Direction {
        N,
        E,
        S,
        W
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
  
        // Number of test cases.
        System.out.println("T");
        int T = input.nextInt();
        System.out.println(T);

        for (int t = 0; t < T; t++) {
            // The number of rows and the number of columns of the rune grid
            // Rows and columns are identified by integers, ranging from 0 to, respectively, R − 1 and C − 1
            // The cell in the upper left corner is in row 0 and column 0.
            System.out.println("R");
            System.out.println(input.nextInt());
            System.out.println("C");
            System.out.println(input.nextInt());

            //The stabilising corridor comprises N columns and L is the leftmost (westernmost) chosen column
            System.out.println("N");
            System.out.println(input.nextInt());
            System.out.println("L");
            System.out.println(input.nextInt());

            // Number of magic beams
            System.out.println("B");
            int B = input.nextInt();
            System.out.println(B);

            // Magic beam, identified by i, occupies the cell in row ri and column ci, has length li, starting from (ri, ci), 
            // extends in the direction specified by di and travels in that direction.
            for (int i = 0; i < B; i++) {
                System.out.println("ri");
                System.out.println(input.nextInt());
                System.out.println("ci");
                System.out.println(input.nextInt());
                System.out.println("li");
                System.out.println(input.nextInt());
                System.out.println("di");
                System.out.println(input.next().charAt(0));
            }
        }
    }
}