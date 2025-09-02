import java.io.*;
import java.net.*;
import java.util.*;

public class CalculatorClient {
  private static final String SERVER_HOST = "localhost";
  private static final int SERVER_PORT = 12345;
  private static final int BUF_SIZE = 1024;

  public static void main(String[] arg) {
    try (DatagramSocket socket = new DatagramSocket(); Scanner sc = new Scanner(System.in)) {
      InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);

      while (true) {
        System.out.println("Enter request (e.g: ADD 10 20) or 'exit' to quit: ");
        String req = sc.nextLine();
        if (req.equalsIgnoreCase("exit"))
          break;

        byte[] reqBytes = req.getBytes();
        DatagramPacket reqPacket = new DatagramPacket(reqBytes, reqBytes.length, serverAddress, SERVER_PORT);
        socket.send(reqPacket);

        byte[] buffer = new byte[BUF_SIZE];
        DatagramPacket resPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(resPacket);

        String res = new String(resPacket.getData(), 0, resPacket.getLength());
        System.out.println("Server replied: " + res);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
