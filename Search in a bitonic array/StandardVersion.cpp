//
//  main.cpp
//  Searchbitonicarray
//
//  Created by Hsu yun hung on 2020/9/30.
//  Copyright © 2020 Hsu yun hung. All rights reserved.
//

#include <iostream>
using namespace std;
int main(int argc, const char * argv[]) {
    //create a bitonic array
    int bit[10]={0,1,2,3,4,5,9,8,7,6};
    //find the index of biggest value
    int mid=(sizeof(bit)/sizeof(bit[0]))/2;

    int stop=false;
    while(stop==false){
        if(bit[mid+1]<bit[mid] && bit[mid-1]<bit[mid]){
            stop=true;
        }else if(bit[mid+1]>bit[mid]){
            mid+=1;
        }else if(bit[mid-1]>bit[mid]){
            mid-=1;
        }
    }
    
    //worst cast(upper bound)
    int svalue=6; //the value we will search for
    int h1=mid;
    int h2=mid;
    int lo1=0;
    int lo2=(sizeof(bit)/sizeof(bit[0]))-1;

    int m,m1;
    int t=-1,t1=-1;
    
    while(lo1<=h1){
        m=lo1+(h1-lo1)/2;
        if(svalue<bit[m]){h1=m-1;}
        else if (svalue>bit[m]){lo1=m+1;}
        else{t=m;
            break;
        }
    }
    

    
    while(lo2>=h2){
        m1=h2+(lo2-h2)/2;
        if(svalue<bit[m1]){h2=m1+1;}
        else if (svalue>bit[m1]){lo2=m1-1;}
        else{t1=m1;
            break;
        }
    }
    
    if (t1==-1 && t==-1) {
        cout<<"cannot find";
    }else if (t1>0){
        cout<<t1;
    }else if(t>0){
        cout<<t;
    }
    
    
    
    
    
    
    
}
