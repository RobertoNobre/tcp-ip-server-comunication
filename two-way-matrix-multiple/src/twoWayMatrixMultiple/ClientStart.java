package twoWayMatrixMultiple;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientStart {
   private static final String module    = "Client";
   private static final Random randGen   = new Random();
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
 		 	Matrix m = msgFactory(j, i);

	        System.out.println("Matrix sent by client: \n");
	        m.writeMatrix(m.getMatrix());

	        oos.writeObject(m); 
	        Matrix rm = (Matrix) ois.readObject();

	        System.out.println("Matrix received from server: \n");
	        rm.writeMatrix(rm.getMatrix());
	        System.out.println();
	        
            Thread.sleep(Info.loopDelay);
        }
         clientSocket.close();
     } catch (IOException | InterruptedException exception) {
         System.out.println("Exception launched: " + exception.getMessage());
         System.exit(1);
     }

      System.out.println();
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " stopped.");
  }

   private static Matrix msgFactory(int j, int i) {
      Matrix m = new Matrix(j, i);

      if (randGen.nextInt(20) == 0) {
         isRunning = false;
      }

      return m;
  }
}
