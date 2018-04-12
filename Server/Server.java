import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) throws Exception {
        

        DatagramSocket serverSocket = new DatagramSocket(6000);
        int a,b,result=0;
        char segno;
        byte[] receiveData = new byte[1024];
        byte[] sendData;//  = new byte[1024];
        byte[] datifinali = new byte[1024];
        
        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            datifinali = receivePacket.getData();
            ByteArrayInputStream bais =new ByteArrayInputStream(datifinali);
            DataInputStream dis = new DataInputStream(bais);
            a=dis.readInt();
            b=dis.readInt();
            segno=dis.readChar();
            
         /*   if(segno=='+')
                result = a+b;
            else if(segno=='-')
                result = a-b;
            else if(segno=='*')
                result = a*b;
            else if(segno=='/')
                result = a/b;
            else result=0;*/
            result=a+b;
            System.out.println(result);
            
            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            DataOutputStream fromUserToArray = new DataOutputStream(baos);
            fromUserToArray.writeInt(result);
            sendData= baos.toByteArray();
            
            InetAddress IPAddress = receivePacket.getAddress();
            
            int port = receivePacket.getPort();
            
            DatagramPacket sendPacket = new    DatagramPacket(sendData, sendData.length, IPAddress, port);
            
            serverSocket.send(sendPacket);
       
        }
        
       
    }
}

