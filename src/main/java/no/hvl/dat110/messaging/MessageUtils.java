package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
	    byte[] segment = new byte[SEGMENTSIZE];
	    byte[] data = message.getData();
	    
	    int payloadLength = data.length;
	    
	    if (payloadLength <= 124) {
	        segment[0] = (byte) payloadLength;
	        System.arraycopy(data, 0, segment, 1, payloadLength);
	    } else {
	        segment[0] = 124;
	        System.arraycopy(data, 0, segment, 1, 124);
	        segment[125] = 0;
	        segment[126] = 0;
	        segment[127] = 0;
	        System.arraycopy(data, 124, segment, 128 - payloadLength, payloadLength - 124);
	    }
	    
	    return segment;
	}



	public static Message decapsulate(byte[] segment) {
		
	    int payloadLength = segment[0] & 0xFF;
	    byte[] payload = new byte[payloadLength];
	    
	    if (payloadLength <= 124) {
	        System.arraycopy(segment, 1, payload, 0, payloadLength);
	    } else {
	        System.arraycopy(segment, 1, payload, 0, 124);
	        System.arraycopy(segment, 128 - payloadLength, payload, 124, payloadLength - 124);
	    }
	    
	    Message message = new Message(payload);
	    
	    return message;
	}


	
}
