import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = ('localhost', 10002)

sock.connect( server_address )

print("Connection established...")

sock.send("Hello Server".encode())
print("Msg sent")

data = sock.recv(100)
print("Message from server: %s" % data.decode() )

sock.close()

