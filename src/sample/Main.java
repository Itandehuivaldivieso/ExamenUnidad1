package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        int resp;

        //panel StackPane para el titulo y sus caracteristicas
        StackPane flow = new StackPane();
        flow.setPadding(new Insets(5, 1, 5, 1));
        flow.setStyle("-fx-background-color: #98a509;");

        //Texto del encabezado y caracteristicas
        Text titulo = new Text("Calculo de la distancia, velocidad y tiempo");
        titulo.setFont(Font.font("Century Gothic", FontWeight.BLACK, 20));
        flow.getChildren().addAll(titulo);
        root.setTop(flow);
        root.setCenter(cuerpo(0));

        //Vbox para los radioButtons
        VBox izq = new VBox();
        izq.setStyle("-fx-background-color: #9a9d89");
        izq.setPadding(new Insets(5, 0, 5, 0));

        //Boton distancia
        ToggleGroup opciones = new ToggleGroup();
        RadioButton distancia = new RadioButton("Distancia");
        distancia.setToggleGroup(opciones);
        distancia.setPadding(new Insets(2,0,2,0));
        distancia.setStyle("-fx-font-family: 'Century Gothic'");
        distancia.setStyle("-fx-font-size: 12");

        //Boton velocidad
        RadioButton velocidad = new RadioButton("Velocidad");
        velocidad.setPadding(new Insets(2,0,2,0));
        velocidad.setStyle("-fx-font-family: 'Century Gothic'");
        velocidad.setStyle("-fx-font-size: 12");
        velocidad.setToggleGroup(opciones);

        //Boton tiempo
        RadioButton tiempo    = new RadioButton("Tiempo");
        tiempo.setPadding(new Insets(2,0,2,0));
        tiempo.setStyle("-fx-font-family: 'Century Gothic'");
        tiempo.setStyle("-fx-font-size: 12");
        tiempo.setToggleGroup(opciones);

        //Evento listener para saber que radioButton se seleccionó
        opciones.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)opciones.getSelectedToggle();
                if (rb != null) {
                    String s = rb.getText();
                    if (s == "Distancia")
                        root.setCenter(cuerpo(1));
                    else if (s== "Tiempo")
                        root.setCenter(cuerpo(2));
                    else if (s == "Velocidad")
                        root.setCenter(cuerpo(3));
                    // change the label
                        System.out.println("SELECT" +   s);
                    //l2.setText(s + " selected");
                }
            }
        });

        //Agregación
        izq.getChildren().addAll(distancia,velocidad,tiempo);
        root.setRight(preguntas());
        root.setLeft(izq);

        primaryStage.setTitle("Examen unidad 1");
        primaryStage.setScene(new Scene(root, 500, 300));

        primaryStage.show();
    }

    /* Metodo para que se vean los datos del creador y las formulas
     *
     */
    public VBox preguntas(){

        VBox dere = new VBox();
        dere.setStyle("-fx-background-color: #9a9d89;");
        dere.setPadding(new Insets(10));
        dere.setSpacing(8);

        //Boton descripcion
        Button descripcion = new Button("Datos creador");
        descripcion.setStyle("-fx-font-family: 'Century Gothic'");


        descripcion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información necesaria");
                alert.setHeaderText("Datos del creador");
                alert.setContentText("Nombre: Itandehui Valdivieso Ortiz " +"\n" + "Topicos avanzados de programación");

                alert.showAndWait();
            }
        });

        Button formulas = new Button("Formulas");
        formulas.setStyle("-fx-font-family: 'Century Gothic'");
        formulas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Formulario");
                alert.setHeaderText("Formulas tomadas");
                alert.setContentText("Velocidad = distancia / tiempo " +"\n" + "Distancia = velocidad * tiempo" +"\n"+ "Tiempo = distancia / velocidad");

                alert.showAndWait();
            }
        });
        dere.getChildren().addAll(descripcion,formulas);

        return dere;
    }
    /*
     * En este metodo se crean los textField y se hace el calculo de lo que se quiera calcular.
     */
    public GridPane cuerpo(int respp){

        GridPane contenedorCalculador = new GridPane();
        contenedorCalculador.setStyle("-fx-font-family: 'Century Gothic'");
        contenedorCalculador.setStyle("-fx-background-color: #9a9d89;");
        contenedorCalculador.setHgap(10);
        contenedorCalculador.setVgap(10);
        contenedorCalculador.setPadding(new Insets(0, 11, 0, 11));

        Text distanciaTe = new Text("Distancia");
        contenedorCalculador.add(distanciaTe,0,1);
        TextField distancia = new TextField();
        contenedorCalculador.add(distancia,1,1);

        Text velocidadTe = new Text("Velocidad");
        contenedorCalculador.add(velocidadTe,0,3);
        TextField velocidad = new TextField();
        contenedorCalculador.add(velocidad,1,3);

        Text tiempoTe = new Text("Tiempo");
        contenedorCalculador.add(tiempoTe,0,5);
        TextField tiempo = new TextField();
        contenedorCalculador.add(tiempo,1,5);

        Button enviar = new Button("Enviar");
        contenedorCalculador.add(enviar,0, 8);

        Text respuesta = new Text();
        contenedorCalculador.add(respuesta, 1,7);
        Text cc = new Text("Respuesta");
        contenedorCalculador.add(cc,0,7);


        enviar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int resultado;
                try {
                    if (respp==1 ){
                        resultado = Integer.parseInt(velocidad.getText()) * Integer.parseInt(tiempo.getText());
                        respuesta.setText(Integer.toString(resultado));
                    }
                    if(respp ==2){
                        resultado = Integer.parseInt(distancia.getText()) / Integer.parseInt(velocidad.getText());
                        respuesta.setText(Integer.toString(resultado));
                    }
                    if(respp == 3 ){
                        resultado = Integer.parseInt(distancia.getText()) / Integer.parseInt(tiempo.getText());
                        respuesta.setText(Integer.toString(resultado));
                    }
                }catch (Exception e){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Los contenedores están vacios");
                    alert.showAndWait();
                }

            }
        });
        if(respp ==1)
            distancia.setDisable(true);
        if(respp==2)
            tiempo.setDisable(true);
        if (respp ==3)
            velocidad.setDisable(true);
        if(respp ==0) {
            distancia.setDisable(true);
            tiempo.setDisable(true);
            velocidad.setDisable(true);
            enviar.setDisable(true);
        }
        return contenedorCalculador;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
