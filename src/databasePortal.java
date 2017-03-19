



import java.io.File;

import javax.imageio.ImageIO;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class databasePortal extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("");
       
        BorderPane root = new BorderPane();
           
        root.setLeft(createMenu(root));       
        root.setCenter(createSignUpForm());
      	 
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public VBox createMenu(BorderPane root) {
    	 
    	 VBox vbox = new VBox(); 
         
         Button btn0 = new Button("Sign up");
         btn0.setMaxWidth(Double.MAX_VALUE);
         btn0.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                root.setCenter(createSignUpForm());
             }
         });
                      
         Button btn1 = new Button("Add product to your order");
         btn1.setMaxWidth(Double.MAX_VALUE);
         btn1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 Text text = new Text("add product");
                 root.setCenter(text);
             }
         });
         
         
         Button btn2 = new Button("Pay for your order");
         btn2.setMaxWidth(Double.MAX_VALUE);
         btn2.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 Text text = new Text("pay");
                 root.setCenter(text);
             }
         });
         
         
         Button btn3 = new Button("Look up coupons");
         btn3.setMaxWidth(Double.MAX_VALUE);
         btn3.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 HBox hbox = new HBox(); 
            	 hbox.setSpacing(5);
            	 hbox.setPadding(new Insets(30, 20, 10, 20)); 
            	 TextField TextField = new TextField("Enter product name...");
            	 TextField.setPrefWidth(200);
            	 Button btn = new Button("Search");
            	 hbox.getChildren().addAll(TextField, btn); 
                 root.setCenter(hbox);
             }
         });
         
         Button btn4 = new Button("Create a regular order");
         btn4.setMaxWidth(Double.MAX_VALUE);
         
         Button btn5 = new Button("Rate your delivery man");
         btn5.setMaxWidth(Double.MAX_VALUE);
         btn5.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 VBox vbox = new VBox(); 
            	 vbox.setPadding(new Insets(30, 20, 10, 20));  
            	 
            	 HBox one = new HBox(); 
            	 one.setSpacing(5); 
            	 Label label = new Label("Delivery Man Name:");
            	 TextField textField = new TextField("");
            	 textField.setPrefWidth(200);
            	 one.getChildren().addAll(label, textField); 
          
            	 HBox two = new HBox(); 
            	 Button btn1 = new Button();
            	 Button btn2 = new Button();
            	 Image emptystar = new Image(getClass().getResourceAsStream("./emptyStar.png")); 
            	 btn1.setbac
            	 Image star = new Image(getClass().getResourceAsStream("./star.png")); 
            	btn1.setGraphic(new ImageView(emptystar));
            		 btn2.setGraphic(new ImageView(star));
            	 
            	 
            	
            	 two.getChildren().addAll(btn1,btn2); 
            	// Button btn = new Button("Submit");
            	 
            	 vbox.getChildren().addAll(one, two ); 
                 root.setCenter(vbox);
             }
         });
            
         Button btn6 = new Button("Quit");
         btn6.setMaxWidth(Double.MAX_VALUE);
         btn6.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.exit(0);
             }
         });
         
         vbox.setSpacing(0);
         vbox.setPadding(new Insets(0, 20, 10, 0)); 
         vbox.getChildren().addAll(btn0,btn1,btn2,btn3,btn4,btn5,btn6); 
         return vbox;       
    }
    
    public GridPane createSignUpForm(){
	
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
				
		Text scenetitle = new Text("Sign Up Below");
		grid.add(scenetitle, 0, 0, 2, 1);
	
		//name
		Label name = new Label("Name:");
		grid.add(name, 0, 1);
		TextField nameTextField = new TextField();
		grid.add(nameTextField, 1, 1);
	
		//password
		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		    
	    //address
	    Label address = new Label("Address:");
		grid.add(address, 0, 3);
		TextField addressTextField = new TextField();
		grid.add(addressTextField, 1, 3);
		
	    //phone
		Label phone = new Label("Phone:");
		grid.add(phone, 0, 4);
		TextField phoneTextField = new TextField();
		grid.add(phoneTextField, 1, 4);
	    
		//email 
		Label email = new Label("Email :");
		grid.add(email, 0, 5);
		TextField emailTextField = new TextField();
		grid.add(emailTextField, 1, 5);
		
		Button btn = new Button("Sign up");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 7);
		
		//get and verify text 
		
		return grid; 
	}

}
	

//sign up 
//add product to order 
//pay for order 
	// include redeeming points and coupons 
//look up coupons for a product 
//create a regular order 
//rate a delivery man 
// quit 
