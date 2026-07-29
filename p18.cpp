#include<iostream>
#include<vector>
#include<map>
using namespace std;

int find(vector<int>& ldr,int node){
    if(ldr[node]!=node)
        ldr[node]=find(ldr,ldr[node]);
    return ldr[node];
}

void join(vector<int>& ldr,int lt,int rt){
    ldr[find(ldr,lt)] = find(ldr,rt);
}

void solve(){
    int n,q;
    cin>>n>>q;

    vector<int> a(n+1),ldr(n+1);
    map<int,int> vlm,lvm;

    for(int i=1;i<=n;i++){
        cin>>a[i];
        if(vlm.find(a[i])==vlm.end()){
            vlm[a[i]]=i;
            lvm[i]=a[i];
        }
        ldr[i]=vlm[a[i]];
    }

    while(q--){
        int type;
        cin>>type;

        if(type==1){
            int x,y;
            cin>>x>>y;

            if(x==y) continue;
            if(vlm.find(x)==vlm.end()) continue;

            if(vlm.find(y)!=vlm.end()){
                int rx=find(ldr,vlm[x]);
                int ry=find(ldr,vlm[y]);

                if(rx!=ry){
                    ldr[rx]=ry;
                    lvm.erase(rx);
                }

                vlm.erase(x);
            }
            else{
                int rx=find(ldr,vlm[x]);
                lvm[rx]=y;
                vlm.erase(x);
                vlm[y]=rx;
            }
        }
        else{
            int idx;
            cin>>idx;
            cout<<lvm[find(ldr,ldr[idx])]<<'\n';
        }
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int tc;
    cin>>tc;

    for(int i=1;i<=tc;i++){
        cout<<"Case "<<i<<":\n";
        solve();
    }
}