from socket import *
import ssl
import base64
msg = "\r\nI love computer networks!\r\n"
endmsg = "\r\n.\r\n"
# Choose a mail server (e.g. Google mail server) and call it mailserver
mailserver = 'gaia.ecs.csus.edu' #Fill in start #Fill in end

# Create socket called clientSocket and establish a TCP connection with mailserver
#Fill in start
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((mailserver,25))


#Fill in end
recv = clientSocket.recv(1024).decode()
print (recv)

if recv[:3] != '220':
   print ('220 reply not received from server.')
   
# Send HELO command and print server response.
heloCommand = 'HELO Alice\r\n'
clientSocket.send(heloCommand.encode())
recv1 = clientSocket.recv(1024).decode() #encoding and decoding was necessary
print (recv1)
if recv1[:3] != '250':
   print ('250 reply not received from server.')
"""  
#needs TLS/SSL
tlsCommand = 'STARTTLS\r\n'
clientSocket.send(tlsCommand.encode())
recvTls=clientSocket.recv(1024).decode()
print(recvTls)

sslCS=ssl.wrap_socket(clientSocket,ssl_version=ssl.PROTOCOL_SSLv23)
authCommand='AUTH LOGIN\r\n'
sslCS.send(authCommand.encode())
recvAuth = sslCS.recv(1024).decode()
print(recvAuth)
usernameCommand='\r\n'
pwCommand='\r\n'
sslCS.send(usernameCommand.encode())
sslCS.send(pwCommand.encode())
"""
# Send MAIL FROM command and print server response.
# Fill in start
mailfromCommand = 'MAIL FROM: <fake@scammer.guy>\r\n' #mail from my email
clientSocket.send(mailfromCommand.encode())
recvMailfrom = clientSocket.recv(1024).decode()
print(recvMailfrom)


# Fill in end

# Send RCPT TO command and print server response.
# Fill in start
rcptCommand = 'RCPT TO: <beomjinhan@gmail.com>\r\n' #mail to my email
clientSocket.send(rcptCommand.encode())
recvRcpt = clientSocket.recv(1024).decode()
print(recvRcpt)
# Fill in end

# Send DATA command and print server response.
# Fill in start
dataCommand = "DATA\r\n" #data command
clientSocket.send(dataCommand.encode())
recvData = clientSocket.recv(1024).decode()
print(recvData)
# Fill in end

# Send message data.
# Fill in start
msg2 = msg+endmsg  #combined messages to one, because two separate commands didn't really work
clientSocket.send(msg2.encode())
recvMsg = clientSocket.recv(1024).decode()
print(recvMsg)

# Fill in end
"""
# Message ends with a single period.
# Fill in start
clientSocket.send(endmsg.encode())
recvEnd = clientSocket.recv(1024).decode()
print(recvEnd)
# Fill in end
"""
# Send QUIT command and get server response.
# Fill in start
quitCommand = "QUIT\r\n"
clientSocket.send(quitCommand.encode())
recvQuit = clientSocket.recv(1024).decode()
print(recvQuit)

# Fill in end
