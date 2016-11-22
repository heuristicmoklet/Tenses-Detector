package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText Input;
    TextView Hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input = (EditText) findViewById(R.id.editTextTenses);
        Hasil = (TextView) findViewById(R.id.textViewHasil);

        Intent intent = getIntent();
        String scannedText = intent.getStringExtra("text");
        if (scannedText != "") {
            Input.setText(scannedText);
        }
        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProses();
            }
        });

        findViewById(R.id.imageViewKamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturetext();
            }
        });
    }

    private void capturetext() {
        Intent i = new Intent(getApplicationContext(), OcrCaptureActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView iv = (ImageView) findViewById(R.id.imageViewKamera);
            iv.setImageBitmap(bitmap);
        }
    }

    private void doProses() {
        InputStream inputStream = getResources().openRawResource(R.raw.verb0);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();
        String input = Input.getText().toString().isEmpty() ? "" : Input.getText().toString();
        String[] separated = input.split(" ");

        String tobe1[][] = {{"is", "was", "have", "be","will"}, {"am", "were", "has", "been"}, {"are", "were", "had"}};
        String s[] = {"i", "you", "they", "we", "he", "she"};
//        String hasil = "";
        int i, j, k;
        for (i = 0; i < separated.length; i++) {
//            hasil += separated[i].toString() + "\n";
            if (separated[i].equals(tobe1[0][4])) {
                Hasil.setText("Simple Future Tense");
            }  else if(separated[i].equals(tobe1[0][4]) && separated[i+1].equals("be") && separated[i+2].endsWith("ing")){
                Hasil.setText("Future Continuous Tense");
            }

            //use verb 2
            for (j = 0; j < scoreList.size(); j++) {
                if (separated[i].equals(tobe1[0][4]) && separated[i+1].equals(tobe1[0][2]) && separated[i+2].equals(scoreList.get(j)[2])){
                    Hasil.setText("Future Perfect Tense");
                } else if (separated[i].equals(tobe1[2][2]) && separated[i+1].equals(scoreList.get(j)[2])||separated[i].endsWith("ed")){
                    Hasil.setText("Past Perfect Tense");
              } else if (separated[i].equals(scoreList.get(j)[1])||separated[i].endsWith("ed")) {
                    Hasil.setText("Simple Past Tense");
                }
//                if(separated[i].equals(tobe1[0][2]) && separated[i+1].equals(scoreList.get(j)[2]) || separated[i].equals(tobe1[1][2]) && separated[i+1].equals(scoreList.get(j)[2])){ //+sakjane v3
//                    Hasil.setText("Present Perfect Tense");
//                }
            }

            for (k = 0; k<tobe1.length;k++){
                if (separated[i].equals(tobe1[k][0]) && separated[i+1].endsWith("ing")){
                    Hasil.setText("Present Continuous Tense");
                } else if (separated[i].equals(tobe1[k][1]) && separated[i+1].endsWith("ing")){
                    Hasil.setText("Past Continuous Tense");
                }  else if(separated[i].equals(tobe1[0][4]) && separated[i+1].equals("be") && separated[i+2].endsWith("ing")){
                    Hasil.setText("Future Continuous Tense");
                }  else if (separated[i].equals(tobe1[0][2])  && separated[i+1].equals(tobe1[1][3]) && separated[i+2].endsWith("ing") ||
                        separated[i].equals(tobe1[1][2]) && separated[i+1].equals(tobe1[1][3]) && separated[i+2].endsWith("ing")){
                    Hasil.setText("Present Perfect Continuous Tense");
                }  else if (separated[i].equals(tobe1[2][2]) && separated[i+1].endsWith("ed")){
                    Hasil.setText("Past Perfect Tense");
                }  else if (separated[i].equals(tobe1[2][2]) && separated[i+1].equals(tobe1[1][3]) && separated[i+2].endsWith("ing")){
                    Hasil.setText("Past Perfect Continuous Tense");
                } else if (separated[i].equals(tobe1[0][4]) && separated[i+1].equals(tobe1[0][3]) && separated[i+2].equals(tobe1[1][3]) && separated[i+3].endsWith("ing")){
                    Hasil.setText("Future Perfect Continuous Tense");
                }
            }

        }
    }
}
/*for (int i = 0; i < scoreList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                Log.d("VERB", "onCreate: "+i+"|"+j+":"+scoreList.get(i)[j]); //split
            }
        }
        */