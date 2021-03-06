import java.util.HashMap;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.* ;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class databasePortal extends Application {
    static Connection con;
    BorderPane root;
    HashMap<String, Double> productCart = new HashMap<String, Double>();
    HashMap<String, Double> couponCart = new HashMap<String, Double>();

	public static void main(String[] args) {
		//establish database connection
		con = connect();
		//launch GUI
		launch(args);
	}
    
    public static Connection connect(){
    	// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			System.out.println("Successful Driver registration");
		}
		catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		//This is the url you must use for Postgresql.
		//Note: This url may not valid now !
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		String usernamestring = "cs421g26";
		String passwordstring = "Joseph@421";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, usernamestring, passwordstring);
			System.out.println("Successful Connection");
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed Connection");
			int sqlCode = e.getErrorCode(); // Get SQLCODE
			String sqlState = e.getSQLState(); // Get SQLSTATE 
			System.out.println("Code: "  + sqlCode + "  sqlState: " + sqlState);
		}
		return con; 
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("");
       
        root = new BorderPane();    
        root.setCenter(clientWindow());
 
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();	
    }

    public TabPane clientWindow() {
    	
    	 TabPane tabPane = new TabPane();
    	 tabPane.setSide(Side.LEFT);
    	 tabPane.setRotateGraphic(true);  
    	  	 
    	 //add product 
    	 Tab addProduct = new Tab("Add Product"); 
    	 addProduct.setClosable(false);
    	 addProduct.setContent(addProductContent()); 
    	 tabPane.getTabs().add(addProduct); 
    	 	 
    	 //check out 
    	 Tab checkOut = new Tab("Check Out");
    	 checkOut.setClosable(false);
    	 checkOut.setContent(checkOutContent()); 
    	 tabPane.getTabs().add(checkOut); 
    	 
    	 //look up coupon
    	 Tab coupon = new Tab("Find Coupons");    	 
    	 coupon.setClosable(false);  
    	 coupon.setContent(couponContent()); 
    	 tabPane.getTabs().add(coupon); 
         
    	 //rate your delivery man 
         Tab rate = new Tab("Rate Delivery Man"); 
    	 rate.setClosable(false);
    	 rate.setContent(rateContent());
    	 tabPane.getTabs().add(rate); 
         
    	 //logout (just quits)
    	 Tab logout = new Tab("Logout"); 
    	 tabPane.getTabs().add(logout); 
    	 logout.setClosable(false);
    	 tabPane.getSelectionModel().selectedItemProperty().addListener(
    			    new ChangeListener<Tab>() {
    			        @Override
    			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
    			          if(t1.equals(logout)) {
    			        	  System.exit(0);
    			          }
    			        }
    			    }
    			);
    	 
  
         return tabPane; 
    }
    
    public VBox addProductContent() {
    	VBox vbox = new VBox();
    	//vbox.setAlignment(Pos.TOP_CENTER);
    	vbox.setSpacing(10);
   	 	vbox.setPadding(new Insets(70, 30, 30, 20));

    	HBox hbox = new HBox(); 
   	 	hbox.setAlignment(Pos.TOP_CENTER);
   	 	hbox.setSpacing(5);

   	 	TextField textField = new TextField();
   	 	textField.setPromptText("Enter product name...");
   	 
   	 	textField.setPrefWidth(200);
   	 	Button btn = new Button("Search");

   	 	HBox searchResults = new HBox();
   	 	btn.setOnAction(new EventHandler<ActionEvent>() {
   	 		
            @Override
            public void handle(ActionEvent event) {
            	searchResults.getChildren().clear();
            	Double price = queryProducts(textField.getText()); 
            	displayProducts(textField.getText(), price, searchResults);
            	
            }
        });
   	 	hbox.getChildren().addAll(textField, btn);
   	 	vbox.getChildren().addAll(hbox, searchResults);
   	 	return vbox;
    }

    public void displayProducts(String search, Double price, HBox searchResults){

    	searchResults.setAlignment(Pos.BOTTOM_CENTER);
    	searchResults.setSpacing(150);
    	searchResults.setPadding(new Insets(10, 50, 50, 20));

		if(search.equals("bagel"))
		{
			Image img = new Image(getClass().getResourceAsStream("./bagel.jpg"));
			ImageView iv = new ImageView();
	        iv.setImage(img);
	        searchResults.getChildren().add(iv);
		}
		else if(search.equals("pumpkin pie"))
		{
			Image img = new Image(getClass().getResourceAsStream("./pumpkinPie.png"));
			ImageView iv = new ImageView();
	        iv.setImage(img);
	        searchResults.getChildren().add(iv);
		}
		else if(search.equals("banana"))
		{
			Image img = new Image(getClass().getResourceAsStream("./bananas.jpg"));
			ImageView iv = new ImageView();
	        iv.setImage(img);
	        searchResults.getChildren().add(iv);
		}
		else if(search.equals("shrimp"))
		{
			Image img = new Image(getClass().getResourceAsStream("./shrimp.jpg"));
			ImageView iv = new ImageView();
	        iv.setImage(img);
	        searchResults.getChildren().add(iv);
		}
		else if(search.equals("popcorn"))
		{
			Image img = new Image(getClass().getResourceAsStream("./popcorn.jpg"));
			ImageView iv = new ImageView();
	        iv.setImage(img);
	        searchResults.getChildren().add(iv);
		}

		Text p = new Text("$" + price.toString());

		Button btn = new Button ("Select");
		btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                productCart.put(search, price);
            }
        });

		searchResults.getChildren().addAll(p,btn);
    }
    
    
    public double queryProducts(String name) {

        Double cheapestPrice = 0.0;
        try {
           
            Statement statement = con.createStatement();
            //System.out.println("Successful Connection");
            String querySQL = "SELECT * From product WHERE name = '" + name + "';";
            //System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while ( rs.next ( ) ) {
                String productName = rs.getString("name");
                cheapestPrice = rs.getDouble("cheapestprice");
                //System.out.println ("Cheapest Price:  " + cheapestPrice);
            }
            //System.out.println ("DONE");
        }
        catch (SQLException e) {
            System.out.println("Failed Connection");
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: "  + sqlCode + "  sqlState: " + sqlState);
        }
      
        return cheapestPrice;
		
    }

    
    //TODO
    public HBox checkOutContent() {
    	HBox h = new HBox(); 
    	return h; 

    	// keep a list of products + price
    	// coupons + amount saved

    	// display sub total
    	//with coupon

    	// pay (use card on file)  >> success
    }
    
    public VBox couponContent() {
    	HBox hbox = new HBox(); 
   	 	hbox.setAlignment(Pos.TOP_CENTER);
   	 	hbox.setSpacing(5);
   	 	hbox.setPadding(new Insets(70, 20, 10, 20)); 
   	 	TextField textField = new TextField();
   	 	textField.setPromptText("Enter product name...");
   	 
   	 	textField.setPrefWidth(200);
   	 	Button btn = new Button("Search");
   	 	
   	 	HBox h2 = new HBox(); 
   	 	h2.setAlignment(Pos.CENTER);
   	 	btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String s = textField.getText(); 
            	if(!s.isEmpty())
            	{
            		h2.getChildren().clear();
            		Double savings = queryCoupons(s);
            		if(savings.equals(0.0))
            		{
            			Text t = new Text("No coupons found!"); 
            			h2.getChildren().add(t); 
            		}
            		else
            		{
            			displayCoupons(s, savings, h2); 
            		}
            		
            	}
            }
        });
   	 	hbox.getChildren().addAll(textField, btn);
   	 	VBox v = new VBox(); 
   	 	v.getChildren().addAll(hbox, h2); 
   	 	return v; 
    }
    
    public void displayCoupons(String search, Double savings, HBox h) {
    	
    	h.setAlignment(Pos.BOTTOM_CENTER);
    	h.setSpacing(150);
    	h.setPadding(new Insets(10, 50, 50, 20));
	
		Image img = new Image(getClass().getResourceAsStream("./coupon.png"));
		ImageView iv = new ImageView();
        iv.setImage(img);
		
		Text p = new Text("$" + savings.toString());

		Button btn = new Button ("Select");
		btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                couponCart.put(search, savings);
            }
        });

		h.getChildren().addAll(iv,p,btn);
    	
    	
    }
    
    public double queryCoupons(String name) {
        int itemNum = 0;
        int promocode = 0;
        double amountSaved = 0.0;
        try {
            //connect();
            //Statement statement = con.createStatement();
            //Connection con = DriverManager.getConnection("jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421", "cs421g26", "Joseph@421");
            Statement statement = con.createStatement();
           // System.out.println("Successful Connection");
            String queryProducts = "SELECT itemid From product WHERE name = '" + name + "';";
           // System.out.println(queryProducts);
            java.sql.ResultSet rs_products = statement.executeQuery (queryProducts);
            while (rs_products.next()) {
                //String productName = rs_products.getString("name");
                itemNum = (rs_products.getInt("itemid"));
                System.out.println(itemNum);
            }
            String queryAssociatedWith = "SELECT * FROM \"associatedWith\" WHERE itemid = " + itemNum + ";";
            //System.out.println(queryAssociatedWith);
            java.sql.ResultSet rs_promoCodes = statement.executeQuery(queryAssociatedWith);
            while ( rs_promoCodes.next() )  {
                promocode = rs_promoCodes.getInt("promocode");
               // System.out.println(promocode);
            }
            String queryCoupons = "SELECT * FROM coupon WHERE promocode = " + promocode + ";";
           // System.out.println(queryCoupons);
            java.sql.ResultSet rs_coupons = statement.executeQuery(queryCoupons);
            while ( rs_coupons.next () ) {
                amountSaved = rs_coupons.getDouble("amountsaved");
                //System.out.println(amountSaved);
            }

           // System.out.println ("DONE");
        }
        catch (SQLException e) {
            System.out.println("Failed Connection");
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: "  + sqlCode + "  sqlState: " + sqlState);
        }

        return amountSaved;

    	//format output to window
    }
  
    public VBox rateContent() {    
    	VBox v = new VBox(); 
    	v.setAlignment(Pos.TOP_CENTER);
    	v.setSpacing(5); 	
    	v.setPadding(new Insets(70, 20, 10, 20)); 
    	
    	
    	HBox h = new HBox(); 
		h.setAlignment(Pos.TOP_CENTER);
   	 	h.setSpacing(5); 	 
    	Label label = new Label("Delivery Man Name:");
    	TextField textField = new TextField("");
    	textField.setPrefWidth(200);
    	h.getChildren().addAll(label, textField); 
 
    	
    	Image emptystar = new Image(getClass().getResourceAsStream("./emptyStar.png")); 
    	Image star = new Image(getClass().getResourceAsStream("./star.png")); 
    	HBox h2 = new HBox(); 
    	h2.setAlignment(Pos.CENTER);
    	for(int i = 0; i<5; i++)
    	{
	    	ToggleButton btn1 = new ToggleButton();
	    	btn1.setGraphic(new ImageView(emptystar));
	    	btn1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {	 
	            	if(!btn1.isSelected())
	            	{
	            		btn1.setGraphic(new ImageView(emptystar));  
	            	}
	            	else 
	            	{
	            		btn1.setGraphic(new ImageView(star));  
	            	}
	            }
	        });
	    	h2.getChildren().addAll(btn1);
    	}
    	
    	Button submit = new Button("Submit"); 
    	submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {	
  
            	int selected = 0; 
            	ObservableList<Node> nodes = h2.getChildren(); 
            	for( Node n : nodes)
            	{
            		if(((ToggleButton)n).isSelected())
            		{
            			selected++; 
            		}
            	}
            	
            	String s = textField.getText(); 
            	
            	if(selected > 0 && !s.isEmpty())
            	{
            		boolean success = insertRating(s, selected); 
            		if(success) {
            			Text t = new Text("Success! Thank you for you input"); 
            			v.getChildren().addAll(t); 
            			textField.clear();
            			for( Node n : nodes)
                    	{
                    		((ToggleButton)n).setGraphic(new ImageView(emptystar));
                    	}
            		}
            		
            	}
            }
        });
    	
    	v.getChildren().addAll(h,h2,submit); 
         
    	 return v; 
    }
   
    public boolean insertRating(String name, int rating)
    {
    	//given the delivery man's name update their rating
        try {
            Statement statement = con.createStatement();
            //System.out.println("Successful Connection");
            String querySQL = "UPDATE \"deliveryMan\" SET rating = "+ rating +" WHERE name = '" + name + "';";
            //System.out.println (querySQL) ;
            statement.executeUpdate (querySQL) ;
            //System.out.println ("Successful insert");
            return true; 
        }
        catch (SQLException e) {
            System.out.println("Failed Connection");
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: "  + sqlCode + "  sqlState: " + sqlState);
        }
        return false; 
    }
    
    public GridPane loginWindow(){
	
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
            	boolean success = verifyLogin(emailTextField.getText(), pwBox.getText());
            	if(success == false)
            	{
            		 Text error = new Text("Invalid Email or Password!"); 
            		 error.setFill(Color.RED); 
            		 grid.add(error,1,4,1,2);
            	}
            }
        });
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(loginbtn);
		grid.add(hbBtn, 1, 3);
		
		Separator separator = new Separator();
		grid.add(separator, 0,6,3,1); 
		
		Text signup = new Text("  No account?\nSign Up Below");
		signup.setFont(Font.font ("Verdana", 20));
		grid.add(signup, 1, 7);
	
		//email 
		Label email2 = new Label("Email :");
		grid.add(email2, 0, 8);
		TextField emailTextField2 = new TextField();
		grid.add(emailTextField2, 1, 8);
		
		//password
		Label pw2 = new Label("Password:");
		grid.add(pw2, 0, 9);
		PasswordField pwBox2 = new PasswordField();
		grid.add(pwBox2, 1, 9);
		    
	    //address
	    Label address = new Label("Address:");
		grid.add(address, 0, 10);
		TextField addressTextField = new TextField();
		grid.add(addressTextField, 1, 10);
		
	    //phone
		Label phone = new Label("Phone:");
		grid.add(phone, 0, 11);
		TextField phoneTextField = new TextField();
		grid.add(phoneTextField, 1, 11);
	    
		Button signUpbtn = new Button("Sign up");
		signUpbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	boolean success =  verifySignUp(emailTextField2.getText(), pwBox2.getText(), addressTextField.getText(), phoneTextField.getText());
            	if(success == false)
            	{
            		 Text error = new Text("Invalid input, try again!"); 
            		 error.setFill(Color.RED); 
            		 grid.add(error,1,13,1,2);
            	}
            }
        });
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn2.getChildren().add(signUpbtn);
		grid.add(hbBtn2, 1, 12);
	
		return grid; 
    }

    public boolean verifyLogin(String email, String password)
    {
    	//verify that strings are correct values 
    	if(email.length() > 50 || email.length() <= 0)
    	{
    		System.out.println("email prob");
    		return false;
    	} 	
    	if(password.length() > 20 || password.length() <= 0)
    	{
    		System.out.println("psw prob");
    		return false;
    	}
    	
    	//query database for user 
    	String query = "SELECT * FROM users WHERE email = '"+ email +"';";
    	Statement stmt = null; 
		try {
			stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	           String pas = rs.getString("password"); 
	           System.out.println(pas);
	           if(password.equals(pas))
	           {
	        	   root.setCenter(clientWindow());
	           }
	        }
		} 
		catch (SQLException e ) {} 
		 
		try {
			stmt.close();
		} catch (SQLException e) {}
		
		return true; 
    }
    
    public boolean verifySignUp(String email, String password, String address, String phone)
    {
        if ((email.length() < 50 && email.length() > 0) && (password.length()<20 && password.length() > 0) && (address.length()< 50 && address.length() > 0) && (Integer.parseInt(phone) != 0 && phone.length() > 0)) {
			String query = "INSERT INTO users VALUES('" + email + "' , '" + password + "', " + Integer.parseInt(phone) + ", '" + address + "');";
			System.out.println(query);
			root.setCenter(clientWindow());
        	return true;
        }
        else {
            return false;
        }
    }
}
	