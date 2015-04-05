package viralflyer.henrygarant.com.viralflyer;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Reader extends ActionBarActivity{

    private TextView readerText;
    private TextView statusText;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mIntentFilters;
    private String[][] mNFCTechLists;
    private Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader);

        readerText = (TextView)findViewById(R.id.readerText);
        statusText = (TextView)findViewById(R.id.statusText_Reader);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

            mNFCTechLists = new String[][]{new String[]{NfcF.class.getName()}};
            mPendingIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            // set an intent filter for all MIME data
            IntentFilter ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            try {
                ndefIntent.addDataType("*/*");
                mIntentFilters = new IntentFilter[]{ndefIntent};
            } catch (Exception e) {
                Log.e("TagDispatch", e.toString());
            }
        if(mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            statusText.setText("Status: Ready");
        }else{
            Toast.makeText(this, "Please Enable NFC",
                    Toast.LENGTH_SHORT).show();
            statusText.setText("Status: Ready");
            Intent intentNFCSettings = new Intent(Settings.ACTION_NFC_SETTINGS);
            intentNFCSettings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivityForResult(intentNFCSettings, 0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mNFCTechLists);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        statusText.setText("Status: Reading Tag");
        tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        String s = "";

        // parse through all NDEF messages and their records and pick text type only
        Parcelable[] data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (data != null) {
            try {
                for (int i = 0; i < data.length; i++) {
                    NdefRecord[] recs = ((NdefMessage) data[i]).getRecords();
                    for (int j = 0; j < recs.length; j++) {
                        if (recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN &&
                                Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT)) {
                            byte[] payload = recs[j].getPayload();
                            String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
                            int langCodeLen = payload[0] & 0077;

                            s += " " + new String(payload, langCodeLen + 1, payload.length - langCodeLen - 1,
                                    textEncoding);
                        }
                    }
                }
                statusText.setText("Status: Success");
            } catch (Exception e) {
                Log.e("TagDispatch", e.toString());
                statusText.setText("Status: Failed To Read Tag");
            }
        }
        readerText.setText(s);
    }
}
