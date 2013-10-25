package perassoft.italianverbsextension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;

public class VerbDownloaderActivity extends Activity implements OnClickListener {

	private AutoCompleteTextView mEditVerb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verb_downloader);
		setTitle(R.string.download_new_verb);
	
		findViewById(R.id.buttonGo).setOnClickListener(this);
		findViewById(R.id.buttonCancel).setOnClickListener(this);
		mEditVerb = ((AutoCompleteTextView) findViewById(R.id.editTextVerb));
		VerbsAutoCompleteAdapter adapter = new VerbsAutoCompleteAdapter(this,
				android.R.layout.simple_dropdown_item_1line);

		mEditVerb.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonCancel) {
			Intent intent = new Intent();
			setResult(RESULT_CANCELED, intent);
			finish();
		}
		else if (v.getId() == R.id.buttonGo) {
			Editable name = mEditVerb.getText();
			Bundle data = new Bundle();
			CharSequence[] lines = new CharSequence[96];
			int i = 0;
			boolean error = false;
			try {
				byte[] p = name.toString().getBytes("UTF-8");
				String base64 = Base64.encodeToString(p, Base64.DEFAULT);
	    		URL url = new URL("http://www.ecommuters.com/verbs/get/" + base64);
				InputStream openStream = url.openStream();
				BufferedReader reader;
				reader = new BufferedReader(new InputStreamReader(openStream));
				String line = null;
				while ((line = reader.readLine()) != null) {
					lines[i] = line;
					i++;
				}
			} catch (Exception e) {
				data.putString("ERROR", e.getMessage());
				error = true;
			}
			data.putCharSequenceArray("VERB", lines);
			Intent intent = new Intent();
			intent.putExtras(data);
			setResult(error ? RESULT_CANCELED : RESULT_OK, intent);
			finish();
		}

	}

}
