package viralflyer.henrygarant.com.viralflyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


