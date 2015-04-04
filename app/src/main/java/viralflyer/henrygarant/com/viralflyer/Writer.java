package viralflyer.henrygarant.com.viralflyer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

public class Writer extends ActionBarActivity {

    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mIntentFilters;
    private String[][] mNFCTechLists;
    private Tag tag;
    private Button messageButton;
    private EditText messageText;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writer);

        messageButton = (Button) findViewById(R.id.pushButton);
        messageText = (EditText) findViewById(R.id.messageText);
        statusText = (TextView) findViewById(R.id.statusText);

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
        tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
    }

    public void PushMessage(View v) {
        NfcManager manager = (NfcManager) this.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            try {
                // create an NDEF message with record of plain text type
                NdefMessage mNdefMessage = new NdefMessage(
                        new NdefRecord[]{
                                createNewTextRecord(messageText.getText().toString(), Locale.ENGLISH, true)});
                Ndef ndef = Ndef.get(tag);
                ndef.connect();
                ndef.writeNdefMessage(mNdefMessage);
                ndef.close();
                statusText.setText("Status: Success");
            } catch (IOException e) {
                e.printStackTrace();
                statusText.setText("Status: Failed Connection");
            } catch (FormatException e) {
                e.printStackTrace();
                statusText.setText("Status: Failed To Format");
            } catch (NullPointerException e) {
                e.printStackTrace();
                statusText.setText("Status: Failed No Receiver");
            }
        } else {
            statusText.setText("Status: Enable NFC");
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        }
    }

    public static NdefRecord createNewTextRecord(String text, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);
        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char) (utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }
}
