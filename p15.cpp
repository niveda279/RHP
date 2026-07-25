#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#define ll long long int
using namespace std;
int find(vector<int>&ldr,int node){
	if(ldr[node]!=node){
		ldr[node]=find(ldr,ldr[node]);
	}
	return ldr[node];
}
void join(vector<int> &ldr,int lt,int rt){
	int ldrlt=find(ldr,lt);
	int ldrrt=find(ldr,rt);
	ldr[ldrrt]=ldrlt;
}
void solve(){
	int n,m;
	cin>>n>>m;
	vector<int>ldr(n+1);
	for(int i=1;i<=n;i++)ldr[i]=i;
	for(int e=1;e<=m;e++){
		int lt,rt;
		cin>>lt>>rt;
		join(ldr,lt,rt);
	}
	set<int>st;
	for(int i=1;i<=n;i++){
		st.insert(find(ldr,i));
	}
	cout<<st.size();
}
int main(){
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	int tc=1;
	while(tc--) solve();
}