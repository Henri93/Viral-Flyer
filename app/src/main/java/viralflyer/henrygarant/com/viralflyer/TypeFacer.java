package viralflyer.henrygarant.com.viralflyer;


import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;


public class TypeFacer{

    private Typeface font;

    public TypeFacer(String filename , Context c){
        font = Typeface.createFromAsset(c.getAssets(), filename);
    }

    public void setFont(Button v) {
        v.setTypeface(font);
    }

    public void setFont(TextView v) {
        v.setTypeface(font);
    }
}
