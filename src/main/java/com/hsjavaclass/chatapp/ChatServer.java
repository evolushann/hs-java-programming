package com.hsjavaclass.chatapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
	Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();

	public ChatServer(int port) {
		try (ServerSocket server = new ServerSocket(port)) {
			while (true) {
				ClientHandler client = new ClientHandler(this, server.accept());
				clients.add(client);
				client.start();
			}
		} catch (IOException e) {
			System.out.println("The server failed on port: " + port + ".");
		}
	}

	public synchronized void broadcast(String message) {
		System.out.println("New message -> " + message);
		for (ClientHandler client : clients)
			client.sendMessage(message);
	}

	public void clientDisconnected(ClientHandler client) {
		clients.remove(client);
		broadcast("Chat member " + client.getName() + " left");
	}

	public static void main(String[] args) {
		new ChatServer(8085);
	}
}
