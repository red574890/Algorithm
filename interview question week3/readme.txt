Megasort question

1.
Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n−1] is sorted and the subarray 
a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary array of length 
n (instead of 2n)?

2.
Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but 
a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.

Example:
Input array:{8,4,3,1}
Output:6
Reason: this array has the following inversions
{8,4},{8,1},{8,3},{4,1},{4,3},{3,1}


3.
Shuffling a linked list. Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.

Qucksort question
1. 
Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n bolts. The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt together, the carpenter can see which one is bigger (but the carpenter cannot compare two nuts or two bolts directly). Design an algorithm for the problem that uses at most proportional to nlogn compares (probabilistically).

2.
Selection in two sorted arrays. Given two sorted arrays a[] and b[], of lengths n1 and n2 and an integer 
0≤k<n1+n2, design an algorithm to find a key of rank k. The order of growth of the worst case running time of your algorithm should be 
logn, where n=n1+n2
Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
Version 2: k=n/2 (median).
Version 3: no restrictions.

3.
Decimal dominants. Given an array with n keys, design an algorithm to find all values that occur more than 
n/10 times. The expected running time of your algorithm should be linear.
