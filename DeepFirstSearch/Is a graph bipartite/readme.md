# Check whether a graph is bipartite.

For example.

![Test Image 3](https://github.com/red574890/Algorithm/blob/master/DeepFirstSearch/Is%20a%20graph%20bipartite/Bipartite_example.png)

Every red vertice is adjacent to white vertice, so we can split them into two sections as following.



<img src="https://github.com/red574890/Algorithm/blob/master/DeepFirstSearch/Is%20a%20graph%20bipartite/bipartite%20spilt.png" width="200" height="200" />


## Method:

1. In graphwithattribute, add colors for each vertice.
2. During DFS, check whether the edge and itself are different colors.

 
