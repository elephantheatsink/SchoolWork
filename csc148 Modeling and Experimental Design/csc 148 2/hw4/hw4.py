
"""
Beomjin Han
This program simulates 3 HD classes selling roof shingles unit it reaches
reorder point and terminates.
Usage: run the Python program normally without parameters.
"""

import simpy #simpy runtime
import pdb #default python debugger
import numpy as np
import random #not sure whether to use "from numpy import random"
import pdb #debugging

env=simpy.Environment() #make environment object
seedName = 2468 
np.random.seed(seedName) #seeding



class HomeDepot:
    """Home Depot class"""
    def __init__(self,env,homeDepotName,inventory,rp):
        self.env = env
        self.homeDepotName = homeDepotName
        self.inventory = inventory
        self.rp = rp
        
        #pdb.set_trace()


class Warehounse:
    """Roofing materials Warehouse operations """
    def __init__(self,env,warehouseName):
        self.env = env
        self.warehouseName = warehouseName
        #pdb.set_trace()
        self.numberOfHD = 3 #number of HD stores to be supplied

hd1 = HomeDepot(env,'HD 1','2000','50')
hd2 = HomeDepot(env,'HD 2','2000','50')
hd3 = HomeDepot(env,'HD 3','2000','50')

