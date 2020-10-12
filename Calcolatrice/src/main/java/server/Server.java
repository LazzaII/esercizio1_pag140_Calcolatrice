package server;

import java.io.*;
import java.net.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String op1;
    double op1d;
    String op2;
    double op2d;
    char op;
    double ris;
    String risposta;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione");
            server = new ServerSocket(7777);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.out.println(1);
        }
        return client;
    }
    
    public void comunica(){
        try{
            System.out.println("(3) Inserisci il primo operando");
            op1 = inDalClient.readLine();
            System.out.println("(4) Inserisci il secondo operando");
            op2 = inDalClient.readLine();
            System.out.println("(5) Inserisci l'operatore");
            op = (char) inDalClient.read();
            
            op1d = Double.parseDouble(op1);
            op2d = Double.parseDouble(op2);
            
            switch(op){
                case '+':
                    ris = op1d + op2d;
                    risposta = Double.toString(ris);
                    break;
                case '-':
                    ris = op1d - op2d;
                    risposta = Double.toString(ris);
                    break;    
                case '/':
                    ris = op1d / op2d;
                    risposta = Double.toString(ris);
                    break;
                case '*':
                    ris = op1d * op2d;
                    risposta = Double.toString(ris);
                    break;
                default:
                    risposta = "Operatore non valido";                
            }
            
            //OUTPUT AL CLIENT      
            outVersoClient.writeBytes("(7)" + risposta + '\n');
            System.out.println("(9) Fine elaborazione");
            client.close();
        }
        catch(Exception e){
           System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]){
        Server server = new Server();
        server.attendi();
        server.comunica();
    }
    
}
