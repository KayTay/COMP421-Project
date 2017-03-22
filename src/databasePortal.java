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
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.* ;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class databasePortal extends Application {
    public static void main(String[] args) {
		launch(args);

		// Register the driver.  You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			System.out.println("Successful Driver registration");
		}
		catch (Exception cnfe) {
			System.out.println("Class not found");
		}


		//// This is the url you must use for Postgresql.
////Note: This url may not valid now !
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		String usernamestring = "cs421g26";
		String passwordstring = "Joseph@421";

		try {
			Connection con = DriverManager.getConnection(url, usernamestring, passwordstring);
			Statement statement = con.createStatement();

			System.out.println("Successful Connection");

			// insert into DB
			String querySQL = "INSERT INTO users VALUES ('amanda.ivey@mail.mcgill.fr', 'password', 1234567890, '475 Ave Des Pins')";
//			String querySQL = "SELECT * From users"; //INSERT INTO users VALUES ('amanda.ivey@mail.mcgill.ca', 'password', 1234567890, '475 Ave Des Pins')";
			System.out.println (querySQL) ;
			java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
			while ( rs.next ( ) ) {
				String email = rs.getString ( 1 ) ;
				int phoneNum = rs.getInt (3);
				System.out.println ("email:  " + email);
				System.out.println ("name:  " + phoneNum);
			}
			System.out.println ("DONE");


		}
		catch (SQLException e) {
			System.out.println("Failed Connection");
			int sqlCode = e.getErrorCode(); // Get SQLCODE
			String sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: "  + sqlCode + "  sqlState: " + sqlState);
		}

		// Insert a new tuple
//	try {
//	    String querySQL = "INSERT INTO users VALUES ('amanda.ivey@mail.mcgill.ca', 'password', 1234567890, '475 Ave Des Pins')";
//	    System.out.println (querySQL) ;
//	    java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
//	    while ( rs.next ( ) ) {
//		String email = rs.getString ( 1 ) ;
//		int phoneNum = rs.getInt (3);
//		System.out.println ("email:  " + email);
//		System.out.println ("name:  " + phoneNum);
//	    }
//	    System.out.println ("DONE");
//	} catch (SQLException e)
//	    {
////		sqlCode = e.getErrorCode(); // Get SQLCODE
////		sqlState = e.getSQLState(); // Get SQLSTATE
////
////		// Your code to handle errors comes here;
////		// something more meaningful than a print would be good
////		System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
//	    }
//

	}
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("");
       
        BorderPane root = new BorderPane();    
        root.setCenter(clientWindow());
 
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    	
    }

    public TabPane clientWindow() {
    	
    	 TabPane tabPane = new TabPane();
    	 tabPane.setSide(Side.LEFT);
    	 tabPane.setRotateGraphic(true);  
    	 
    	 
    	 //add product 
    	 Tab addProduct = new Tab("Add Product"); 
    	 addProduct.setClosable(false);
    	 //addProduct.setContent(addProductContent()); 
    	 tabPane.getTabs().add(addProduct); 
    	 
    	 
    	 //Pay 
    	 Tab pay = new Tab("Check Out");
    	 pay.setClosable(false);
    	 tabPane.getTabs().add(pay); 
    	 
    	 //look up coupon
    	 Tab coupon = new Tab("Find Coupons");    	 
    	 coupon.setClosable(false);  
    	 coupon.setContent(couponContent()); 
    	 tabPane.getTabs().add(coupon); 
         
    	 //regular order
    	 Tab regularOrder = new Tab(); 
    	 regularOrder.setClosable(false);
    	 tabPane.getTabs().add(regularOrder); 
         
        
         
         Tab rate = new Tab(); 
         VBox vbox = new VBox(); 
    	 vbox.setPadding(new Insets(30, 20, 10, 20)); 
    	 pay.setClosable(false);
    	 tabPane.getTabs().add(pay); 
    	 
    	 HBox one = new HBox(); 
    	 one.setSpacing(5); 
    	 Label label = new Label("Delivery Man Name:");
    	 TextField textField2 = new TextField("");
    	 textField2.setPrefWidth(200);
    	 one.getChildren().addAll(label, textField2); 
  
    	 HBox two = new HBox(); 
    	 Button btn1 = new Button();
    	 Button btn2 = new Button();
//    	 Image emptystar = new Image(getClass().getResourceAsStream("./emptyStar.png")); 
//    	 btn1.setbac
//    	 Image star = new Image(getClass().getResourceAsStream("./star.png")); 
//    	btn1.setGraphic(new ImageView(emptystar));
//    		 btn2.setGraphic(new ImageView(star));
         rate.setContent(vbox);
         
        Tab logout = new Tab(); 
       
 
         Button btn6 = new Button("Quit");
         btn6.setMaxWidth(Double.MAX_VALUE);
         btn6.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.exit(0);
             }
         });
         
//         vbox.setSpacing(0);
//         vbox.setPadding(new Insets(0, 20, 10, 0)); 
//         vbox.getChildren().addAll(btn1,btn2,btn3,btn4,btn5,btn6); 
//         return vbox;   
         
         return tabPane; 
    }
    
    public void addProductContent() {
    	
    }
    
    public HBox couponContent() {
    	HBox hbox = new HBox(); 
   	 	hbox.setAlignment(Pos.TOP_CENTER);
   	 	hbox.setSpacing(5);
   	 	hbox.setPadding(new Insets(30, 20, 10, 20)); 
   	 	TextField textField = new TextField();
   	 	textField.setPromptText("Enter product name...");
   	 
   	 	textField.setPrefWidth(200);
   	 	Button btn = new Button("Search");
   	 	btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                queryCoupons(textField.getText()); 
            }
        });
   	 	hbox.getChildren().addAll(textField, btn);
   	 	return hbox; 
    }
    
    public void queryCoupons(String name) {
    	//TODO query database for coupons 
    	//format output to window 
    }
    
  
    public GridPane LoginWindow(){
	
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
			
		Text login = new Text(" Log In");
		login.setFont(Font.font ("Verdana", 20));
		grid.add(login, 1,0 );
		
		//name
		Label email = new Label("Email:");
		grid.add(email, 0, 1);
		TextField emailTextField = new TextField();
		grid.add(emailTextField, 1, 1);
	
		//password
		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		Button loginbtn = new Button("Log In");
		loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	verifyLogin(email.getText(), pw.getText()); 
            }
        });
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(loginbtn);
		grid.add(hbBtn, 1, 3);
		
		Separator separator = new Separator();
		grid.add(separator, 0,4,3,1); 
		
		Text signup = new Text("  No account?\nSign Up Below");
		signup.setFont(Font.font ("Verdana", 20));
		grid.add(signup, 1, 5);
	
		//name
		Label name = new Label("Name:");
		grid.add(name, 0, 6);
		TextField nameTextField = new TextField();
		grid.add(nameTextField, 1, 6);
	
		//email 
		Label email2 = new Label("Email :");
		grid.add(email2, 0, 7);
		TextField emailTextField2 = new TextField();
		grid.add(emailTextField2, 1, 7);
		
		//password
		Label pw2 = new Label("Password:");
		grid.add(pw2, 0, 8);
		PasswordField pwBox2 = new PasswordField();
		grid.add(pwBox2, 1, 8);
		    
	    //address
	    Label address = new Label("Address:");
		grid.add(address, 0, 9);
		TextField addressTextField = new TextField();
		grid.add(addressTextField, 1, 9);
		
	    //phone
		Label phone = new Label("Phone:");
		grid.add(phone, 0, 10);
		TextField phoneTextField = new TextField();
		grid.add(phoneTextField, 1, 10);
	    
		Button signUpbtn = new Button("Sign up");
		signUpbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	verifySignUp(name.getText(), email2.getText(), pw2.getText(), address.getText(), phone.getText()); 
            }
        });
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn2.getChildren().add(signUpbtn);
		grid.add(hbBtn2, 1, 11);
	
		return grid; 
    }
    
    public void verifyLogin(String email, String password)
    {
    	//TODO
    	//verify that strings are correct 
    	//add to database 
    	//on success jump to client window 
    }
    
    
    public void verifySignUp(String name, String email, String password, String address, String phone)
    {
    	//TODO
    	//verify that strings are correct 
    	//add to database 
     	//on success jump to client window 
    	
    }
}
	

//sign up 
//add product to order 
//pay for order 
	// include redeeming points and coupons 
//look up coupons for a product 
//create a regular order 
//rate a delivery man 
//quit 
