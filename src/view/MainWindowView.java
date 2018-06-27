package view;

import controller.RecordController;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Record;
import presistence.DataPresistence;

import java.time.LocalDate;

public class MainWindowView extends Application{

	public static void main(String[] args) {
		DataPresistence<Record> dt = new DataPresistence<>();
		dt.createMainDirectory();
		dt.createBackupDirectory();
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
		Label lbTotalReceitaDiaria = new Label("Total:-");
		Label lbTotalDespesaFatura = new Label("Total:-");
		Label lbTotalDespesa = new Label("Total:-");
		Label lbTotalIVA = new Label("Total:-");
		
		TextField txtfReceitaDiaria = new TextField();
		TextField txtfDespesaFatura = new TextField();
		TextField txtfDespesa = new TextField();
		TextField txtfIVA = new TextField();
		
		Button btnRegist = new Button("Registar");
		
		/**set action of controls**/
		menuItemConsultarTodos.setOnAction(e -> {
			Stage newStage = new Stage();
			new RegestryView().initUI(stage,newStage,false);
			stage.hide();
		});

		btnRegist.setOnAction(e -> {
			RecordController controller = new RecordController();
			double receitaDiaria = Double.parseDouble(txtfReceitaDiaria.getText());
			double despesaFatura = Double.parseDouble(txtfDespesaFatura.getText());
			double despesa = Double.parseDouble(txtfDespesa.getText());
			double IVA = Double.parseDouble(txtfIVA.getText());
			boolean result = controller.createNewRecord(receitaDiaria, despesaFatura, despesa, IVA, false);
			Alert alert = null;

			if(result){
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Confirmação");
				alert.setHeaderText("Registo feito com sucesso");
				alert.setContentText(null);
			}
			else{
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Erro ao efetura o registo de dados");
				alert.setContentText(null);
			}

			alert.show();
		});
		
		/**add controls to layout manager**/
		VBox labelTextFieldReceitaDiariaBox = new VBox(10);
		labelTextFieldReceitaDiariaBox.setAlignment(Pos.CENTER);
		labelTextFieldReceitaDiariaBox.setPadding(new Insets(5));
		labelTextFieldReceitaDiariaBox.getChildren().addAll(lbReceitaDiaria, txtfReceitaDiaria, lbTotalReceitaDiaria);
		
		VBox labelTextFieldDespesaFaturaBox = new VBox(10);
		labelTextFieldDespesaFaturaBox.setAlignment(Pos.CENTER);
		labelTextFieldDespesaFaturaBox.setPadding(new Insets(5));
		labelTextFieldDespesaFaturaBox.getChildren().addAll(lbDespesaFatura, txtfDespesaFatura, lbTotalDespesaFatura);
		
		VBox labelTextFieldDespesaBox = new VBox(10);
		labelTextFieldDespesaBox.setAlignment(Pos.CENTER);
		labelTextFieldDespesaBox.setPadding(new Insets(5));
		labelTextFieldDespesaBox.getChildren().addAll(lbDespesa, txtfDespesa, lbTotalDespesa);
		
		VBox labelTextFieldIVABox = new VBox(10);
		labelTextFieldIVABox.setAlignment(Pos.CENTER);
		labelTextFieldIVABox.setPadding(new Insets(5));
		labelTextFieldIVABox.getChildren().addAll(lbIVA, txtfIVA, lbTotalIVA);

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
		
		Scene scene = new Scene(root, 540, 190);
		
		stage.setTitle("Pastel Registo Diário");
		stage.setScene(scene);
		stage.show();
		
	}

}
