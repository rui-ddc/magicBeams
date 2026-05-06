import java.util.ArrayList;
import java.util.List;

class MagicBeams {
    int R;
    int C;
    int N;
    int L;
    int B;
    Beam[] beams;

    public MagicBeams(int R, int C, int N, int L, int B, Beam[] beams) {
        this.R = R;
        this.C = C;
        this.N = N;
        this.L = L;
        this.B = B;
        this.beams = beams;
    }

    private int[][] buildRuneGrid() {
        int[][] runeGrid = new int[R][C];

        for(int i = 0; i < B; i++) {
            Beam b = beams[i];
            int rowPos = b.row();
            int colPos = b.col();
            int rowStep = 0;
            int colStep = 0;

            switch(b.dir()) {
                case 'N':
                    rowStep = -1;
                    break;
                case 'S':
                    rowStep = 1;
                    break;
                case 'E':
                    colStep = 1;
                    break;
                case 'W':
                    colStep = -1;
                    break;
            }

            for(int j = 0; j < b.len(); j++) {
                // Beam ids start from 1,...
                runeGrid[rowPos][colPos] = i + 1;
                rowPos += rowStep;
                colPos += colStep; 
            }
        }

        return runeGrid;
    }

    public String solve() {
        int[][] runeGrid = buildRuneGrid();

        // List beams that intersect the chosen columns
        List<Integer> beamsToClear = new ArrayList<>();
        boolean[] seen = new boolean[B];
        for(int r = 0; r < R; r++) {
            for(int c = L; c < L + N; c++) {
                int beamId = runeGrid[r][c];
                if(beamId > 0 && !seen[beamId - 1]) {
                    beamsToClear.add(beamId);
                    seen[beamId - 1] = true;
                }
            }
        }

        if(beamsToClear.isEmpty()) {
            return "False alarm";
        }

        return "Implement solve";
    }
}