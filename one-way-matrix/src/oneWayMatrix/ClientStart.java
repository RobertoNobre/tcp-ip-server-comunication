package oneWayMatrix;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientStart {
   private static final String module    = "Client";
   private static boolean      isRunning = true;

   public static void main(String[] args) {
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();

      try (Socket clientSocket = new Socket("localhost", Info.listeningPort)) {
         System.out.println("Local TCP port " + clientSocket.getLocalPort());
         System.out.println("Sending MATRIX to TCP port " + clientSocket.getPort());
         System.out.println();

         while (isRunning) {
	    	 Random r = new Random();
	    	 int i = r.nextInt(10-1) + 1;
	    	 int j = r.nextInt(10-1) + 1;
            Matrix m = new Matrix(j, i);

            System.out.println("Matrix sent by client: \n");
            m.writeMatrix(m.getMatrix());

            OutputStream os = clientSocket.getOutputStream();  
            ObjectOutputStream oos = new ObjectOutputStream(os);  
            oos.writeObject(m); 
            
            isRunning = false;
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
