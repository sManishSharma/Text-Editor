package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    String filePath=null;
    @FXML
    public HTMLEditor textarea;
    //public MenuItem close;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void closeApp(){
        Platform.exit();
        System.exit(0);
    }
    public  void OpenMethod() throws Exception {
        String s=new String();
        FileChooser filechosser=new FileChooser();
        filechosser.setTitle("Open");
        //FileChooser.ExtensionFilter extfit=new FileChooser.ExtensionFilter("txt(.txt)",".txt");
        //filechosser.getExtensionFilters().addAll(extfit,new FileChooser.ExtensionFilter("doc(.doc)",".doc"),new FileChooser.ExtensionFilter("Allfile","."));
        File file=filechosser.showOpenDialog(null);
        if(file.canRead()){
            System.out.println(file+"  file name");
            System.out.println(file.getName());
            filePath=file.getPath();
            Scanner filedata=new Scanner(file);
            while(filedata.hasNext()){
                s=s+filedata.nextLine()+"\n";
                textarea.setHtmlText(s);
            }
        }
    }
    public void SaveMethod() throws FileNotFoundException {

        System.out.println(textarea.getHtmlText());
        if(filePath!=null){
            File save=new File(filePath);
            PrintWriter saveWriter=new PrintWriter(save);
            System.out.println(textarea.getHtmlText());
            saveWriter.print(textarea.getHtmlText());
            saveWriter.close();
        }
        if(filePath==null) {
            SaveAsMethod();
        }
    }
    public void NewMethod() throws Exception {
        Main new1=new Main();
        new1.start(new Stage());
    }
    public void SaveAsMethod() throws FileNotFoundException {
        FileChooser filechosser=new FileChooser();
        filechosser.setTitle("Save As");
        FileChooser.ExtensionFilter extfit=new FileChooser.ExtensionFilter("txtfiles(*.txt)","*.txt");
        filechosser.getExtensionFilters().addAll(extfit,new FileChooser.ExtensionFilter("doc","*.doc"));
        File file=filechosser.showSaveDialog(null);
        filePath=file.getPath();
        SaveMethod();
       // fileName.setText(file.getName());


    }
}

