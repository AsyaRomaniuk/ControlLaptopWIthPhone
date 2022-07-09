import socket
import threading


def handle(client, address):
    while True:
        try:
            command = client.recv(1024)
            if not command:
                client.close()
                print(f"The client {address} has left.")
                break
            print(f"The new command has received: {command.decode('utf-8')}")
        except Exception:
            client.close()
            print(f"The client {address} has left.")
            break
        try:
            exec(command)  # !!!!!!!
        except Exception as e:
            print(f"The error has occured during command execution:")
            print(e)


def receive():
    while True:
        client, address = server.accept()
        print(f"The new client {address} has joined.")
        thread = threading.Thread(target=handle, args=(client, address))
        thread.start()


# def close():
# server.shutdown(socket.SHUT_RDWR)
# server.close()
# print("closed")

if __name__ == "__main__":
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    ipv4 = socket.gethostbyname_ex(socket.gethostname())[2][0]
    print(ipv4)
    server.bind((ipv4, 2000))
    server.listen()
    print("Server is listening...")
    receive()
