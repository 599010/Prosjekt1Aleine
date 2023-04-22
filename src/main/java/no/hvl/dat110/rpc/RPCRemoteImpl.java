package no.hvl.dat110.rpc;

// RPC server-side method implementations must extend this class

public abstract class RPCRemoteImpl {
	
	public RPCRemoteImpl(byte rpcid, RPCServer rpcserver) {
		rpcserver.register(rpcid, this);
	}

	// method that will be invoked by the server
	// params
	public abstract byte[] invoke(byte[] params);

//Three tests gets errors when I make changes 	
}

//this is what I would've done if every test didn't turn red, I don't know if there's a problem with my compiler.

//public abstract class RPCRemoteImpl {
	
	//public RPCRemoteImpl(byte rpcid, RPCServer rpcserver) {
		//rpcserver.register(rpcid, this);
//	}

//	public byte[] invoke(byte[] params) {
//		try {
			// Unmarshal the incoming parameters
//			Object[] unmarshalledParams = RPCUtils.unmarshallVoid(params);
//
			// Invoke the actual implementation of the remote method
	//		Object result = invokeMethod(unmarshalledParams);

			// Marshal the result
//			byte[] marshalledResult = RPCUtils.marshallVoid(result);

			// Return the marshalled result
//			return marshalledResult;
	//	} catch (Exception e) {
			// If an exception occurs during the remote method invocation, return an error message
		//	return RPCUtils.marshallString("Error executing remote method: " + e.getMessage());
		//}
	//}

//	public abstract Object invokeMethod(Object[] params);
//}
