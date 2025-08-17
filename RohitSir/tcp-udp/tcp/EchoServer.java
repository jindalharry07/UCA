import java.io.*;
import java.net.*;

public class EchoServer{
  public static void main(String[] args){
   int port=12345;
   ServerSocket server=null;
   try{
     server = new ServerSocket(port);
     System.out.println("WE are able to create a object on the port "+ port );
   }catch(IOException e){
     System.out.println("We are not able to create a object on the port " + port);
   }
   
   while(true){
     try{
       Socket client= server.accept();
       System.out.println("Client address is : " + client.getRemoteSocketAddress());
       Thread t=new Thread(() -> handleClient(client));
       t.start();
     }catch(Exception e){
       System.out.println("Getting Error while accepting the connection" );
     }
   }
  }


  public static  void handleClient(Socket client){
    try(
      BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter out=new PrintWriter(client.getOutputStream(),true);
    ){
      String line;
      while((line=in.readLine()) != null){
        System.out.println("Recieved this from client : "+line);

        out.println("Echo back : "+line);
      }
    }catch(Exception e){
      e.printStackTrace();
    }finally {
      closeConnection(client);
    }
  }

  public static void closeConnection(Socket client){
    try{
      client.close();
    } catch(Exception e){}
  }
}
