package twoWayMatrixMultiple;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class MultitaskServer
   {
   private final String module;
   private boolean      isRunning;
   private int          threadsCount;

   MultitaskServer(String module) {
      super();
      this.module = module;
      this.isRunning = false;
      this.threadsCount = 0;
  }

   void startServer() {
      this.isRunning = true;

      try (ServerSocket serverSocket = new ServerSocket(Info.listeningPort)) {
         System.out.println(this.module + " at " + InetAddress.getLocalHost() + " listening TCP port " + serverSocket.getLocalPort());
         System.out.println();

         while (isRunning) {
            Socket newClientSocket = serverSocket.accept();

            System.out.println("Launching new client handler");
            Thread clientHandler = new ClientHandler(this, newClientSocket, threadsCount++);
            clientHandler.start();
        }
     } catch (IOException exception) {
         System.out.println("Exception launched: " + exception.getMessage());
         System.exit(1);
     }

      System.out.println(this.module + " going down.");
      System.out.println();
  }

   void threadClosed(int threadId) {
      this.threadsCount--;

      System.out.println("Closing thread " + threadId + "; " + this.threadsCount + " remaining.");
      System.out.println();

      if (threadsCount == 0) {
         System.out.println("Shutting down server.");
         this.isRunning = false;
         System.exit(0);
      }
   }
}
