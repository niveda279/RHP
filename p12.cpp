#include <iostream>
#include <vector>
using namespace std;

void solve() {
    int n;
    cin >> n;

    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++)
        cin >> a[i];

    int bal = 0;
    bool ok = true;

    for (int stk = 1; stk <= n; stk++) {
        if (a[stk] >= stk) {
            bal += (a[stk] - stk);
        } else {
            int needed = stk - a[stk];

            if (bal < needed) {
                ok = false;
                break;
            }

            bal -= needed;
        }
    }

    cout << (ok ? "YES" : "NO") << endl;
}

int main() {
    solve();
    return 0;
}
