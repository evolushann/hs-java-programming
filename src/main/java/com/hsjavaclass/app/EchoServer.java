package com.hsjavaclass.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 8008;
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(PORT);

			while (true) {
				Socket client = server.accept();

				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

				out.println("Hi!");
				out.println("Type 'seeya' to exit");
				out.flush();

				String line;
				while ((line = in.readLine()) != null) {
					out.println("Echo: " + line);
					out.flush();
					if(line.trim().toLowerCase().equals("seeya")) break;
				}

				in.close();
				out.close();
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
