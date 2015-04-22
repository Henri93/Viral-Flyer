package viralflyer.henrygarant.com.viralflyer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        TypeFacer typeFacer = new TypeFacer("encode_thin.ttf", this);
        typeFacer.setFont(write);
        typeFacer.setFont(read);
        typeFacer.setFont(help);

        //Testing creating files
        // Find the root of the external storage.
        // See http://developer.android.com/guide/topics/data/data-  storage.html#filesExternal

        File root = android.os.Environment.getExternalStorageDirectory();
        help.setText(root.getAbsolutePath().toString());
        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File (root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "myData.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println("Hi , How are you");
            pw.println("Hello");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("File", "******* File not found. Did you" +
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


