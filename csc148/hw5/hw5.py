"""
Beomjin Han
csc148 hw 5
Professor Mitchell
"""


import simpy #simpy runtime
import pdb #default python debugger
import numpy as np
import random #for the random number generator

env=simpy.Environment() #make environment object
seedName = 884635 
np.random.seed(seedName) #seeding

class HomeDepot:
    """Home Depot Class"""
    def __init__(self,env,num, inventoryLimit, rpValue, w):
        self.maxInv = 200
        self.env = env                #environment
        self.num = num                 #num
        self.name = "HD "+str(num)    #set the name using the num value
        self.inventory = inventoryLimit  #setting values here with variables declared outside
        self.rp = rpValue
        self.interruption = False #this flag is used later when i(t) is below rp and don't want to send interrupt second time
        
        print("Running "+self.name+" __init__fcn at time 0 Setting Inventory & RP values to 200 & 50") 
        #this gets printed every time Home Depot is instantiated
        
        
    def doOrders(self,env, w):
        
        while True: #loop until inventory reaches reorder point
            
            self.sold = np.random.random_integers(10,45) #random number goes to sold
            yield env.timeout(np.random.random_integers(1,2))            #takes one or two time unit to process
            
            if(self.sold>self.inventory): #if we cannot meet the order
                print("Stockout occurred for "+self.name+" at time "+str(env.now))
                stockoutDone[self.num-1]=env.event() #create a shared event in global list
                w.stockoutList[self.num-1]=w.stockoutList[self.num-1]+1 #increment stockout occurence at corresponding index of HD
                yield stockoutDone[self.num-1] #wait until signal is succeeded
            
            self.inventory = self.inventory-int(self.sold) #decrement inventory by the random amount
            print(self.name+" inventory level is "+str(self.inventory)+" at time "+str(env.now))  #looped statement after each sell
            if (self.inventory<self.rp and self.interruption==False): #not sure about the location of the interrupt
                w.dis.interrupt(self)
                print("!!"+self.name+" is interrupting warehouse w at time "+str(env.now))
                self.interruption=True
                           
        print("Finished "+self.name+" Ops at time "+str(env.now)+" with inventory "+ str(self.inventory)) #after while loop is over, print this
        #pdb.set_trace()
    """
    def soHandler(self,env, stockoutDone):
        if(self.inventory>self.sold):
            stockoutDone.succeed()
            yield(stockoutDone)
    """

class Warehouse:
    """Roofing materials Warehouse operations """
    def __init__(self,env):
        self.env = env
           
        #pdb.set_trace()
        self.numberOfHD = 17 #number of HD stores to be supplied, which is 17
        self.dis=env.process(self.dispatcher(env))
        env.process(self.periodic(env))
        self.restocks = 0
        self.pendingRestockList = [None]*self.numberOfHD
        self.stockoutList = [0]*self.numberOfHD

        
    def dispatcher(self,env):
        while True:
            try:
                yield env.timeout(1) #idk what to put here lol?
            except simpy.Interrupt as interrupt: #this part i'm pretty confident. name and inventory
                print("Start scheduling & delivering restock to "+interrupt.cause.name+" at time "+str(env.now))
                do_restock = env.process(self.restock(env, interrupt.cause)) #calls the warehouse's restock function, passes home depot
                self.pendingRestockList[interrupt.cause.num-1]=str("'HK"+str(interrupt.cause.num)+"'")
                
                
    def restock(self,env, HD):
        yield env.timeout(np.random.random_integers(2,4)) #takes 2-4 t.u. to complete restock
        
        
        
        #HD.inventory=200 #restocks HD object's inventory to 200 #perfect restock
        
        HD.inventory=int(HD.maxInv - (0.5)*HD.rp) #non-ideal restock
        
        print(HD.name+" Completed re-stocking, resuming w. I(t)= 200 at time "+str(env.now))
        self.pendingRestockList[HD.num-1]=None
        #pdb.set_trace()
        if (stockoutDone[HD.num-1]!= None): # and stockoutDone[HD.num-1].triggered==False): #many scenarios during the time it takes to restock. stockout could happen. sometimes the shared event wants to be triggered twice.
           stockoutDone[HD.num-1].succeed()
           stockoutDone[HD.num-1]=None

        HD.interruption=False
        self.restocks +=1
          
    def periodic(self,env):
        while True:
            yield env.timeout(4)
            tempList = []
            for val in self.pendingRestockList:
                if val != None:
                    tempList.append(val)
                    
            print("RPT:: The HDks with pending restocks at time "+str(env.now)+" :: [", end ="")
                
            print(*tempList, sep=", ", end ="")
            print("]")
        

w=Warehouse(env)
stockoutDone = [None]*w.numberOfHD


pfList = [] #initialize a list of scheduled executions

for k in range(w.numberOfHD):
    pfList.append(HomeDepot(env,k+1, 200, 50, w)) #loop to instantiate home depot and add home depot object to the list
    
for k in range(w.numberOfHD):
    print("Starting doOrders() for "+pfList[k].name+" at time 0") 
    env.process(pfList[k].doOrders(env, w)) #loop to add processes


env.run(until = 168)
print("Finished run at model time 168.0")
print("Total number of stockouts, by HD number [",end="")
print(*w.stockoutList, sep=", ", end="")
print("]")
totalSt = 0
for i in range (len(w.stockoutList)):
   totalSt = totalSt+w.stockoutList[i]
print("Grand total number of stockouts over all "+str(totalSt))
print("Grand total number of restocks completed over all HDk  "+str(w.restocks))