package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public Message send(Message message) {

		try {
			// get the data from the message
			byte[] data = message.getData();
			
			// write the data length as a single byte to the output stream
			outStream.writeByte(data.length);
			
			// write the data to the output stream
			outStream.write(data);
			
		} catch (IOException ex) {
			System.out.println("Failed to send message: " + ex.getMessage());
			ex.printStackTrace();
		}
		return message;
	}

	public Message receive() {

		Message message = null;
		
		try {
			// read the data length as a single byte from the input stream
			int length = inStream.readUnsignedByte();
			
			// create a byte array to store the data
			byte[] data = new byte[length];
			
			// read the data into the byte array
			inStream.readFully(data);
			
			// create a new message with the received data
			message = new Message(data);
			
		} catch (IOException ex) {
			System.out.println("Failed to receive message: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return message;
	}


	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}