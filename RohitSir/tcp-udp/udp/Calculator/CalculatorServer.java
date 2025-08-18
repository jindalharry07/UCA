import java.net.*;

public class CalculatorServer {
  private static final int PORT = 12345;
  private static int BUF_SIZE = 1024;

  public static void main(String []arg) {
    try(DatagramSocket socket = new DatagramSocket(PORT)) {
      byte[] buffer = new byte[BUF_SIZE];
      System.out.println("UDP Calculator Server started on port "+ PORT);

      while(true) {
        DatagramPacket packet =new DatagramPacket (buffer,buffer.length);
        socket.receive(packet);

        String req = new String (packet.getData(),0,packet.getLength());

        System.out.println("Recived request: "+req);

        String res = calculate(req);
        byte[] resBytes = res.getBytes();
        DatagramPacket resPacket = new DatagramPacket( resBytes,resBytes.length,packet.getAddress(),packet.getPort());

        socket.send(resPacket);
      }
    }catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static String calculate(String req) {
    try {
      String [] parts = req.trim().split(" ");

      if(parts.length!=3) return "Invalid format! Use: OPERATION num1 num2";

      String op = parts[0].toUpperCase();
      double num1 = Double.parseDouble(parts[1]);
      double num2 = Double.parseDouble(parts[2]);

      switch (op) {
        case "ADD": return "Result = " + (num1 + num2);
        case "SUB": return "Result = " + (num1 - num2);
        case "MUL": return "Result = " + (num1 * num2);
        case "DIV":
          if (num2 == 0) return "Error: Division by zero!";
            return "Result = " + (num1 / num2);
        default: return "Unsupported operation! Use ADD, SUB, MUL, DIV.";
      }
    }catch (Exception e) {
      return "Error in calculation!";
    }
  }
}
