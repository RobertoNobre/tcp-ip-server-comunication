package twoWayMatrixMultiple;

public class Info
   {
   public static final String sysName        = "Matrix Multiple Two Way comunication";
   public static final String sysVersion     = "2.00";

   public static final int    listeningPort  = 5000;
   public static final int    loopDelay      = 1000; //temporização no lado cliente

   public static final String getUniformTitle()
      {
      return (Info.sysName + " - " + Info.sysVersion);
      }
   }
