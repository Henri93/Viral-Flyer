package viralflyer.henrygarant.com.viralflyer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
        ExternalStorageManager externalStorageManager = new ExternalStorageManager();
        externalStorageManager.createVfFile("Henry", "New Event", "Come to support us.", "4/26/15");
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


