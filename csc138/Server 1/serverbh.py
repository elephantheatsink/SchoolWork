#import socket module
from socket import *
serverSocket = socket(AF_INET, SOCK_STREAM)

#Fill in start
serverPort = 12000 #setting port number to 12000
serverSocket.bind(('',serverPort)) #binding the server to the port
serverSocket.listen(1) # server begins listening for incoming TCP requests

#Fill in end

while True:
   #Establish the connection
   print ('Ready to serve...')
   #Fill in start
   connectionSocket, addr = serverSocket.accept() #server waits on accept() for incoming requests,new socket created on return
      #Fill in end
   try:
      #Fill in start
      message = connectionSocket.recv(1024) #would be "sentence" in the slides 
      #Fill in end
      filename = message.split()[1]
      f = open(filename[1:]) #open the file and read the contents
      
      #Fill in start
      outputdata =  f.read() #Fill in end
      
      #Send one HTTP header line into socket
      #Fill in start
      rOK = 'HTTP/1.1 200 OK'
      connectionSocket.send(rOK.encode()) #sends a 200 OK header line
      #Fill in end
      
      #Send the content of the requested file to the client
      for i in range(0, len(outputdata)):
         connectionSocket.send(outputdata[i].encode())
      connectionSocket.close()
      
   except IOError:
      #Send response message for file not found
      #Fill in start
      rNot = '404 Not Found'
      connectionSocket.send(rNot.encode()) #sends an error message
      #Fill in end
      
      #Close client socket
      #Fill in start
      connectionSocket.close()
      #Fill in end
serverSocket.close()