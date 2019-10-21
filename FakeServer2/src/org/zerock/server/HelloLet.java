package org.zerock.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HelloLet extends AbstractLet {

	@Override
	public void serve(InputStream in, OutputStream out) throws Exception {
		// TODO Auto-generated method stub
		String msg = "<h1>Hello World</h1>";
		out.write(new String("HTTP/1.1 200 OK\r\n").getBytes()); 
	    out.write(new String("Cache-Control: private\r\n").getBytes()); 
	    out.write(new String("Content-Length: "+ msg.getBytes().length+"\r\n")
	    		.getBytes()); 
	    out.write(new String("Content-Type: text/html\r\n\r\n")
	    		.getBytes());
	    out.write(msg.getBytes());
		
	}

}
