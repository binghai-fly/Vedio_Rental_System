package last;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo extends Application {
	Manager manager=new Manager();
	private HashMap<String, Customer> userMap=new HashMap<String, Customer>();
	private Customer customer;
	TextArea resArea=new TextArea();
	Stage primaryStage;
	Scene mainScene;
	public void initData() {
		CD cd1=new CD("<<Baby>>", "3mins36s", "Justin Bieber", "Great!");
		CD cd2=new CD("<<See You Again>>","3mins49s","Wiz Khalifa,Charlie Puth","Nice!");
		
		DVD dvd1=new DVD("<<Pride & Prejudice>>", "127mins",  "Joe Wright", "Good!");
		DVD dvd2=new DVD("<<Jane Eyre>>","112mins","Franco Zeffirelli","Perfect!");
		
		manager.add(cd1);
		manager.add(cd2);
		manager.add(dvd1);
		manager.add(dvd2);
	}
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		
		initData();
		MainScene();
		
		 primaryStage.setScene(mainScene);
	     primaryStage.setTitle("影音管理系统");
	     primaryStage.show();
		
	}
	
	public void MainScene() {
		Button bt1=new Button("Manager");
		Button bt2=new Button("Customer");
		bt1.setOnAction(e->{
			primaryStage.setScene(ManagerUIScene());
		});
		
		bt2.setOnAction(e->{
			primaryStage.setScene(CustomerUIScene());
		});
		VBox root = new VBox(20, bt1, bt2);
        root.setStyle("-fx-padding: 50; -fx-alignment: center;");
        mainScene = new Scene(root, 600, 400);
	}
	
	public Scene ManagerUIScene() {
		PasswordField passwordField =new PasswordField();
		PasswordField passConfirm=new PasswordField();
		Label label1 =new Label("Please input password :");
		Label label2 =new Label("Please input password again :");
		passConfirm.setPromptText("Please input password again");
		passwordField.setPromptText("Please input password");
		Button loginBtn=new Button("Log in");
		Button backBtn = new Button("Back"); 
		TextArea resArea = new TextArea();
		resArea.setEditable(false);
		resArea.setPrefHeight(300);
		
		loginBtn.setOnAction(e->{
			resArea.clear();
			String password=passwordField.getText();
			String passCon=passConfirm.getText();
			if (password.isEmpty()||passCon.isEmpty()) {
				resArea.appendText("The password cannot be left blank\nPlease input again!");
			}
			
			else if (!passCon.equals(password)) {
				resArea.appendText("The passwords entered are different\nPlease input again!");
			}
			else if (!passCon.equals(manager.getPassword())) {
				resArea.appendText("Incorrect password!");
			}
			
			else if (passCon.equals(manager.getPassword())) {
				resArea.appendText("The password is correct!");
				primaryStage.setScene(ManagerScene());
			}
			
			passConfirm.clear();
			passwordField.clear();
			
		});
		
		 backBtn.setOnAction(e -> {
	            primaryStage.setScene(mainScene);
	        });
		
		VBox buttonBox = new VBox(10,label1, passwordField,label2, passConfirm, loginBtn,backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        // 5. 返回这个界面的Scene
        return new Scene(root, 600, 500);
		
	}
	
	public Scene CustomerUIScene() {
		TextField tf=new TextField();
		Label label =new Label("Please input your name:");
		tf.setPromptText("Please input your name");
		Button enterBtn=new Button("Enter");
		Button backBtn =new Button("Back");
		
		
		enterBtn.setOnAction(e->{
			String nameString=tf.getText();
			if (userMap.containsKey(nameString)) {
				customer =userMap.get(nameString);
			}
			else {
				Customer newcCustomer=new Customer(nameString);
				userMap.put(nameString, newcCustomer);
				customer=newcCustomer;
			}
			primaryStage.setScene(CustomerScene());
		});
		backBtn.setOnAction(e -> {
			primaryStage.setScene(mainScene);
	    });
		
		VBox buttonBox = new VBox(10,label,tf,enterBtn,backBtn);
        VBox root = new VBox(20, buttonBox);
        root.setStyle("-fx-padding: 20;");

        // 5. 返回这个界面的Scene
        return new Scene(root, 600, 500);
	}
	
	
	public Scene ManagerScene() {
		resArea.clear();
		Button showBtn = new Button("Show");
        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button backBtn = new Button("Back");
        Button showDeBtn =new Button("Detailed Show");
        Button searchBtn=new Button("Search");
        
        // 2. 创建结果显示区
        TextArea resArea = new TextArea();
        resArea.setEditable(false);
        resArea.setPrefHeight(300);

        // 3. 绑定按钮事件
      
        showBtn.setOnAction(e -> {
            resArea.clear();
            for (Item item : Manager.list) {
                resArea.appendText(item.print()+"\n");
            }
        });

        // 返回按钮：切换回主界面
        backBtn.setOnAction(e -> {
            primaryStage.setScene(mainScene);
        });
        
        addBtn.setOnAction(e->{
        	primaryStage.setScene(AddScene());
        });
        
        showDeBtn.setOnAction(e->{
        	resArea.clear();
        	for (Item item:Manager.list) {
        		resArea.appendText(item.toString()+"\n");
        	}
        });
        deleteBtn.setOnAction(e->{
        	primaryStage.setScene(DeleteScene());
        });
        
        searchBtn.setOnAction(e->{
        	primaryStage.setScene(SearchMScene());
        	
        });
        
        // 4. 布局
        VBox buttonBox = new VBox(10, showBtn, showDeBtn,searchBtn,addBtn, deleteBtn, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        // 5. 返回这个界面的Scene
        return new Scene(root, 600, 500);
	}
	
	public Scene CustomerScene() {
		resArea.clear();
		Button showBtn = new Button("Show");
        Button borrowBtn = new Button("Borrow");
        Button retBtn = new Button("Return");
        Button backBtn = new Button("Back"); // 返回按钮
        Button showDeBtn =new Button("Detailed Show");
        Button searchBtn=new Button("Search");
        Button searchSelfBtn =new Button("Record");
        // 2. 创建结果显示区
        TextArea resArea = new TextArea();
        resArea.setEditable(false);
        resArea.setPrefHeight(300);

        // 3. 绑定按钮事件
        showBtn.setOnAction(e -> {
            resArea.clear();
            for (Item item : Manager.list) {
                resArea.appendText(item.print()+"\n");
            }
        });

        
        searchBtn.setOnAction(e->{
        	primaryStage.setScene(SearchScene());
        	
        });
        
        backBtn.setOnAction(e -> {
            primaryStage.setScene(mainScene);
        });
        
        retBtn.setOnAction(e->{
        	primaryStage.setScene(ReturnScene());
        });
        borrowBtn.setOnAction(e->{
        	
        	primaryStage.setScene(BorrowScene());
        });
        showDeBtn.setOnAction(e->{
        	resArea.clear();
        	for (Item item:Manager.list) {
        		resArea.appendText(item.toString()+"\n");
        	}
        });
        searchSelfBtn.setOnAction(e->{
        	resArea.clear();
        	resArea.appendText("\t\tHello "+customer.getName()+"\n\n");
        	resArea.appendText(customer.SearchSelf());
        });

        // 4. 布局
        VBox buttonBox = new VBox(10, showBtn, showDeBtn,borrowBtn, retBtn,searchBtn,searchSelfBtn,backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        // 5. 返回这个界面的Scene
        return new Scene(root, 600, 500);
	}
	
	public Scene ReturnScene() {
		resArea.clear();
		TextField tf=new TextField();
		Label label =new Label("Please enter the name or index of the item you are returning:");
		tf.setPromptText("Please enter the name or index of the item you are returning");
		
		
		Button retBtn = new Button("Return By title");
		Button retIdxButton=new Button("Return By Index");
        Button backBtn = new Button("Back"); 
        
        TextArea resArea = new TextArea();
        resArea.setEditable(false);
        resArea.setPrefHeight(300);

        
        retBtn.setOnAction(e -> {
        	resArea.clear();
        	resArea.appendText(customer.ReturnByTitle(tf.getText()));
        });
        retIdxButton.setOnAction(e->{
        	resArea.clear();
        	if (tf.getText().isEmpty()) resArea.appendText("Please input index of book which you want to return ");
        	else resArea.appendText(customer.ReturnByIndex(Integer.parseInt(tf.getText())));
        });

        
        backBtn.setOnAction(e -> {
            primaryStage.setScene(CustomerScene());
        });

       
        VBox buttonBox = new VBox(10,label , tf, retBtn, retIdxButton,backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        // 5. 返回这个界面的Scene
        return new Scene(root, 600, 500);
	}
	
	public Scene BorrowScene() {
		resArea.clear();
		TextField tf=new TextField();
		Label label =new Label("Please enter the name or index of the item you are borrowing:");
		tf.setPromptText("Please enter the name or index of the item you are borrowing");
		
		
		Button borrowBtn = new Button("Borrow By Title");
		Button borByidxButton=new Button("Borrow By Index");
        Button backBtn = new Button("Back");

        
        TextArea resArea = new TextArea();
        resArea.setEditable(false);
        resArea.setPrefHeight(300);

        
        borrowBtn.setOnAction(e -> {
        	resArea.clear();
     		resArea.appendText(customer.borrowByTitle(tf.getText()));
        });
        
        borByidxButton.setOnAction(e->{
        	resArea.clear();
        	if (tf.getText().isEmpty()) resArea.appendText("Please input index of book which you want to borrow ");
        	else resArea.appendText(customer.borrowByIndex(Integer.parseInt(tf.getText())));
        });
        
        backBtn.setOnAction(e -> {
            primaryStage.setScene(CustomerScene());
        });

        
        VBox buttonBox = new VBox(10,label,tf, borrowBtn,borByidxButton, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
	}
	
	public Scene AddScene() {
		resArea.clear();
		Button addCdBtn = new Button("Add CD");
		Button addDvdBtn=new Button("Add DVD");
        Button backBtn = new Button("Back");

        TextArea resArea = new TextArea();
        resArea.setEditable(false);
        resArea.setPrefHeight(300);

        addCdBtn.setOnAction(e -> {
        	primaryStage.setScene(addCDScene());
        	
        });

        addDvdBtn.setOnAction(e -> {
        	primaryStage.setScene(addDVDScene());
        	
        });
        
        backBtn.setOnAction(e -> {
            primaryStage.setScene(ManagerScene());
        });

        VBox buttonBox = new VBox(10,addCdBtn,addDvdBtn, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
	}
	
	public Scene addCDScene() {
		resArea.clear();
		Label label1 =new Label("name:");
		Label label2 =new Label("playingTime:");
		Label label3 =new Label("comment:");
		Label label4 =new Label("Singer:");
		TextField tf=new TextField();
		tf.setPromptText("name");
		TextField tf1=new TextField();
		tf1.setPromptText("playingTime");
		TextField tf2=new TextField();
		tf2.setPromptText("comment");
		TextField tf3=new TextField();
		tf3.setPromptText("Singer");
		
		Button addBtn = new Button("Add");
		Button backBtn = new Button("Back");
		
		addBtn.setOnAction(e->{
			CD cd=new CD(tf.getText(),tf1.getText(), tf3.getText(), tf2.getText());
			manager.add(cd);
			resArea.clear();
			resArea.appendText("Add Successfully!");
		});
		 backBtn.setOnAction(e -> {
	            primaryStage.setScene(AddScene());
	        });

		
		VBox buttonBox = new VBox(10,label1,tf,label2,tf1,label3,tf2,label4,tf3,addBtn, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
		
	}
	public Scene addDVDScene() {
		resArea.clear();
		TextField tf=new TextField();
		tf.setPromptText("name");
		TextField tf1=new TextField();
		tf1.setPromptText("playingTime");
		TextField tf2=new TextField();
		tf2.setPromptText("comment");
		TextField tf3=new TextField();
		tf3.setPromptText("Director");
		Label label1 =new Label("name:");
		Label label2 =new Label("playingTime:");
		Label label3 =new Label("comment:");
		Label label4 =new Label("Director:");
		
		Button addBtn = new Button("Add");
		Button backBtn = new Button("Back");
		
		addBtn.setOnAction(e->{
			DVD cd=new DVD(tf.getText(),tf1.getText(), tf3.getText(), tf2.getText());
			manager.add(cd);
			resArea.clear();
			resArea.appendText("Add Succssefully!");
		});
		 backBtn.setOnAction(e -> {
	            primaryStage.setScene(AddScene());
	        });

		
		VBox buttonBox = new VBox(10,label1,tf,label2,tf1,label3,tf2,label4,tf3, addBtn, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
		
	}
	
	public Scene DeleteScene() {
		resArea.clear();
		TextField tf=new TextField();
		Label label =new Label("Please enter the name or index of the item you are deleting:");
		tf.setPromptText("Please enter the name or index of the item you are deleting:");
		
		Button deleteBtn = new Button("Delete by title");
		Button deleteIdxBtn = new Button("Delete by Index");
		Button backBtn = new Button("Back");
		
		deleteBtn.setOnAction(e->{
			resArea.clear();
			resArea.appendText(manager.deleteByTitle(tf.getText()));
		});
		deleteIdxBtn.setOnAction(e->{
			resArea.clear();
			if (tf.getText().isEmpty()) resArea.appendText("Please input index of book which you want to borrow ");
        	else resArea.appendText(manager.deleteByIndex(Integer.parseInt(tf.getText())));
		});
		 backBtn.setOnAction(e -> {
	            primaryStage.setScene(ManagerScene());
	        });

		
		VBox buttonBox = new VBox(10,label,tf, deleteBtn, deleteIdxBtn,backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
	}
	
	public Scene SearchScene() {
		resArea.clear();
		TextField tf=new TextField();
		tf.setPromptText("name");
		Label label =new Label("name:");
		
		Button searchBtn = new Button("Search");
		Button backBtn = new Button("Back");
		
		searchBtn.setOnAction(e->{
			resArea.clear();
			resArea.appendText(customer.SearchSth(tf.getText()));
		});
		 backBtn.setOnAction(e -> {
	            primaryStage.setScene(CustomerScene());
	        });

		
		VBox buttonBox = new VBox(10,label ,tf,searchBtn, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
	}
	
	public Scene SearchMScene() {
		resArea.clear();
		TextField tf=new TextField();
		tf.setPromptText("name");
		Label label =new Label("name:");
		
		Button searchBtn = new Button("Search");
		Button backBtn = new Button("Back");
		
		searchBtn.setOnAction(e->{
			resArea.clear();
			resArea.appendText(manager.SearchSth(tf.getText()));
		});
		 backBtn.setOnAction(e -> {
	            primaryStage.setScene(ManagerScene());
	        });

		
		VBox buttonBox = new VBox(10,label ,tf,searchBtn, backBtn);
        VBox root = new VBox(20, buttonBox, resArea);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 600, 500);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
