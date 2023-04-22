package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
		msgclient = new MessagingClient(server, port);
		connection = msgclient.connect();
	}
	
	public void connect() {
		connection = msgclient.connect();
	}
	
	public void disconnect() {
		connection.close();
	}

	/*
	 Make a remote call on the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */
	public byte[] call(byte rpcid, byte[] param) {
		byte[] returnval = null;

		// encapsulate the rpcid and param in a byte array according to the RPC message syntax
		byte[] rpcmsg = RPCUtils.encapsulate(rpcid, param);

		// create a message to send to the server
		Message request = new Message(rpcmsg);

		// send the message and receive the reply
		Message send = connection.send(request);
		Message receive = connection.receive();

		// extract the payload from the reply message and decapsulate it
		byte[] replyPayload = receive.getData();
		returnval = RPCUtils.decapsulate(replyPayload);

		return returnval;
	}
}
