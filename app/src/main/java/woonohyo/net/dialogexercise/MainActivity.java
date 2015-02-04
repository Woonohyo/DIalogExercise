package woonohyo.net.dialogexercise;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements DialogInterface.OnClickListener {
    Button touchButton;
    TextView textViewName;
    TextView textViewAge;
    EditText editTextName;
    EditText editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        touchButton = (Button) findViewById(R.id.button_touch_me);
        touchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        textViewName = (TextView) findViewById(R.id.textView_name);
        textViewAge = (TextView) findViewById(R.id.textView_age);
    }

    private ProgressDialog dlg;

    public class LoadThread extends Thread {
        int progress = 0;

        @Override
        public void run() {
            while (progress < 101) {
                dlg.setProgress(progress);
                ++progress;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    void showDialog() {

        dlg = new ProgressDialog(this);
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dlg.setMessage("Fucking...");
        dlg.show();

        LoadThread thread = new LoadThread();
        thread.start();

        /*
        LinearLayout lay = (LinearLayout) View.inflate(this, R.layout.laydlg, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        editTextAge = (EditText) lay.findViewById(R.id.editText_age);
        editTextName = (EditText) lay.findViewById(R.id.editText_name);
        dlg.setTitle("THIS IS TITLE!");
        dlg.setView(lay);
        dlg.setIcon(R.drawable.ic_launcher);
        dlg.setPositiveButton("Input!", this);
        dlg.setNegativeButton("Cancel!", this);
        dlg.show();
        */
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(this, which + " Clicked", Toast.LENGTH_SHORT).show();
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE: {
                textViewAge.setText(editTextAge.getText().toString());
                textViewName.setText(editTextName.getText().toString());
            }
            case DialogInterface.BUTTON_NEGATIVE: {
                editTextAge.setText("");
                editTextName.setText("");
            }
        }
    }
}
