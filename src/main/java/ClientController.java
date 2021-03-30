import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ClientController  implements Initializable {

	 @FXML
	 private ListView<String> messagesList;
	 
	 @FXML
	 private TextField messageTextField;
	 
	 @FXML
	 private ComboBox<String> comboClients;
	 
	 @FXML
	  private ListView<String> clientList;
	 
	 
	
	Client clientConnection;
	
	int ComboSize = 0;
	
	infoClient info;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		clientConnection = new Client(data->{
			info = (infoClient) data;
			Platform.runLater(()->{
				
				comboClients.getItems().clear();
				
				for (int i = 0; i < info.clientsOnline.size(); i++) {
					String onlineClient = info.clientsOnline.get(i);
					System.out.println("Combo: "+ onlineClient);
					comboClients.getItems().add(onlineClient);
					ComboSize = i + 1;
				}
				comboClients.getSelectionModel().selectFirst();
				
				//messagesList.getItems().add(data.toString());
				
				});
			});
			
		clientConnection.start();
	}
	
	
	public void sendButton(ActionEvent e) throws IOException{
		
		info.message = messageTextField.getText();
		messagesList.getItems().add(info.message);
		clientConnection.send(info); 
		messageTextField.clear();
		
	}// end of sendButton
	
	public void addClientButton(ActionEvent e) throws IOException{
		
		String selectClient = comboClients.getValue();
		clientList.getItems().add(selectClient);
		
		
	}// end of addClientButton
	
	public void clearListButton(ActionEvent e) throws IOException{
		
		
		clientList.getItems().clear();
		
		
	}// end of clearListButton
	
	public void addAllButton(ActionEvent e) throws IOException{
		
		for (int i = 0; i < ComboSize; i++) { 
			String selectClient = comboClients.getItems().get(i);
			clientList.getItems().add(selectClient);
		}
		
		
		
	}// end of addAllButton
	
	
	
	
	

}
