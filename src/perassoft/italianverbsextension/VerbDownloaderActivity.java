package perassoft.italianverbsextension;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class VerbDownloaderActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.buttonGo).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonGo )
		{
			Editable name = ((EditText)findViewById(R.id.editTextVerb)).getText();
			Bundle data = new Bundle();
			data.putString("VERB", name.toString());
			Intent intent = new Intent();
		    intent.putExtras(data);
		    setResult(RESULT_OK, intent);
			finish(); 
		}
		
	}

	

}
