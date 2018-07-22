package view;

import controller.MonthlyRecordController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Observer;
import util.Setting;

import java.util.Optional;

public class MainWindowView extends Application implements Observer {

	BorderPane root;
	GridPane gridPaneNode;
	MenuBar menuBar;
	Menu menuConsultarRegistos;
	Menu menuExportar;
	Menu menuAcerca;
	Label lbReceitaDiaria;
	Label lbDespesaFatura;
	Label lbDespesa;
	Label lbIVA;
	Label lbTotalReceitaDiaria;
	Label lbTotalDespesaFatura;
	Label lbTotalDespesa;
	Label lbTotalIVA;
	MonthlyRecordController monthlyRecordController;

	public static void main(String[] args) {
		Setting.setPresistenceData();
		Setting.createMainDirectory();
		Setting.createBackupDirectory();
		Setting.createRecordFile();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		initUI(primaryStage);
	}
	
	private void initUI(Stage stage) {
		monthlyRecordController = new MonthlyRecordController();
		this.monthlyRecordController.registerObserver(this);

		root = new BorderPane();
		gridPaneNode = new GridPane();
		gridPaneNode.setAlignment(Pos.TOP_CENTER);
		//root.setGridLinesVisible(true);
		gridPaneNode.setHgap(8);
		gridPaneNode.setVgap(8);
		gridPaneNode.setPadding(new Insets(5));
		
		/**create controls**/
		menuBar = new MenuBar();
		menuConsultarRegistos = new Menu("Consultar Registos");
		menuExportar = new Menu("Exportar");
		menuAcerca = new Menu("Acerca");
		
		MenuItem menuItemExportarPDF = new MenuItem("PDF");
		MenuItem menuItemConsultarTodos = new MenuItem("Todos registos");
		MenuItem menuItemConsultarPorDatas = new MenuItem("Por datas");
		
		menuConsultarRegistos.getItems().add(menuItemConsultarTodos);
		menuConsultarRegistos.getItems().add(menuItemConsultarPorDatas);
		menuExportar.getItems().add(menuItemExportarPDF);
		
		menuBar.getMenus().add(menuConsultarRegistos);
		menuBar.getMenus().add(menuExportar);
		menuBar.getMenus().add(menuAcerca);
		
		lbReceitaDiaria = new Label("Receita Diária");
		lbDespesaFatura = new Label("Despesa/Fatura");
		lbDespesa = new Label("Despesa");
		lbIVA = new Label("IVA");
		lbTotalReceitaDiaria = new Label("Total:-");
		lbTotalDespesaFatura = new Label("Total:-");
		lbTotalDespesa = new Label("Total:-");
		lbTotalIVA = new Label("Total:-");
		
		TextField txtfReceitaDiaria = new TextField();
		TextField txtfDespesaFatura = new TextField();
		TextField txtfDespesa = new TextField();
		TextField txtfIVA = new TextField();
		
		Button btnRegist = new Button("Registar");
		Button btnSave = new Button("Guarda Registo");
		
		/**set action of controls**/
		menuItemConsultarTodos.setOnAction(e -> {
			Stage newStage = new Stage();
			new RegestryView().initUI(stage,newStage,false);
			stage.hide();
		});

		btnRegist.setOnAction(e -> {
			//TODO:add record already exists message, ask to edit last record registered?
			double receitaDiaria = Double.parseDouble(txtfReceitaDiaria.getText());
			double despesaFatura = Double.parseDouble(txtfDespesaFatura.getText());
			double despesa = Double.parseDouble(txtfDespesa.getText());
			double IVA = Double.parseDouble(txtfIVA.getText());
			boolean result = false;
			boolean newFlag = false;
			boolean editFlag = false;
			Alert alert = null;

			if(this.monthlyRecordController.doesRecordExist()){
				System.out.println("I already exist");

				alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Aviso");
				alert.setHeaderText("Registo já existe!");
				alert.setContentText("Deseja modificar o registo?");

				ButtonType btnYes = new ButtonType("Sim");
				ButtonType btnNo = new ButtonType("Não");

				alert.getButtonTypes().setAll(btnYes, btnNo);

				Optional<ButtonType> alertResult =  alert.showAndWait();

				if(alertResult.get() == btnYes){
					editFlag = true;
					result = this.monthlyRecordController.editRecord(receitaDiaria, despesaFatura, despesa, IVA);
				}
			}
			else{
				System.out.printf("I am new here");
				newFlag = true;
				result = this.monthlyRecordController.addNewRecord(receitaDiaria, despesaFatura, despesa, IVA);
			}

			if((result && editFlag) || (result && newFlag)){
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


		stage.setOnCloseRequest(event -> {
			System.out.println("Saved");
			boolean result = this.monthlyRecordController.saveRecord();
			//TODO: fix message when record already exists
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

		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment((Pos.CENTER_RIGHT));
		buttonBox.setPadding(new Insets(5));
		buttonBox.getChildren().addAll(btnRegist, btnSave);
		
		gridPaneNode.add(inputBox, 0, 0);
		gridPaneNode.add(buttonBox, 0, 3);
		
		root.setTop(menuBar);
		root.setCenter(gridPaneNode);
		
		Scene scene = new Scene(root, 540, 190);
		
		stage.setTitle("Pastel Registo Diário");
		stage.setScene(scene);
		stage.show();
		
	}

	@Override
	public void update() {
		System.out.println("hello i'm updated!!!");
		this.lbTotalReceitaDiaria.setText("Total: " + this.monthlyRecordController.retreiveReceitaDiariaTotalValue());
		this.lbTotalDespesaFatura.setText("Total: " + this.monthlyRecordController.retreiveDespesaFaturaTotalValue());
		this.lbTotalDespesa.setText("Total: " + this.monthlyRecordController.retreiveDespesaTotalValue());
		this.lbTotalIVA.setText("Total: " +  this.monthlyRecordController.retreiveIVATotalValue());
	}
}
