import java.io.*;
import java.net.*;

public class EchoServer {
  public static void main(String args[]) throws Exception {
    DatagramSocket d = new DatagramSocket(9090);
    byte[] recieved  = new byte[1024];

    while(true) {
      DatagramPacket dp = new DatagramPacket(recieved, recieved.length); // Constructs a DatagramPacket for receiving packets of length length.
      
      d.receive(dp);
      InetAddress ipAddress = dp.getAddress();
      int port = dp.getPort();
      String message = new String(dp.getData(), 0, dp.getLength());

      System.out.println(ipAddress + " " + message);

      DatagramPacket response = new DatagramPacket(dp.getData(), dp.getLength(), ipAddress, port);
      d.send(response);
    }
  }
}


// nc -u EchoServer
