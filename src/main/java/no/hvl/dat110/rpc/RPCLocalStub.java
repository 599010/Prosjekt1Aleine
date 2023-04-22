package no.hvl.dat110.rpc;

// RPC client-side (local) stubs must extend this class

public abstract class RPCLocalStub {

	protected RPCClient rpcclient;
	
	public RPCLocalStub(RPCClient rpcclient) {
		this.rpcclient = rpcclient;
	}
	
}

//Ser at det er ein del av oppgavebeskrivelsen, men det blir errors på tests når eg endrer på den.


//her er det eg hadde gjort:
//RPC client-side (local) stubs must extend this class
//public abstract class RPCLocalStub {

//	protected RPCClient rpcclient;
	
//	public RPCLocalStub(RPCClient rpcclient) {
	//	this.rpcclient = rpcclient;
	//}

	// Implement this method in your local stubs
//	public abstract byte[] invoke(byte[] request);
	
	// Use this method to execute the remote call
	//public byte[] call(byte[] request) {
	//	return rpcclient.call(request);
	//}
//}
