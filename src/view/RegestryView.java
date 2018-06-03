package view;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RegestryView {
	
	public void initUI(Stage previousStage, Stage stage, boolean searchFunctionStatus) {
		StackPane root = new StackPane();
		GridPane gridPaneNode = new GridPane();
		gridPaneNode.setAlignment(Pos.TOP_CENTER);
		gridPaneNode.setGridLinesVisible(true);
		//gridPaneNode.setHgap(1);
		//gridPaneNode.setVgap(1);
		//gridPaneNode.setPadding(new Insets(5));
		
		/**create controls**/
		TableView<String> table = new TableView<>();
		
		TableColumn<String, String> tbColReceitaDiaria = new TableColumn<>("Receita Diária");
		TableColumn<String, String> tbColDepesaFatura = new TableColumn<>("Despesa/Fatura");
		TableColumn<String, String> tbColDespesa = new TableColumn<>("Despesa");
		TableColumn<String, String> tbColIVA = new TableColumn<>("IVA");
		TableColumn<String, String> tbColData = new TableColumn<>("Data");
		
		table.getColumns().addAll(tbColReceitaDiaria,
				tbColDepesaFatura,tbColDespesa,tbColIVA,tbColData);
		
		Label lbData = null;
		final DatePicker datePicker = new DatePicker();
		
		if(searchFunctionStatus) {
			lbData = new Label("Data:");
		}
		
		/**set action of controls**/
		stage.setOnCloseRequest(e -> {
			previousStage.show();
			stage.close();
		});
		
		table.widthProperty().addListener(new ChangeListener<Number>()
	    {
	        public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
	        {
	        	tbColReceitaDiaria.setPrefWidth(newWidth.intValue() * 0.25);
	        	tbColDepesaFatura.setPrefWidth(newWidth.intValue() * 0.25);
	        	tbColDespesa.setPrefWidth(newWidth.intValue() * 0.20);
	        	tbColIVA.setPrefWidth(newWidth.intValue() * 0.15);
	        	tbColData.setPrefWidth(newWidth.intValue() * 0.15);
	        }
	    });
		
		if(searchFunctionStatus) {
			datePicker.setOnAction(e ->{
				LocalDate date = datePicker.getValue();
				System.out.println(date);
				
			});
		}
		
		/**add controls to layout managers**/
		HBox boxPesquisa = null;
		if(searchFunctionStatus) {
			boxPesquisa = new HBox();
			boxPesquisa.getChildren().addAll();
		}
		
		//gridPaneNode.add(table, 0, 0);
		if(searchFunctionStatus) {
			root.getChildren().add(boxPesquisa);
		}
		
		root.getChildren().add(table);
		
		Scene scene = new Scene(root, 950, 600);
		stage.setTitle("Consultar Registos");
		stage.setScene(scene);
		stage.show();
	}
}
