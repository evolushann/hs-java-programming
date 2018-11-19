package com.hsjavaclass.chatapp;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
	private final ChatServer server;
	private final Socket client;
	private final DataInputStream in;
	private final DataOutputStream out;
	private final String name;

	public ClientHandler(ChatServer server, Socket socket) throws IOException {
		this.server = server;
		client = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());

		name = in.readUTF();
		System.out.println("A new client connected: " + name + " from " + client.getInetAddress());
	}

	@Override
	public void run() {
		try (client; in; out) {
			while (true) {
				if (in.available() > 0)
					server.broadcast(name + ": " + in.readUTF());
			}
		} catch (IOException e) {
			server.clientDisconnected(this);
			System.out.println("A client disconnected: " + name);
		}
	}

	public void sendMessage(String message) {
		try {
			out.writeUTF(message);
			out.flush();
		} catch (IOException e) {
			server.clientDisconnected(this);
			System.out.println("A client disconnected: " + name);
		}
	}
}
