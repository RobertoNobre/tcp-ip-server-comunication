package networkEcho_I;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientStart
   {
   private static final String module    = "Client";
   private static final Random randGen   = new Random();
   private static boolean      isRunning = true;

   public static void main(String[] args)
      {
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();

      try (Socket clientSocket = new Socket("localhost", Info.listeningPort))
         {
         int msgSent = 0;
         System.out.println("Local TCP port " + clientSocket.getLocalPort());
         System.out.println("Sending bytes to TCP port " + clientSocket.getPort());
         System.out.println();

         while (isRunning)
            {
            byte[] buffer = msgFactory();
            System.out.println(module + " sending " + (++msgSent) + ": " + (new String(buffer)));

            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(buffer, 0, buffer.length);
            }
         clientSocket.close();
         }
      catch (IOException exception)
         {
         System.out.println("Exception launched: " + exception.getMessage());
         System.exit(1);
         }

      System.out.println();
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " stopped.");
      }

   private static byte[] msgFactory()
      {
      byte[] buffer = null;

      if (randGen.nextInt(20) == 0)
         {
         buffer = Info.shutDownCmd.getBytes();
         isRunning = false;
         }
      else
         {
         buffer = fillArray();
         }
      return (buffer);
      }

   private static byte[] fillArray()
      {
      byte[] buffer;
      buffer = new byte[Info.maxPackageSise];
      for (int count = 0; count < buffer.length; count++)
         {
         buffer[count] = (byte) ('A' + randGen.nextInt(26));
         }
      return buffer;
      }
   }
