from socket import *

serverName = 'localhost'
serverPort = 12000
clientSocket = socket(AF_INET,SOCK_DGRAM)
message = input('input:')
clientSocket.sendto(message.encode(),(serverName,serverPort))
modifiedMessage, serverAdress = clientSocket.recvfrom(2048)
print(modifiedMessage.decode())
clientSocket.close()