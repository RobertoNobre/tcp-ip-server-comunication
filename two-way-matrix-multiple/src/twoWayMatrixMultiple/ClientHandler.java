package twoWayMatrixMultiple;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

class ClientHandler extends Thread {
   private final MultitaskServer father;
   private final Socket          clientSocket;
   private final int             threadId;
   private static final Random randGen   = new Random();

   ClientHandler(MultitaskServer father, Socket myClientSocket, int myThreadId) {
      super();
      this.father = father;
      this.clientSocket = myClientSocket;
      this.threadId = myThreadId;
  }

   @Override
   public void run() {
      boolean isRunning = true;
      try {
 	     OutputStream os = clientSocket.getOutputStream();  
 	     ObjectOutputStream oos = new ObjectOutputStream(os); 
 	
 	     InputStream is = clientSocket.getInputStream();
 	     ObjectInputStream ois = new ObjectInputStream(is); 

         System.out.println("Handler " + threadId + " starting.");

         while (isRunning) {
            Matrix MatrixReceived = (Matrix) ois.readObject();

           Matrix MatrixTransposed = new Matrix(MatrixReceived.transposeMatrix(MatrixReceived.getMatrix()));

	       System.out.println("Read .....: \n");
	       MatrixReceived.writeMatrix(MatrixReceived.getMatrix());


	       oos.writeObject(MatrixTransposed); 

	       System.out.println("Writing .... \n");
	       MatrixTransposed.writeMatrix(MatrixTransposed.getMatrix());
           System.out.println();
           
           int integer = randGen.nextInt(20);
               isRunning = !(integer == 0);
       }

     ois.close();
     oos.close();
     clientSocket.close();

     father.threadClosed(this.threadId);
     } catch (IOException exceptionLaunched) {
         // TODO Auto-generated catch block
         father.threadClosed(this.threadId);
     } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
