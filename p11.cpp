#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        string a, b;
        cin >> a >> b;

        int n = a.size();
        int m = b.size();

        vector<int> pa(n + 1, 0), pb(m + 1, 0);

        for (int i = 1; i <= n; i++)
            pa[i] = (pa[i - 1] + (a[i - 1] - '0')) % 10;

        for (int i = 1; i <= m; i++)
            pb[i] = (pb[i - 1] + (b[i - 1] - '0')) % 10;

        if (pa[n] != pb[m]) {
            cout << -1 << '\n';
            continue;
        }

        vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (pa[i] == pb[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        cout << dp[n][m] << '\n';
    }

    return 0;
}
