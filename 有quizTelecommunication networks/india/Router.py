#Classes and objects
#In this task we will create a class to initialize the router information and then we will check the inforamtion of the router

class MyRouter(object): #creating a class which inherts from the default "object" class
    def __init__(self, routername, model, serialno, ios): #class constructor; initializing some variables and the method is called whenever you create a new instance of the class 
        self.routername = routername #"self" is a reference to the current instance of the class
        self.model = model
        self.serialno = serialno
        self.ios = ios
 
    def print_router(self, manuf_date):
        print("The router name is: ", self.routername)
        print("The router model is: ", self.model)
        print("The serial number of: ", self.serialno)
        print("The IOS version is: ", self.ios)
        print("The model and date combined: ", self.model + manuf_date)
        
router1 = MyRouter('R1', '2600', '123456', '12.4') #creating an object by simply calling the class name and entering the arguments required by the __init__ method in between parentheses
 
router1.model #accessing the object's attributes; result is '2600'
 
router1.print_router("20150101") #accessing a function (actually called method) from within the class 

'''
1- The expected output is as fellowing ( This is a default value since it is shipped by the constructor ) 
    The router name is:  R1
    The router model is:  2600
    The serial number of:  123456
    The IOS version is:  12.4
    The model and date combined:  260020150101
2-
   You sould create an instanceobject ( as in lecture 4 ) for the class
   ex) to create an instance fron the MyRouter class called router1
   router1 = MyRouter('Router1', 'M 3600', 'SN=12345678', 'OS=12.6')

   Task : create another instance called router2 and do the following



 
getattr(router2, "ios") #getting the value of an attribute
 
setattr(router2, "ios", "12.1") #setting the value of an attribute
 
hasattr(router2, "ios") #checking if an object attribute exists
 
delattr(router2, "ios") #deleting an attribute
 
isinstance(router2, MyRouter) #verifying if an object is an instance of a particular class
'''

# creating new child class and inheriting all the attributes from MyRouter class (adding new attribute portno )
class MyNewRouter(MyRouter): #creating a new class (child) inheriting from the MyRouter parent class
    def __init__(self, routername, model, serialno, ios, portno):
        MyRouter.__init__(self, routername, model, serialno, ios)
        self.portno= portno

    def print_new_router(self, string):
        print (string+self.model)

class ciscorouter(MyNewRouter):
    def __init__(self, routername, model, serialno, ios, portno, color):
        MyNewRouter.__init__(self, routername, model, serialno, ios, portno)
        self.color=color
 
issubclass(MyNewRouter, MyRouter) #returns True or False; checking if a class is the child of another class
