package com.hsjavaclass.chatapp;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ChatServer {
	private Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();
	private MongoClient mongo;
	private MongoCollection<Document> collection;

	public ChatServer(int port) {
		mongo = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongo.getDatabase("java-programming-db");
		collection = mongoDb.getCollection("chats");
		try (ServerSocket server = new ServerSocket(port)) {
			while (true) {
				ClientHandler client = new ClientHandler(this, server.accept());
				clients.add(client);
				client.start();
				sendPreviousMessages(client);
			}
		} catch (IOException e) {
			System.out.println("The server failed on port: " + port + ".");
		}
	}

	public synchronized void broadcast(String name, String message) {
		System.out.println("New message -> " + name + ": " + message);
		Document document = new Document();
		document.put("name", name);
		document.put("message", message);
		collection.insertOne(document);
		for (ClientHandler client : clients)
			client.sendMessage(name + ": " + message);
	}

	public void clientDisconnected(ClientHandler client) {
		broadcast(client.name, " left");
		clients.remove(client);
	}

	private void sendPreviousMessages(ClientHandler client) {
		collection.find().forEach((Consumer<? super Document>) object -> client.sendMessage(object.get("name") + ": " + object.get("message")));
	}

	public static void main(String[] args) {
		ChatServer server = new ChatServer(8085);
		server.mongo.close();
	}
}
