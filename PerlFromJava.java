import java.io.*;

class PerlFromJava                                                              // Execute a Perl script from Java
 {static void executePerlScript(String fileName)                                // Execute the supplied file as a Perl script
   {try
     {final var pb = new ProcessBuilder("perl", fileName);
      pb.redirectErrorStream(false);                                            // STDERR will be captured and returned to the caller
      final var p = pb.start();

      final var E = p.getErrorStream();                                         // Read and print STDERR
      for(int c = E.read(); c > -1; c = E.read()) System.err.print((char)c);
      E.close();

      final int rc = p.waitFor();                                               // Wait for process to finish and close it
      if (rc != 0) System.out.println("Perl script exited with code: " + rc);
     }
    catch(Exception E)
     {System.err.println("An error occurred while executing Perl script: "+fileName+" error: "+ E.getMessage());
      System.exit(1);
     }
   }

  public static void main(String[] args)                                        // Test
   {executePerlScript("PerlFromJava.pl");
   }
 }

/* Expected output:
STDERR
*/
