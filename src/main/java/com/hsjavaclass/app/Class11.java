package com.hsjavaclass.app;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Class11 {
	public static void main(String[] args) throws IOException {
//		String hostname = "www.columbia.edu";
//		String file = "/~fdc/sample.html";
//		int p = 80;
//
//		InetAddress ip = InetAddress.getByName(hostname);
//		System.out.println(ip.getHostAddress());
//
//		try(Socket s = new Socket(hostname, p)) {
//			PrintWriter out = new PrintWriter(s.getOutputStream(), false);
//			out.print("GET " + file + " HTTP/1.0\r\n");
//			out.print("Accept: text/plain, text/html, text/*\r\n");
//			out.print("\r\n");
//			out.flush();
//
//			InputStreamReader inr = new InputStreamReader(s.getInputStream());
//			BufferedReader br = new BufferedReader(inr);
//			String line;
//			while((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
//		} catch (IOException e) {
//			System.out.println();
//		}

//		String webpage = "http://www.columbia.edu/~fdc/sample.html";
//
//		try {
//			URL url = new URL(webpage);
//			URLConnection conn = url.openConnection();
//			Map<String, List<String>> headers = conn.getHeaderFields();
//
//			Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
//
//			for(Map.Entry<String, List<String>> entry : entrySet) {
//				System.out.println("Header name " + entry.getKey());
//				System.out.println("Header value ");
//				for(String value : entry.getValue()) {
//					System.out.println(value);
//				}
//				System.out.println();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		String webpage = "http://httpbin.org/post";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(webpage);

		try {
			List<NameValuePair> pairs = new ArrayList<>(1);
			pairs.add(new BasicNameValuePair("userId", "123456789"));
			post.setEntity(new UrlEncodedFormEntity(pairs));

			HttpResponse response = client.execute(post);
			BufferedReader reader = new BufferedReader(new InputStreamReader(post.getEntity().getContent()));

			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {

		}
	}
}
