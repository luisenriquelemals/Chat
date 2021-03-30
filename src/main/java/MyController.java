

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyController implements Initializable {
	
	  @FXML
	  private Pane ServerClientPanme;
	
	
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
  
  
	
	public void serverButton(ActionEvent e) throws IOException {
		
		 FXMLLoader loaderServer = new FXMLLoader(getClass().getResource("/FXML/serverFXML.fxml"));
    	 Parent rootServer = loaderServer.load(); 
    	 ServerController serverController = loaderServer.getController();
		 Scene scene = new Scene (rootServer);
		 //rootServer.getStylesheets().add("/styles/gameStyle.css");//set style
    	 Stage newStage1 = new Stage();
    	 newStage1.setScene(scene);
    	 newStage1.setTitle("Server");
    	 //newStage1.setX(500);
    	 //newStage1.setY(300);
    	 newStage1.setResizable(false);
    	 newStage1.setOnCloseRequest(m->{
    		 Platform.exit();
             System.exit(0);
    	 });
    	 newStage1.show();
    	 
        
    	 //Stage var = (Stage) ServerClientPanme.getScene().getWindow();
 		// var.close();
        
	}
	
	public void clientButton(ActionEvent e) throws IOException{
		
		FXMLLoader loaderClient = new FXMLLoader(getClass().getResource("/FXML/clientFXML.fxml"));
   	 	Parent rootClient = loaderClient.load(); 
   	 	ClientController clientController = loaderClient.getController();
		Scene scene = new Scene (rootClient);
		//rootClient.getStylesheets().add("/styles/gameStyle.css");//set style
	   	Stage newStage = new Stage();
	   	newStage.setScene(scene);
	   	newStage.setTitle("Client");
	   	 //newStage1.setX(500);
	   	 //newStage1.setY(300);
	    newStage.setResizable(false);
	   	newStage.show();
      
	}
	
	

}
