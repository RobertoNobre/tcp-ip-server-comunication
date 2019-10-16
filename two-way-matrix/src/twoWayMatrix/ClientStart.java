package twoWayMatrix;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Random;

public class ClientStart implements Serializable {

	private static final long serialVersionUID = -597465615080578264L;
	
	private static final String module    = "Client";
	private static boolean      isRunning = true;

   public static void main(String[] args) throws ClassNotFoundException {
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
	  System.out.println();
	
	  try (Socket clientSocket = new Socket("localhost", Info.listeningPort)) {
	     System.out.println("Local TCP port " + clientSocket.getLocalPort());
	     System.out.println("Sending bytes to TCP port " + clientSocket.getPort());
	     System.out.println();
	
	     OutputStream os = clientSocket.getOutputStream();  
	     ObjectOutputStream oos = new ObjectOutputStream(os); 
	
	     InputStream is = clientSocket.getInputStream();
	     ObjectInputStream ois = new ObjectInputStream(is); 
	     
	     
	     while (isRunning) {
		 	Random r = new Random();
		 	int i = r.nextInt(10-1) + 1;
		 	int j = r.nextInt(10-1) + 1;
	        Matrix m = new Matrix(j, i);
	
	        System.out.println("Matrix sent by client: \n");
	        m.writeMatrix(m.getMatrix());
	        
	        oos.writeObject(m); 
	        Matrix rm = (Matrix) ois.readObject();
	
	        System.out.println("Matrix received from server: \n");
	        rm.writeMatrix(rm.getMatrix());
	        
	        System.out.println();
	    }
	     clientSocket.close();
	 } catch (IOException exception) {
	     System.out.println("Exception launched: " + exception.getMessage());
	     System.exit(1);
	 }

	  System.out.println();
	  System.out.println(Info.getUniformTitle());
	  System.out.println(module + " stopped.");
   }
}
