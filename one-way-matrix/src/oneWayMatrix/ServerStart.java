package oneWayMatrix;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {
   private static final String module = "Server";
   
   public static void main(String[] args) throws ClassNotFoundException {
      boolean isRunning = true;

      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();

      try (ServerSocket serverSocket = new ServerSocket(Info.listeningPort)) {
         System.out.println("Server at " + InetAddress.getLocalHost() + ", listening TCP port " + serverSocket.getLocalPort());
         System.out.println();
         
         Socket newClientSocket = serverSocket.accept();

         while (isRunning) {
            InputStream inputStream = newClientSocket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Matrix m = (Matrix) ois.readObject();

            System.out.println("Matrix received in server: \n");

            m.writeMatrix(m.getMatrix());
            
            isRunning = false;
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
