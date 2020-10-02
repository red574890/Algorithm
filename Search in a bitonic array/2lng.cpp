//
//  main.cpp
//  第 2 個問題 Searchbitonicarray
//
//  Created by Hsu yun hung on 2020/9/30.
//  Copyright © 2020 Hsu yun hung. All rights reserved.
//

#include <iostream>
using namespace std;

int rightsearch(int high,int low,int bit[],int val );
int leftsearch(int high,int low, int bit[],int val );

int main(int argc, const char * argv[]) {
    //create a bitonic array
    int bit[10]={0,12,11,10,9,8,7,6,5,4};
    //find the index of biggest value
    int mid;
    int low=0;
    int high=(sizeof(bit)/sizeof(bit[0]))-1;
    int val=12;
    int f1;
    int f2;
    int res=-1;
    low=0;
    
    int stop=-1;

    

    
    
    
    while (low<=high && stop==-1) {
        mid=low+((high-low)/2);
        if(val<=bit[mid]){
            f1=rightsearch(high, mid, bit, val);
            f2=leftsearch(mid, low, bit, val);
            if(f1!=-1){res=f1;}
            if(f2!=-1){res=f2;}
            stop=1;
            
        }else{
            if(bit[mid]<bit[mid+1]){  // if the current mid is not mid, search the peak 
                low=mid;
            }else{
                high=mid;
            }
        }
    }
    cout<<res;

    
    


    
    
    
    
    
    
    
    
    
}

int leftsearch(int high,int low,int bit[],int val ){ // search the left side
    int f=-1;
    int mid;
        while (low<=high) {
        mid=low+(high-low)/2;
        if(val==bit[mid]){
            f=mid;
            break;
        }else if(val<bit[mid]){
            high=mid-1;
        }else{
            low=mid+1;
        }
    }
    return f;
}

int rightsearch(int high,int low, int bit[],int val ){   //search the right side
    int f=-1;
    int mid;
    while (low<=high) {
        mid=low+(high-low)/2;
        if(val==bit[mid]){
            f=mid;
            break;
        }else if(val<bit[mid]){
            low=mid+1;
        }else{
            high=mid-1;
        }
    }
    return f;
}
