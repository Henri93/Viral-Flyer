package viralflyer.henrygarant.com.viralflyer;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ExternalStorageManager {

    private File folderLocation;
    private File newFile;
    private String htmlBasics1 = "<!DOCTYPE html>\r\n" +
            "<html>\r\n" +
            " <head>\r\n" +
            "  <meta charset=\"UTF-8\">\r\n" +
            "  <title></title>\r\n" +
            " </head>\r\n" +
            " <body>\r\n";
    private String htmlBasics2 = "  \r\n" +
            " </body>\r\n" +
            "</html>";

    public ExternalStorageManager(){
        folderLocation = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "ViralFlyer");
        if(!chechFolderExists(folderLocation)){
            folderLocation.mkdir();
        }

    }

    public boolean chechFolderExists(File folderLocation){
        if(folderLocation.exists()){
            return true;
        }else{
            return false;
        }
    }

    public String getFolderPath(){
        return folderLocation.getPath();
    }

    public void createFile(String fileName, String data){
        newFile = new File(getFolderPath(), fileName + ".txt");
        try {
            FileOutputStream f = new FileOutputStream(newFile);
            PrintWriter pw = new PrintWriter(f);
            pw.write(data);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createVfFile(String fileName, String title, String message, String date){
        File newFile = new File(getFolderPath(), fileName + ".html");
        try {
            FileOutputStream f = new FileOutputStream(newFile);
            OutputStreamWriter osw = new OutputStreamWriter(f);
            osw.append(htmlBasics1);
            osw.append("<h1>" + title + "</h1>");
            osw.append("\r\n");
            osw.append("<p>" + message + "</p>");
            osw.append("\r\n");
            osw.append("<p>" + date + "</p>");
            osw.append("\r\n");
            osw.append(htmlBasics2);
            osw.flush();
            osw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createVfFile(String fileName, String title, String date){
        File newFile = new File(getFolderPath(), fileName + ".xml");
        try {
            FileOutputStream f = new FileOutputStream(newFile);
            OutputStreamWriter osw = new OutputStreamWriter(f);
            osw.append(htmlBasics1);
            osw.append("<h1>" + title + "</h1>");
            osw.append("\r\n");
            osw.append("<p>" + date + "</p>");
            osw.append("\r\n");
            osw.append(htmlBasics2);
            osw.flush();
            osw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
