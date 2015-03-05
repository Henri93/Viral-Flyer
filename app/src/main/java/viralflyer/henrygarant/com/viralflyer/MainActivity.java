package viralflyer.henrygarant.com.viralflyer;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if the user has NFC
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //Inform the user of their NFC situation
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC enabled", Toast.LENGTH_SHORT).show();
        } else if (nfcAdapter != null) {
            Toast.makeText(this, "Please enable NFC", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "NFC failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        /*
        For testing purposes
        TODO remove
        */
        enableForegroundDispatch();
        super.onResume();
    }

    @Override
    protected void onPause() {
        /*
        For testing purposes
        TODO remove
        */
        disableForegroundDispatch();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.hasExtra(nfcAdapter.EXTRA_TAG)) {
            Toast.makeText(this, "NFC intent received", Toast.LENGTH_SHORT).show();
            Tag tag = intent.getParcelableExtra(nfcAdapter.EXTRA_TAG);
            //NdefMessage ndefMessage = createNdefMessage("My NFC message");
            //writeNdefMessage(tag, ndefMessage);
        }
        super.onNewIntent(intent);
    }

    public void enableForegroundDispatch() {
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilters = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    public void disableForegroundDispatch() {
        nfcAdapter.disableForegroundDispatch(this);
    }

    public void formatTag(Tag tag, NdefMessage ndefMessage) {
        try {
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);
            if (ndefFormatable == null) {
                Toast.makeText(this, "Tag is not formatable", Toast.LENGTH_SHORT).show();
                return;
            }
            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();
            Toast.makeText(this, "Tag written", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("FormatTag", e.getMessage());
        }
    }

    public void writeNdefMessage(Tag tag, NdefMessage ndefMessage) {
        try {
            if (tag == null) {
                return;
            }

            Ndef ndef = Ndef.get(tag);

            if (ndef == null) {
                //Tag needs to be formatted
                formatTag(tag, ndefMessage);
            } else {
                ndef.connect();

                if (!ndef.isWritable()) {
                    //tag is not writable
                    ndef.close();
                    return;
                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
            }
        } catch (Exception e) {
            Log.e("WriteTag", e.getMessage());
        }
    }
}
