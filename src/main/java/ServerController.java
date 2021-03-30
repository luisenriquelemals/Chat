import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ServerController implements Initializable {
	
	  @FXML
	  private ListView<String> serverList;
	
	  Server serverConnection;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
							
		 serverConnection = new Server( (data) -> {
			 infoClient info = (infoClient) data;
				Platform.runLater(()->{
					String message = "Client #"+ (info.ID + 1) + " send: "+ info.message;
					serverList.getItems().add(message);
				});
				
			});
		
	}// end of initialize
	
	
	
	

}
