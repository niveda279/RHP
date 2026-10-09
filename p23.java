import java.util.*;
class Sofa {
    int fsr, fsc, ssr, ssc;
    char dir;
    int moves;
    public Sofa(int fsr, int fsc, int ssr, int ssc, char d, int m) {
        this.fsr = fsr;
        this.fsc = fsc;
        this.ssr = ssr;
        this.ssc = ssc;
        this.dir = d;
        this.moves = m;
    }
}
public class Main {
    static int M, N;
    static char[][] grid;
    static boolean[][][] vis;
    static boolean free(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N && grid[r][c] != 'H';
    }
    static boolean canAdd(int r1, int c1, int r2, int c2, char dir, boolean[][][] vis) {
        if (!free(r1, c1) || !free(r2, c2)) return false;
        int r = Math.min(r1, r2);
        int c = Math.min(c1, c2);
        int d = (dir == 'H') ? 0 : 1;
        if (vis[r][c][d]) return false;
        vis[r][c][d] = true;
        return true;
    }
    static boolean free2x2(int r, int c) {
        return free(r, c) && free(r, c + 1) && free(r + 1, c) && free(r + 1, c + 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        grid = new char[M][N];
        ArrayList<int[]> start = new ArrayList<>();
        ArrayList<int[]> target = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.next().charAt(0);
                if (grid[i][j] == 's') start.add(new int[]{i, j});
                if (grid[i][j] == 'S') target.add(new int[]{i, j});
            }
        }
        int sr1 = start.get(0)[0], sc1 = start.get(0)[1];
        int sr2 = start.get(1)[0], sc2 = start.get(1)[1];
        int tr1 = target.get(0)[0], tc1 = target.get(0)[1];
        int tr2 = target.get(1)[0], tc2 = target.get(1)[1];
        char startDir = (sr1 == sr2) ? 'H' : 'V';
        char targetDir = (tr1 == tr2) ? 'H' : 'V';
        if (startDir == 'H' && sc1 > sc2) {
            int t = sc1; sc1 = sc2; sc2 = t;
        }
        if (startDir == 'V' && sr1 > sr2) {
            int t = sr1; sr1 = sr2; sr2 = t;
        }
        if (targetDir == 'H' && tc1 > tc2) {
            int t = tc1; tc1 = tc2; tc2 = t;
        }
        if (targetDir == 'V' && tr1 > tr2) {
            int t = tr1; tr1 = tr2; tr2 = t;
        }
        vis = new boolean[M][N][2];
        Queue<Sofa> q = new LinkedList<>();
        int sd = (startDir == 'H') ? 0 : 1;
        vis[sr1][sc1][sd] = true;
        q.add(new Sofa(sr1, sc1, sr2, sc2, startDir, 0));
        while (!q.isEmpty()) {
            Sofa s = q.poll();
            int r1 = s.fsr, c1 = s.fsc;
            int r2 = s.ssr, c2 = s.ssc;
            if (r1 == tr1 && c1 == tc1 && s.dir == targetDir) {
                System.out.println(s.moves);
                return;
            }
            int nr, nc;
            if (s.dir == 'H') {
                nr = r1 - 1;
                if (free(nr, c1) && free(nr, c2) && canAdd(nr, c1, nr, c2, 'H', vis))
                    q.add(new Sofa(nr, c1, nr, c2, 'H', s.moves + 1));
            } else {
                nr = r1 - 1;
                if (free(nr, c1) && canAdd(nr, c1, r1, c1, 'V', vis))
                    q.add(new Sofa(nr, c1, r1, c1, 'V', s.moves + 1));
            }
            if (s.dir == 'H') {
                nr = r1 + 1;
                if (free(nr, c1) && free(nr, c2) && canAdd(nr, c1, nr, c2, 'H', vis))
                    q.add(new Sofa(nr, c1, nr, c2, 'H', s.moves + 1));
            } else {
                nr = r2 + 1;
                if (free(nr, c1) && canAdd(r2, c1, nr, c1, 'V', vis))
                    q.add(new Sofa(r2, c1, nr, c1, 'V', s.moves + 1));
            }
            if (s.dir == 'H') {
                nc = c1 - 1;
                if (free(r1, nc) && canAdd(r1, nc, r1, c1, 'H', vis))
                    q.add(new Sofa(r1, nc, r1, c1, 'H', s.moves + 1));
            } else {
                nc = c1 - 1;
                if (free(r1, nc) && free(r2, nc) && canAdd(r1, nc, r2, nc, 'V', vis))
                    q.add(new Sofa(r1, nc, r2, nc, 'V', s.moves + 1));
            }
            if (s.dir == 'H') {
                nc = c2 + 1;
                if (free(r1, nc) && canAdd(r1, c2, r1, nc, 'H', vis))
                    q.add(new Sofa(r1, c2, r1, nc, 'H', s.moves + 1));
            } else {
                nc = c1 + 1;
                if (free(r1, nc) && free(r2, nc) && canAdd(r1, nc, r2, nc, 'V', vis))
                    q.add(new Sofa(r1, nc, r2, nc, 'V', s.moves + 1));
            }
            if (s.dir == 'H') {
                int left = c1;
                int right = c2;
                if (free2x2(r1 - 1, left)) {
                    if (canAdd(r1 - 1, left, r1, left, 'V', vis))
                        q.add(new Sofa(r1 - 1, left, r1, left, 'V', s.moves + 1));
                    if (canAdd(r1 - 1, right, r1, right, 'V', vis))
                        q.add(new Sofa(r1 - 1, right, r1, right, 'V', s.moves + 1));
                }
                if (free2x2(r1, left)) {
                    if (canAdd(r1, left, r1 + 1, left, 'V', vis))
                        q.add(new Sofa(r1, left, r1 + 1, left, 'V', s.moves + 1));
                    if (canAdd(r1, right, r1 + 1, right, 'V', vis))
                        q.add(new Sofa(r1, right, r1 + 1, right, 'V', s.moves + 1));
                }
            } else {
                int top = r1;
                int bottom = r2;
                if (free2x2(top, c1 - 1)) {
                    if (canAdd(top, c1 - 1, top, c1, 'H', vis))
                        q.add(new Sofa(top, c1 - 1, top, c1, 'H', s.moves + 1));
                    if (canAdd(bottom, c1 - 1, bottom, c1, 'H', vis))
                        q.add(new Sofa(bottom, c1 - 1, bottom, c1, 'H', s.moves + 1));
                }
                if (free2x2(top, c1)) {
                    if (canAdd(top, c1, top, c1 + 1, 'H', vis))
                        q.add(new Sofa(top, c1, top, c1 + 1, 'H', s.moves + 1));
                    if (canAdd(bottom, c1, bottom, c1 + 1, 'H', vis))
                        q.add(new Sofa(bottom, c1, bottom, c1 + 1, 'H', s.moves + 1));
                }
            }
        }
        System.out.println("Impossible");
    }
}
