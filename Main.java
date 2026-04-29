import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

class Main {
    record Beam(int r, int c, int l, char d) {}

    private static String solve(int R, int C, int N, int L, int B, Beam[] beams) {

        int[][] occupied = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                occupied[r][c] = -1;
            }
        }

        for (int i = 0; i < beams.length; i++) {
            for (int l = 0; l < beams[i].l(); l++) {
                if (beams[i].d() == 'N') {
                    occupied[beams[i].r() - l][beams[i].c()] = i;
                } else if (beams[i].d() == 'S') {
                    occupied[beams[i].r() + l][beams[i].c()] = i;
                } else if (beams[i].d() == 'E') {
                    occupied[beams[i].r()][beams[i].c() + l] = i;
                } else if (beams[i].d() == 'W') {
                    occupied[beams[i].r()][beams[i].c() - l] = i;
                }
            }
        }

        if (true) {
            // Grid display for debugging
            for (int row = 0; row < occupied.length; row++) {
                for (int col = 0; col < occupied[row].length; col++) {
                    System.out.printf("%4d", occupied[row][col]); // width = 4 chars
                }
                System.out.println();
            }
        }

        // Check for necessary beams to move
        Set<Integer> beamsInChosenColumns  = new HashSet<>();
        for (int c = L; c < L + N; c++) {
            for (int r = 0; r < R; r++) {
                if (occupied[r][c] > -1) {
                    beamsInChosenColumns .add(occupied[r][c]);
                }
            }
        }
        // If none return Flase Alarm
        if (beamsInChosenColumns.isEmpty()) {
            return "False alarm";
        }

        // Ray tracing
        // Em vez de prints vou criar um set
        LinkedList<Integer>[] adjList = new LinkedList[B];
        for (int i = 0; i < B; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < B; i++) {
        //for (int i : beamsInChosenColumns ) {
            Set<Integer> beamsBlockingPath  = new HashSet<>();
            if (beams[i].d() == 'N') {
                for (int r = beams[i].r() - beams[i].l(); r > -1; r--) {
                    beamsBlockingPath.add(occupied[r][beams[i].c()]);
                }
            } else if (beams[i].d() == 'S') {
                for (int r = beams[i].r() + beams[i].l(); r < R; r++) {
                    beamsBlockingPath.add(occupied[r][beams[i].c()]);
                }
            } else if (beams[i].d() == 'E') {
                for (int c = beams[i].c() + beams[i].l(); c < C; c++) {
                    beamsBlockingPath.add(occupied[beams[i].r()][c]);
                }
            } else if (beams[i].d() == 'W') {
                for (int c = beams[i].c() - beams[i].l(); c > -1; c--) {
                    beamsBlockingPath.add(occupied[beams[i].r()][c]);
                }
            }

            for (int j : beamsBlockingPath) {
                if (j != -1) {
                    adjList[i].add(j);
                }
            }
        }

        for (int w = 0; w < adjList.length; w++) {
            System.out.print(w + ": ");
            if (adjList[w] == null || adjList[w].isEmpty()) {
                System.out.println("(empty)");
                continue;
            }
            for (int v : adjList[w]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        // Ok decidi que vou só criar a lista de adj para todos os nó e simplesmente
        // Uso apenas aqueles que me interessam já em formato de grafo
        // Build graph and check for cycles...?

        /*
            #1 Check which beams intercept the chosen columns
                #1.1 If no beams intercept out> "False alarm"
                #1.2 If beams intercept start creating a directed graph of dependencies

                (example)
                A can only move if B and C move
                C can move freely
                B can only move if D and E move
                D and E can move freely

                In this example, C, D and E would be graph leaves

                #2.1 If any loop gets formed at any point out> "Disaster"
`		        #2.2 If there are no loops, then remove and output all leaves in order of lowest index until the graph has no more nodes
        */

        return "Implement solver";
    }

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

            String output = solve(R, C, N, L, B, beams);
            System.out.println(output);
        }
    }
}