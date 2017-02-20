package com.kmecpp.jlib.server;

import java.io.IOException;

public class Server {

	// private final int port;
	//
	// private Server(int port) {
	// this.port = port;
	// }

	public static void create(final int port, final int maxClient) throws IOException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				//				try {
				//					ServerSocket server = new ServerSocket(port);
				//				} catch (IOException e) {
				//
				//				}
				while (true) {
					break;
					//					Socket client = server.accept();
				}

			}

		}).start();
	}

	// public int getPort() {
	// return port;
	// }

}
