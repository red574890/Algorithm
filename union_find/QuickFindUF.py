class QuickFindUF():
  
    def __init__(self,N):
        if(type(N)!=int):
            print("Size must be integer")
        else:
            self.id_array=list(range(1,N+1))
            self.size=N

    def connected(self,p,q):
        if(type(p)!=int or type(q)!=int):
            print("Input must be integer")
        else:
            return self.id_array[p-1]==self.id_array[q-1]
        
    def union(self,p,q):
        if(type(p)!=int or type(q)!=int):
            print("Input must be integer")
        else:
            pid=self.id_array[p-1]
            qid=self.id_array[q-1]
            for i in range(0,len(self.id_array)):
                if(self.id_array[i]==pid):
                    self.id_array[i]=qid
                    
    def find(self,p):
        if(type(p)!=int):
            print("Input must be integer")
        else:
            return(self.id_array[p-1])
        
    def size(self):   #show size
        return(self.size)
    
    def printarray(self): #show array
        print(self.id_array)
