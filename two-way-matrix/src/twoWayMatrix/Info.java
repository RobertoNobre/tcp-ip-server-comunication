package twoWayMatrix;

public class Info
   {
   public static final String sysName        = "Network Echo II";
   public static final String sysVersion     = "1.00";

   public static final int    listeningPort  = 5000;
   public static final int    maxPackageSise = 32;
   public static final String shutDownCmd    = "go down";

   public static final String getUniformTitle()
      {
      return (Info.sysName + " - " + Info.sysVersion);
      }
   }
