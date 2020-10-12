# KARLA D LOZADA GUERRA
# CSC148
# Homework 5

import simpy
import pdb
import numpy as np
import random

env = simpy.Environment()  # creating the environment
seedName = 532487
np.random.seed(seedName)

'''
However h5 needed specs:
We have a total of 17 Home Depot stores. The warehouse serves to all of the Home Depot, these Home Depot must
exist for an entire model execution. We still have one kind of customer order and order size n is random. 
Order processing is more complex in h5.
As soon as a size n order decrements HDk’s inventory below level RP = 50 (RP stands for “reorder point”), 
HDk is required to interrupt the warehouse’s pf “dispatcher”. 
The interrupting HDk continues processing orders and decrementing HDk’s inventory level Ik(t) 
If a next order’s orderSize satisfies (Ik(t)–orderSize) < 0 (out-of-stock)
IF out-of-stock ( occurs, an HDk is required to cease/stop(interrupt) fulfilling all orders. 
The model need to wait synchronously (meaning HDk’s order processing pf is suspended) until the 
shared event (refer to as stockoutDone) happens. 
Also, warehouse instance is required to have a pf that processes restock for an HD. 
'''

'''
Warehouse class where we will have the amount of home Depots that it is supplying
The class Warehouse has more functionality than in HW4, because we will handle the 
dispatch, where the inventory will be restock and create a report
'''


class Warehouse:

    def __init__(self, env):
        self.env = env
        self.dispat = env.process(self.dispat_warehouse(env))  # scheduling the pf on dispat

    '''
       Reporting model function will print the list of home depot that need to be restocked in 4 time units
       The function will help with organization and debugging. 
       '''

    def reporting_model(self, env):
        while 1:
            timeunit = 4
            yield env.timeout(timeunit)  # will wait 4 time units to do the reporting
            print("RPT:: The HDks with pending restocks at time ", int(env.now), " :: [", end="")
            print(*waiting_restock_list, sep=", ", end="]\n")


    '''
    The dispatch process function is the driver for the interrupt, where Home Depo sends 
    the interrupt signal to the Warehouse dispatch, where we will interrupt until restock store
    also we will also store the info for pending restock Home depots 
    '''

    def dispat_warehouse(self, env):
        while 1:
            # we may get interrupt for specific time
            ran = 1
            try:
                yield env.timeout(ran)
            except simpy.Interrupt as inter_stop:
                # when we received an interrupt, we stop the stop the holding
                # and we switch to restock the Home Depot
                # that is why we immediately let the user know that the store
                # is starting the process of restock
                print("Start scheduling & delivering restock to " + inter_stop.cause.name_store + " at time ",
                      int(env.now))
                env.process(self.restock_homedepot(env, inter_stop.cause)) # after the interrupt we can schedule the
                # process function of restocking the home depot
                # list that has the home depot pending of restock for later print
                waiting_restock_list.append("'HK" + str(inter_stop.cause.num) + "'")


    '''
    restock_homedepot will restock the Home Depot which will be scheduled by the 
    warehouse dispatcher. We will pass the Home Depor object in order to update
    the stock of inventory by setting it to 200. The function also has the Restock
    Policy function which is commented. On this func we are passing the object 
    Home Depot. 
    '''

    def restock_homedepot(self, env, HomeDe):
        vari = np.random.uniform(2, 4)
        yield env.timeout(vari)
        HomeDe.inventory = 200
        # Restock Policy ###

        # HomeDe.inventory = 200 - (0.5 * 50)

        # After the inventory has been updated we will print the statement
        # to show client the Home Depot has completed stock again.
        print(HomeDe.name_store + " Completed re-stocking, resuming w. I(t)= 200 at time ", int(env.now))
        # pop the first element on the list, which is already stocked, so
        # restocking is not done twice
        restock_done = restock[HomeDe.num - 1]
        waiting_restock_list.pop(0)
        # After we finish the restock we need to finish the shared event and let the Home Depot keep
        stckdone = stckout_done[HomeDe.num - 1]
        if stckdone is not None:
            if stckout_done[HomeDe.num - 1].triggered == 0:
                stckout_done[HomeDe.num - 1].succeed()




'''
On the Home Depot class we have all the functionality of the code. We know that the stock of each HD will be 200 and
the reorder point is 50, which we will use for our do_orders() function.
In our Home Depot we will use an __init__ to create it and will have the values for it's num, stock and the reorder
point num.
'''



class HomeDepot:

    def __init__(self, env, num, inven_limit, rp_val):
        self.env = env
        self.inventory = inven_limit
        self.num = num  # num that will help us naming the home depot
        self.name_store = "HD " + str(num)  # name of the Home Depot
        self.rp_val = rp_val

        # model will print when Home Depot is created
        print("Running " + self.name_store + " __init__fcn at time 0 Setting Inventory & RP values to 200 & 50")

    '''
       In the function signal we are checking if the event has succeed and we can continue with the model.
       When the Home Depot has finished restocking, we know that because the model will have enough inventory
       to fulfill the orders. 
       '''

    def signal(self, env, stckout_done):
        if self.order > self.inventory:
            amount = stckout_done
            stckout_done.succeed()  # the shared event is done
            yield (amount)

    '''
    We have our do_orders function, which will create our functionality for decrementing our stock with the random number
    bought. As we know if the stock is greater than 50 then we can continue with the order.
    Also, our inventory level I(t) every 1 or 2 hours which I decided to create a random value from 1 to 2 for the yield.   
    '''

    def do_orders(self, env):

        while 1:  # will keep running until interrupted

            self.order = np.random.uniform(10, 45)  # random order for asked
            duration = np.random.randint(1, 3)  # number 1 or 2 for wait
            yield env.timeout(duration)
            '''
            When the order is bigger than the actual inventory in the Home Depot,
            we have stockout, which means we create our shared event. 
            The store can continue when the shared event happens - wich is the StckoutDone
            '''

            valnew = 1
            if self.order > self.inventory:
                print("Stockout occurred for " + self.name_store + " at time ", int(env.now))
                stckout_done[self.num - valnew] = env.event()  # shared even list
                yield stckout_done[self.num - valnew]
            '''
            Used the same idea as H4 of decrementing the inventory by subtracting the inventory 
            and the order amount and printing the info about the Home Depot
            '''
            if self.inventory > 50:
                self.inventory = self.inventory - int(self.order)
                print(self.name_store + " inventory level is " + str(self.inventory) + " at time ", int(env.now))

            '''
                When we have out stockout, we need to refill the inventory. This func will check
                if the inventory is bigger than the order so it will keep going on may solve
                other orders if available. Stockout is when I(t) - orderSize < 0 , I(t) < orderSize.
                If this condition is meet then we need the shared event. 
                '''

            if self.rp_val - self.inventory > 0:
                w.dispat.interrupt(self)
                print("!!" + self.name_store + " is interrupting warehouse w at time ", int(env.now))




w = Warehouse(env)
env.process(w.reporting_model(env)) # reporting schedule pfs 
'''
Initializing all lists needed for the model 
'''
stckout_done = [None] * 17
restock = [0] * 17
waiting_restock_list = []
stock_out_list = [0] * 17
pfList = []

''' We are creating the Home Depot Object with the an inventory = 200 and RP value = 50 
    By having the loop we can create n amount of Home Depot without having to create an 
    instance of them manually
'''
for k in range(17):
    pfList.append(HomeDepot(env, k + 1, 200, 50))

''' We are scheduling the pf for each Home Depot. Again by having the loop the program
    becomes more consistent. 
'''
for k in range(17):
    print("Starting doOrders() for " + pfList[k].name_store + " at time 0")
    env.process(pfList[k].do_orders(env))  # loop to add processes

env.run(170)
print('Finish run at model time ', int(env.now))

print('Total number of stockouts by HD number are :', *stock_out_list)
total_stockout = len(stock_out_list)
print('Grand total number of stockouts over all HDk', total_stockout)
total_restock = len(restock)
print('Grand total number of restocks completed over all HDk', total_restock)
