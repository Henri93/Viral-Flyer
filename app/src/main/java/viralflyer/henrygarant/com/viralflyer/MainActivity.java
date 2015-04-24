package viralflyer.henrygarant.com.viralflyer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button write = (Button)findViewById(R.id.writeButton);
        Button read = (Button)findViewById(R.id.readButton);
        Button help = (Button)findViewById(R.id.helpButton);
        TextView mainCoverText = (TextView)findViewById(R.id.mainCoverText);
        TypeFacer typeFacer = new TypeFacer("encode_thin.ttf", this);
        typeFacer.setViewFont(write);
        typeFacer.setViewFont(read);
        typeFacer.setViewFont(help);
        typeFacer.setViewFont(mainCoverText);

        //Testing creating files
        String folder_main = "ViralFlyer";
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), folder_main);
        if (!file.mkdirs()) {
            Log.e("EXTERNAL FILES:", "Directory not created");
        }

        File textFile = new File( file.getPath(), "ViralFlyer.txt");
        try {
            FileOutputStream f = new FileOutputStream(textFile);
            PrintWriter pw = new PrintWriter(f);
            pw.println("This is a file");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("FILE: ", "******* File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(View v){
        //go to read activity
        Intent intent = new Intent(this, Reader.class);
        startActivity(intent);
    }

    public void write(View v){
        //go to write activity
        Intent intent = new Intent(this, Writer.class);
        startActivity(intent);
    }

    private String canWriteEx () {

        String state = Environment.getExternalStorageState();

        if (state.equals (Environment.MEDIA_MOUNTED)) {
            Log.i("FILES:", "Can write to external directory: "
                    + this.getExternalFilesDir(null).getAbsolutePath());
            return "true";
        } else {
            Log.i ("FILES:", "Cannot write to external directory: "
                    + this.getExternalFilesDir (null).getAbsolutePath ());
            return "false";
        }
    }
}


