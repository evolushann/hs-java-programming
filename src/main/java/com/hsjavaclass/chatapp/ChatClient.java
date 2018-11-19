package com.hsjavaclass.chatapp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	public ChatClient(String name, String server, int port) {
		try (Socket conn = new Socket(server, port);
		     DataInputStream in = new DataInputStream(conn.getInputStream());
		     DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		     BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

			out.writeUTF(name);
			out.flush();

			while (true) {
				System.out.println(in.readUTF());

				out.writeUTF(console.readLine());
				out.flush();
			}
		} catch (UnknownHostException e) {
			System.out.println("Server not found!");
		} catch (IOException e) {
			System.out.println("Lost connection to server.");
		}
	}

	public static void main(String[] args) {
		new ChatClient(args[0], "127.0.0.1", 8085);
	}
}
