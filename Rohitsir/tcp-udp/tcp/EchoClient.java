import java.io.*;
import java.net.*;
import java.util.*;

public class EchoClient {
  public static void main (String []arg) {
    String hostServer = "localhost";
    int serverPort = 12345;
    try (Socket socket = new Socket(hostServer, serverPort);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter (socket.getOutputStream(),true);
    Scanner sc=new Scanner(System.in);) {
      System.out.println("Client Connection is made with Server!");
      String msg;
      while(true) {
	msg = sc.nextLine();
        if(msg.equals("exit")) break;
	out.println(msg);
	System.out.println("Server Replied back: "+ in.readLine());
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
