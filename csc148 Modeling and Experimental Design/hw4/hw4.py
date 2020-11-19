
"""
Beomjin Han
This program simulates 3 HD classes selling roof shingles unit it reaches reorder point and terminates.
A loop creates 3 instances of Home Depot. A process function inside Home Depot class decrements the object’s inventory unit it reaches reorder point
Usage: run the Python program normally without parameters.
"""

import simpy #simpy runtime
import pdb #default python debugger
import numpy as np
import random #not sure whether to use "from numpy import random"

env=simpy.Environment() #make environment object
seedName = 927385 
np.random.seed(seedName) #seeding

inventoryLimit=200 #i decided to set these here so that i can change it whenever i want.
rpValue=50


class HomeDepot:
    """Home Depot class"""
    def __init__(self,env,num):
        self.env = env                #environment
        self.name = "HD "+str(num)    #set the name using the 
        self.inventory = inventoryLimit  #setting values here with variables declard outside
        self.rp = rpValue
        print("Running "+self.name+" __init__fcn at time 0 Setting Inventory & RP values to 200 & 50") 
		#this gets printed every time Home Depot is instantiated
        
    def doOrders(self,env):
        while self.inventory> 50: #loop until inventory reaches reorder point
            sold = np.random.uniform(10,45) #random number goes to sold
            yield env.timeout(np.random.randint(1,3))            #takes one or two time unit to process. I tried using 1,2 but it never took 2 time units in my result. 1,3 worked up to two time units
            self.inventory = self.inventory-int(sold) #decrement inventory by the random amount
            print(self.name+" inventory level is "+str(self.inventory)+" at time "+str(env.now))  #looped statement after each sell
        print("Finished "+self.name+" Ops at time "+str(env.now)+" with inventory "+ str(self.inventory)) #after while loop is over, print this
        #pdb.set_trace()


class Warehouse:
    """Roofing materials Warehouse operations """
    def __init__(self,env):
        self.env = env
        
        #pdb.set_trace()
        self.numberOfHD = 3 #number of HD stores to be supplied


print("Running class Warehouse __init__ fcn at time 0 for warehouse WH1//NumPy seed is 927385")
w=Warehouse(env) #instantiating the warehouse class

print("Creating 3 HD stores") 
      
"""scheduling pfs using a list and loop"""
pfList = [] #initialize a list of scheduled executions
for k in range(3):
    pfList.append(HomeDepot(env,k+1)) #loop to instantiate home depot and add home depot object to the list
    
for k in range(3):
    print("Starting doOrders() for "+pfList[k].name+" at time 0") 
    env.process(pfList[k].doOrders(env)) #loop to add processes

env.run()  #run the scheduled processes
print("Finish run at model time "+str(env.now))
