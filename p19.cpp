#include <iostream>
#include <string>
using namespace std;

int main() {
    string s = "abc";
    int k = 8;
    int n = s.length();

    for (int i = 0; i < k; i++) {
        cout << s[i % n];
    }
    return 0;
}