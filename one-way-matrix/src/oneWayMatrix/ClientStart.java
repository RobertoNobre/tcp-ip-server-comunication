package oneWayMatrix;

import java.io.IOException;
import java.net.Socket;

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
            Matrix m = new Matrix(5, 5);
            
            m.escreveMatriz(m.getMatrix());
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
