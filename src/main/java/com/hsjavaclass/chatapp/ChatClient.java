package com.hsjavaclass.chatapp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	private DataInputStream in;
	private DataOutputStream out;
	private BufferedReader console;

	public ChatClient(String name, String server, int port) {
		try {
			Socket conn = new Socket(server, port);
			in = new DataInputStream(conn.getInputStream());
			out = new DataOutputStream(conn.getOutputStream());
			console = new BufferedReader(new InputStreamReader(System.in));

			out.writeUTF(name);
			out.flush();

			Thread sendMessage = new Thread(() -> {
				while (true) {
					String message;
					try {
						if ((message = console.readLine()) != null) {
							out.writeUTF(message);
							out.flush();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

			Thread readMessage = new Thread(() -> {
				while (true) {
					try {
						if (in.available() > 0)
							System.out.println(in.readUTF());
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			});

			sendMessage.start();
			readMessage.start();
		} catch (UnknownHostException e) {
			System.out.println("Server not found!");
		} catch (IOException e) {
			System.out.println("Lost connection to server.");
		}
	}

	public static void main(String[] args) {
		new ChatClient("oof", "127.0.0.1", 8085);
	}
}
