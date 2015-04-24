package viralflyer.henrygarant.com.viralflyer;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ExternalStorageManager {

    private File folderLocation;

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
        File newFile = new File(getFolderPath(), fileName + ".txt");
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
}
