class Stack:
    def __init__(self):
        self.__stackList=[]
    def push(self,val):
        self.__stackList.append(val)
    def pop(self):
        val= self.__stackList[-1] 
        del self.__stackList[-1]
        return val
class AddingStack(Stack):
    def __init__(self):
        Stack.__init__(self)
        self.__sum=0
    def getSum(self):
        return self.__sum
    def push(self,val):
        self.__sum +=val
        Stack.push(self,val)
    def pop(self):
        val= Stack.pop(self)
        self.__sum-= val
        return val
stackObject= AddingStack()
for i in range(3):
    stackObject.push(i)
    print(stackObject.getSum())
print ("------------")
for i in range(3):
    print(stackObject.pop())

