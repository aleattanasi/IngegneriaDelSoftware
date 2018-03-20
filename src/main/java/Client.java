import java.io.*; 
import java.net.*; 
import java.util.*;

class Client {
	
    public int result;
    

    public static void main(String[] a) {
       new Client(6,6,'+');
    }
    
    public Client(int a, int b, char segno) {
        handle(a,b,segno);
    }
    
    public void handle(int a, int b, char segno) {
        try {
        Scanner inFromUser = new Scanner(System.in);
		
		//DatagramSocket clientSocket = new DatagramSocket();
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("server");
	//	InetAddress IPAddress = InetAddress.getLocalHost();
	        
		// Show the local address 
		System.out.println(address);
	
		byte[] sendData; // = new byte[1024]; 
		byte[] receiveData = new byte[1024];
        byte[] datifinali = new byte[1024];
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        DataOutputStream fromUserToArray = new DataOutputStream(baos);

        
        fromUserToArray.writeInt(a);
        fromUserToArray.writeInt(b);
        fromUserToArray.writeChar(segno);
        sendData=baos.toByteArray();

		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 6000);
		
		clientSocket.send(sendPacket); 
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
		
		clientSocket.receive(receivePacket); 
		
		datifinali = receivePacket.getData();
        
        ByteArrayInputStream bais =new ByteArrayInputStream(datifinali);
        DataInputStream dis = new DataInputStream(bais);
        result=dis.readInt();
		
		System.out.println("FROM SERVER: "+result);
		
		clientSocket.close();
 
        } catch (Exception e) {System.err.println(e);}
	} 
} 
