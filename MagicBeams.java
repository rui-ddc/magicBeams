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

        for(int i = 0; i < beams.length; i++) {
            Beam b = beams[i];
            int rPos = b.row();
            int cPos = b.col();
            int rStep = 0;
            int cStep = 0;

            switch(b.dir()) {
                case 'N':
                    rStep = -1;
                    break;
                case 'S':
                    rStep = 1;
                    break;
                case 'E':
                    cStep = 1;
                    break;
                case 'W':
                    cStep = -1;
                    break;
            }

            for(int j = 0; j < b.len(); j++) {
                // Beam ids start from 1,...
                runeGrid[rPos][cPos] = i + 1;
                rPos += rStep;
                cPos += cStep; 
            }
        }

        return runeGrid;
    }

    public String solve() {
        int[][] runeGrid = buildRuneGrid();

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(runeGrid[r][c] > 0) {
                    System.out.printf("%4d", runeGrid[r][c]);
                } else {
                    System.out.print("   .");
                }
            }
            System.out.println();
        }

        return "Implement solve";
    }
}