#include <iostream>
#include <string>
using namespace std;

int main() {
    int N, Q;
    cin >> N >> Q;

    string S;
    cin >> S;

    int pos = 0;   
    while (Q--) {
        int t, x;
        cin >> t >> x;

        if (t == 1) {
            pos = (pos + x) % N;
        } else {
            cout << S[(pos + x - 1) % N] << endl;
        }
    }

    return 0;
}