package client;

import java.io.*;
import java.net.*;

public class Client {
    int portaServer = 7777;
    Socket mySocket;
    BufferedReader input;
    String stringaUtente;
    String StringaDaServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    public Socket connetti(){
      System.out.println("(2) CLIENT partito in esecuzione");
      try{
          input =new BufferedReader(new InputStreamReader(System.in));
          mySocket= new Socket(InetAddress.getLocalHost(), portaServer);
          outVersoServer= new DataOutputStream(mySocket.getOutputStream());
          inDalServer= new BufferedReader(new InputStreamReader(mySocket.getInputStream()));     
      }
      catch (UnknownHostException e){
          System.err.println("Host sconosciuto");
      }
      catch (Exception e){
          System.out.println(e.getMessage());
          System.out.println("Errore durante la connessione!");
          System.exit(1); 
      }
      return mySocket;
    }
    
    public void comunica(){
      try{
          System.out.println("(4) Scrivi il primo operatore" + '\n');
          stringaUtente= input.readLine();
          System.out.println("(5) Invio operatore");
          outVersoServer.writeBytes(stringaUtente + '\n');
          
          System.out.println("(7) Scrivi il secondo operatore" + '\n');
          stringaUtente= input.readLine();
          System.out.println("(8) Invio operatore");
          outVersoServer.writeBytes(stringaUtente + '\n');
          
          System.out.println("(10) Scrivi l'operando (+,-,/,*)" + '\n');
          stringaUtente= input.readLine();
          System.out.println("(11) Invio operando");
          outVersoServer.writeBytes(stringaUtente + '\n');
          
          StringaDaServer = inDalServer.readLine();
          System.out.println("(13) Risposta del server" + StringaDaServer);
          System.out.println("(14) Chiusura connesione");
          mySocket.close();
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
          System.out.println("Errore durante la comunicazione col server!");
          System.exit(1);
      }
    }
  
    public static void main (String args[]){
      Client client= new Client();
      client.connetti();
      client.comunica();
    } 
}
