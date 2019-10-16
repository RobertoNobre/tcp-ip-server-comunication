package twoWayMatrix;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart
   {
   private static final String module = "Server";

   public static void main(String[] args)
      {
      boolean isRunning = true;

      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();

      try (ServerSocket serverSocket = new ServerSocket(Info.listeningPort))
         {
         System.out.println("Server at " + InetAddress.getLocalHost() + ", listening TCP port " + serverSocket.getLocalPort());
         System.out.println();

         int msgSent = 0;
         int msgRcvd = 0;

         Socket newClientSocket = serverSocket.accept();
         InputStream inputStream = newClientSocket.getInputStream();
         OutputStream outputStream = newClientSocket.getOutputStream();

         while (isRunning)
            {
            byte[] inBuffer = new byte[Info.maxPackageSise];
            byte[] outBuffer;
            int bytesRead = inputStream.read(inBuffer, 0, inBuffer.length);

            if (bytesRead >= 0)
               {
               String plainText = (new String(inBuffer, 0, bytesRead));
               String reversedText = reverseString(plainText);

               System.out.println(module + " read " + (++msgRcvd) + ": " + plainText + " (" + bytesRead + " bytes)");

               outBuffer = reversedText.getBytes();
               outputStream.write(outBuffer);

               System.out.println(module + " writing " + (++msgSent) + ": " + reversedText + " (" + bytesRead + " bytes)");
               System.out.println();

               isRunning = !plainText.startsWith(Info.shutDownCmd);
               }
            }
         serverSocket.close();
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

   private static String reverseString(String plainText)
      {
      return ((new StringBuffer(plainText)).reverse().toString());
      }
   }
