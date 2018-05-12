package view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindowView extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		initUI(primaryStage);
	}
	
	private void initUI(Stage stage) {
		BorderPane root = new BorderPane();
		GridPane gridPaneNode = new GridPane();
		gridPaneNode.setAlignment(Pos.TOP_CENTER);
		//root.setGridLinesVisible(true);
		gridPaneNode.setHgap(8);
		gridPaneNode.setVgap(8);
		gridPaneNode.setPadding(new Insets(5));
		
		/**create controls**/
		MenuBar menuBar = new MenuBar();
		Menu menuConsultarRegistos = new Menu("Consultar Registos");
		Menu menuExportar = new Menu("Exportar");
		Menu menuAcerca = new Menu("Acerca");
		
		MenuItem menuItemExportarPDF = new MenuItem("PDF");
		MenuItem menuItemConsultarTodos = new MenuItem("Todos registos");
		MenuItem menuItemConsultarPorDatas = new MenuItem("Por datas");
		
		menuConsultarRegistos.getItems().add(menuItemConsultarTodos);
		menuConsultarRegistos.getItems().add(menuItemConsultarPorDatas);
		menuExportar.getItems().add(menuItemExportarPDF);
		
		menuBar.getMenus().add(menuConsultarRegistos);
		menuBar.getMenus().add(menuExportar);
		menuBar.getMenus().add(menuAcerca);
		
		Label lbReceitaDiaria = new Label("Receita Diária");
		Label lbDespesaFatura = new Label("Despesa/Fatura");
		Label lbDespesa = new Label("Despesa");
		Label lbIVA = new Label("IVA");
		
		TextField txtfReceitaDiaria = new TextField();
		TextField txtfDespesaFatura = new TextField();
		TextField txtfDespesa = new TextField();
		TextField txtfIVA = new TextField();
		
		Button btnRegist = new Button("Registar");
		
		/**set action of controls**/
		menuItemConsultarTodos.setOnAction(e -> {
			Stage newStage = new Stage();
			new RegestryView().initUI(stage,newStage);
			stage.hide();
		});
		
		/**add controls to layout manager**/
		VBox labelTextFieldReceitaDiariaBox = new VBox(10);
		labelTextFieldReceitaDiariaBox.setAlignment(Pos.CENTER);
		labelTextFieldReceitaDiariaBox.setPadding(new Insets(5));
		labelTextFieldReceitaDiariaBox.getChildren().addAll(lbReceitaDiaria, txtfReceitaDiaria);
		
		VBox labelTextFieldDespesaFaturaBox = new VBox(10);
		labelTextFieldDespesaFaturaBox.setAlignment(Pos.CENTER);
		labelTextFieldDespesaFaturaBox.setPadding(new Insets(5));
		labelTextFieldDespesaFaturaBox.getChildren().addAll(lbDespesaFatura, txtfDespesaFatura);
		
		VBox labelTextFieldDespesaBox = new VBox(10);
		labelTextFieldDespesaBox.setAlignment(Pos.CENTER);
		labelTextFieldDespesaBox.setPadding(new Insets(5));
		labelTextFieldDespesaBox.getChildren().addAll(lbDespesa, txtfDespesa);
		
		VBox labelTextFieldIVABox = new VBox(10);
		labelTextFieldIVABox.setAlignment(Pos.CENTER);
		labelTextFieldIVABox.setPadding(new Insets(5));
		labelTextFieldIVABox.getChildren().addAll(lbIVA, txtfIVA);

		HBox inputBox = new HBox(10);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.setPadding(new Insets(5));
		inputBox.getChildren().addAll(labelTextFieldReceitaDiariaBox,
				labelTextFieldDespesaFaturaBox,
				labelTextFieldDespesaBox,
				labelTextFieldIVABox);
		
		GridPane.setHalignment(btnRegist, HPos.RIGHT);
		
		gridPaneNode.add(inputBox, 0, 0);
		gridPaneNode.add(btnRegist, 0, 4);
		
		root.setTop(menuBar);
		root.setCenter(gridPaneNode);
		
		Scene scene = new Scene(root, 540, 170);
		
		stage.setTitle("Pastel Registo Diário");
		stage.setScene(scene);
		stage.show();
		
	}

}
