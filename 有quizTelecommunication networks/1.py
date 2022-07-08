import socket


# creat a socket
sock =socket.socket(socket.AF_INET, socket.SOCK_STREAM)   
# Bind and listen
host = "localhost"  
port = 10002                 
sock.bind((host, port))         

# accept connection
sock.listen() 
#-------------------------------------------------------
print("Server is up...")

try:
	while True:
		client, client_info = sock.accept()
		print("Client address: %s client port: %d" % client_info)
		data = client.recv(100)
		print(data)
		print("Message as a string: %s" % data.decode() )
		str_msg = "Hello Client"
		client.sendall( str_msg)
		print("Message sent: %s" % str_msg)
		client.close()
except:
	print("Termination")
finally:
	print("Closing socket...")
	sock.close()

