package viralflyer.henrygarant.com.viralflyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;


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
        String filename = "ViralFlyer";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
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
}


