//
//  main.cpp
//  第 2 個問題 Searchbitonicarray
//
//  Created by Hsu yun hung on 2020/9/30.
//  Copyright © 2020 Hsu yun hung. All rights reserved.
//

#include <iostream>
using namespace std;
int main(int argc, const char * argv[]) {
    //create a bitonic array
    int bit[10]={0,1,2,10,9,8,7,6,5,4};
    //find the index of biggest value
    int mid=(sizeof(bit)/sizeof(bit[0]))/2;
    int low=0;
    int high=(sizeof(bit)/sizeof(bit[0]))-1;
    int val=10;
    int f=-1;
    
    low=mid;
    
    while (low<high) {
        mid=(high+low)/2;
        if(val==bit[mid]){
            f=mid;
            break;
        }else if(val<bit[mid]){
            low=mid+1;
        }else{
            high=mid;
        }
    }

    
    mid=(sizeof(bit)/sizeof(bit[0]))/2;
    low=0;
    high=mid-1;
    
    while (low<high-1) {
        mid=(high+low)/2;
        if(val==bit[mid]){
            f=mid;
            break;
        }else if(val<bit[mid]){
            high=mid;
        }else{
            low=mid;
        }
    }
    
    cout<<f;
