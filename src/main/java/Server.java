import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

	int count = 1;	
	int ID = 0;
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	ArrayList<String> clientsOnline = new ArrayList<String>();
	
	
	Server(Consumer<Serializable> call){
	
		//System.out.println("the port is : "+GuiServer.port);
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void updateOnlineClients(infoClient infoClient){
			
			infoClient.clientsOnline.clear();
			
			for (int i = 0; i < clientsOnline.size(); i++) {
				String getOnlineClient = clientsOnline.get(i);
				infoClient.clientsOnline.add(getOnlineClient);
			}
			
		}// end of updateOnlineClients
		
		public void updateClients() {
			
			for (int i = 0; i < clients.size(); i++) {
				//System.out.println("Heloooo from updateClients "+i);
				ClientThread oneClient = clients.get(i);
				updateOnlineClients(oneClient.info);
				//System.out.println("Heloooo from updateClients "+i);
				try {
					oneClient.out.reset();
					oneClient.out.writeObject(oneClient.info);
					
				}
				catch(Exception e) {}
				
			}// end of for
			
			
		
			}// end of updateClients
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(5555);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				//callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				updateClients();
				count++;
				ID++;
				
			    }
			}//end of try
				catch(Exception e) {
					//callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			infoClient info;
			
			ClientThread(Socket s, int count){
				info = new infoClient();
				this.connection = s;
				this.count = count;	
				info.ID = ID;
				
				String newClient = "Client "+ (ID + 1);
				clientsOnline.add(newClient);
				
				/*THIS CALLBACK UPDATE SERVER*/
				//callback.accept(info);
				
				
				
			}

			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				//updateClients("new client on server: client #"+count);
				
				//updateClients();
				
				 while(true) {
					    try {
					    	//String data = in.readObject().toString();
					    	
					    	/*Get the info of the GUICLient*/
					    	infoClient data = (infoClient) in.readObject();
					    	
					    	/*THIS CALLBACK UPDATE SERVER*/
					    	callback.accept(data);
					    	
					    	
					    	
					    	
					    	/*SEND INFO TO CLIENT*/
					    	
					    	
					    	//callback.accept("client: HOLA " + count + " sent: " + data);
					    	
					    	//updateClients("client # HOLA 2 "+count+" said: "+data);
					    	
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	//updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}


