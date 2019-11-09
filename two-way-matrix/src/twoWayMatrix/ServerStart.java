package twoWayMatrix;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {
   private static final String module = "Server";

   public static void main(String[] args) throws ClassNotFoundException {
      boolean isRunning = true;
      
      int ctdr = 10;

      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();

      try (ServerSocket serverSocket = new ServerSocket(Info.listeningPort)) {
         System.out.println("Server at " + InetAddress.getLocalHost() + ", listening TCP port " + serverSocket.getLocalPort());
         System.out.println();

         Socket newClientSocket = serverSocket.accept();
         
	     OutputStream os = newClientSocket.getOutputStream();  
	     ObjectOutputStream oos = new ObjectOutputStream(os); 
	
	     InputStream is = newClientSocket.getInputStream();
	     ObjectInputStream ois = new ObjectInputStream(is);

	     while (isRunning) {
            Matrix MatrixReceived = (Matrix) ois.readObject();

            System.out.println("Matrix received in server: \n");
            MatrixReceived.writeMatrix(MatrixReceived.getMatrix());
 
            Matrix MatrixTransposed = new Matrix(MatrixReceived.transposeMatrix(MatrixReceived.getMatrix()));

            System.out.println("Matrix sent to client: \n");
            MatrixTransposed.writeMatrix(MatrixTransposed.getMatrix());
            
            oos.writeObject(MatrixTransposed);
            --ctdr;
            
            if(ctdr == 0) {
            	isRunning = false;
            } 
        }
        serverSocket.close();
     } catch (IOException exception) {
         System.out.println("Exception launched: " + exception.getMessage());
         System.exit(1);
     }
      System.out.println();
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " stopped.");
  }
}
