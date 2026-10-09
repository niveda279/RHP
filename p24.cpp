#include <bits/stdc++.h>
using namespace std;
int main() {
    int M, N;
    cin >> M >> N;
    vector<vector<char>> grid(M, vector<char>(N));
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            cin >> grid[i][j];
        }
    }
    vector<pair<int, int>> start, target;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if (grid[i][j] == 's' || grid[i][j] == 'S')
                start.push_back({i, j});
            else if (grid[i][j] == 't' || grid[i][j] == 'T')
                target.push_back({i, j});
        }
    }
    if (start.empty() || target.empty()) {
        cout << "Impossible";
        return 0;
    }
    int sx = start[0].first, sy = start[0].second;
    int tx = target[0].first, ty = target[0].second;
    queue<tuple<int, int, int, int>> q;
    vector<vector<vector<vector<int>>>> dist(
        M, vector<vector<vector<int>>>(
            N, vector<vector<int>>(
                2, vector<int>(1, -1)
            )
        )
    );
    int horizontal = (start.size() > 1 &&
                      start[0].first == start[1].first) ? 0 : 1;
    q.push({sx, sy, horizontal, 0});
    while (!q.empty()) {
        auto [x, y, dir, steps] = q.front();
        q.pop();

        if (x == tx && y == ty) {
            cout << steps;
            return 0;
        }
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && nx < M && ny >= 0 && ny < N &&
                grid[nx][ny] != 'H') {
                q.push({nx, ny, dir, steps + 1});
            }
        }
    }
    cout << "Impossible";
    return 0;
}