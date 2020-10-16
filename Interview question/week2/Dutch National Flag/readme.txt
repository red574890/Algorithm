Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
1. swap(i,j): swap the pebble in bucket i with the pebble in bucket 
2. color(i): determine the color of the pebble in bucket 

The performance requirements are as follows:

At most n calls to color().
n calls to swap().
Constant extra space.


You can see more about this question in here :https://en.wikipedia.org/wiki/Dutch_national_flag_problem

In this question, we will spilt the array in three segmentation.

Red, White, and Blue.

Therefore, we need three segmenter(index).

High, low, and current

We know that red should be lower that low, current should be white, and blue should higher than high.

The rule is 
if current is red, but current equals to low, Then current++ and low++
if current is red, but current does not equals to low, Then we swap(current,low) and low++ 
if current is white, current++.
if current is blue, we swap(current,high), and high--.


For example, we have the following list.

w ,w,r,b,w,r,b,b,r,r
l                  h
c 
if current == white, which means okay, current should move to next one
w ,w,r,b,w,r,b,b,r,r
l                  h
   c
w ,w,r,b,w,r,b,b,r,r
l                  h
     c
     
In here, current == red. Thus, we need to swap low and current, and also low plus 1.
r ,w,w,b,w,r,b,b,r,r
   l               h
     c
r ,w,w,b,w,r,b,b,r,r
   l               h
       c     
       
In here, current == blue. Thus, we need to swap high and current, and also high minus 1;
r ,w,w,r,w,r,b,b,r,b
   l             h  
       c    

r ,r,w,w,w,r,b,b,r,b
     l           h  
       c 
       
       
r ,r,w,w,w,r,b,b,r,b
     l           h  
         c
r ,r,w,w,w,r,b,b,r,b
     l           h  
           c
r ,r,r,w,w,w,b,b,r,b
       l         h  
           c  
           
r ,r,r,w,w,w,b,b,r,b
       l         h  
             c
r ,r,r,w,w,w,r,b,b,b
       l         h  
             c
r ,r,r,w,w,w,r,b,b,b
       l       h    
             c
r ,r,r,w,w,w,r,b,b,b
       l       h    
               c
               
   
   
       
       
       
       






  
 
 
 
 
