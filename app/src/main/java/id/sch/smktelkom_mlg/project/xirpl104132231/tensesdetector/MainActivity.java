package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText Input;
    TextView Hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input = (EditText) findViewById(R.id.editTextTenses);
        Hasil = (TextView) findViewById(R.id.textViewHasil);

        InputStream inputStream = getResources().openRawResource(R.raw.verb0);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();

        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProses();
            }
        });
    }

    private void doProses() {
        String input = Input.getText().toString().isEmpty() ? "" : Input.getText().toString();
        String[] separated = input.split(" ");
        String hasil = "";
        int i;
        for (i = 0; i < separated.length; i++) {
            hasil += separated[i].toString() + "\n";
        }
        Hasil.setText(hasil);
    }
}
/*for (int i = 0; i < scoreList.size(); i++) {

            for (int j = 0; j < 3; j++) {
                Log.d("VERB", "onCreate: "+i+"|"+j+":"+scoreList.get(i)[j]); //split
            }


        }*/