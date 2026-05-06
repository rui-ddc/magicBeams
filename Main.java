import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  
        // Number of test cases.
        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            // The number of rows and the number of columns of the rune grid
            // Rows and columns are identified by integers, ranging from 0 to, respectively, R − 1 and C − 1
            // The cell in the upper left corner is in row 0 and column 0.
            String[] rc = in.readLine().split(" ");
            int R = Integer.parseInt(rc[0]);
            int C = Integer.parseInt(rc[1]);

            // The stabilising corridor comprises N columns and L is the leftmost (westernmost) chosen column
            String[] nl = in.readLine().split(" ");
            int N = Integer.parseInt(nl[0]);
            int L = Integer.parseInt(nl[1]);

            // Number of magic beams
            int B = Integer.parseInt(in.readLine());

            // Magic beam, identified by i + 1, occupies the cell in row ri and column ci, has length li, starting from (ri, ci), 
            // extends in the direction specified by di and travels in that direction.
            Beam[] beams = new Beam[B];
            for (int i = 0; i < B; i++) {
                String[] b = in.readLine().split(" ");
                int r = Integer.parseInt(b[0]);
                int c = Integer.parseInt(b[1]);
                int len = Integer.parseInt(b[2]);
                char dir = b[3].charAt(0);
                
                // CAUTION: Remember beamId is i + 1
                beams[i] = new Beam(r, c, len, dir);
            }

            MagicBeams mb = new MagicBeams(R, C, N, L, B, beams);
            System.out.println(mb.solve());
        }
    }
}