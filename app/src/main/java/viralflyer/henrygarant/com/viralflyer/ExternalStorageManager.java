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
        File newFile = new File(getFolderPath(), fileName + ".txt");
        try {
            FileOutputStream f = new FileOutputStream(newFile);
            OutputStreamWriter osw = new OutputStreamWriter(f);
            osw.append("Event Name: " + title);
            osw.append("\r\n");
            osw.append("Event Details: " + message);
            osw.append("\r\n");
            osw.append("Event Date: " + date);
            osw.append("\r\n");
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
            osw.append("Event Name: " + title);
            osw.append("\r\n");
            osw.append("Event Date" + date);
            osw.append("\r\n");
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