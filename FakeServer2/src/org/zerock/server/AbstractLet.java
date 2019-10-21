package org.zerock.server;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractLet {
	
	public abstract void serve(InputStream in, OutputStream out);

}
