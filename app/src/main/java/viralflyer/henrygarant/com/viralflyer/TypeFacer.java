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

    public void setViewFont(Button v) {
        v.setTypeface(font);
    }

    public void setViewFont(TextView v) {
        v.setTypeface(font);
    }

    public Typeface getFont() {
        return font;
    }

    public void setFont(String font, Context c) {
        this.font = Typeface.createFromAsset(c.getAssets(), font);
    }
}
