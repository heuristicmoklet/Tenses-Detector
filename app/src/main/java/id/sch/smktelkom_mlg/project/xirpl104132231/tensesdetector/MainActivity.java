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



        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProses();
            }
        });
    }

    private void doProses() {
        InputStream inputStream = getResources().openRawResource(R.raw.verb0);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();
        String input = Input.getText().toString().isEmpty() ? "" : Input.getText().toString();
        String[] separated = input.split(" ");

        String tobe1[][] = {{"is", "was", "have", "be"}, {"am", "were", "has", "been"}, {"are", "were", "had"}};
        String s[] = {"i", "you", "they", "we", "he", "she"};
//        String hasil = "";
        int i, j, k;
        for (i = 0; i < separated.length; i++) {
//            hasil += separated[i].toString() + "\n";
            if (separated[i].endsWith("ed")) {
                Hasil.setText("Simple Past Tense");
            } else if (separated[i].equals("will")) {
                Hasil.setText("Simple Future Tense");
            }  else if(separated[i].equals("will") && separated[i+1].equals("be") && separated[i+2].endsWith("ing")){
                Hasil.setText("Future Continuous Tense");
            }

            for (j = 0; j < scoreList.size(); j++) {
                if (separated[i].equals(scoreList.get(j)[1])) {
                    Hasil.setText("Simple Past Tense");
                }
            }

            for (k = 0; k<tobe1.length;k++){
                if (separated[i].equals(tobe1[k][0]) && separated[i+1].endsWith("ing")){
                    Hasil.setText("Present Continuous Tense");
                } else if (separated[i].equals(tobe1[k][1]) && separated[i+1].endsWith("ing")){
                    Hasil.setText("Past Continuous Tense");
                }  else if(separated[i].equals("will") && separated[i+1].equals("be") && separated[i+2].endsWith("ing")){
                    Hasil.setText("Future Continuous Tense");
                } else if(separated[i].equals(tobe1[k][2])){ //+sakjane v3
                    Hasil.setText("Present Perfect Tense");
                }
            }

        }
    }
}
/*for (int i = 0; i < scoreList.size(); i++) {


                else if(separated[i].equals("will") && separated[i+1].equals("be") && separated[i+2].endsWith("ing")){
                Hasil.setText("Future Continuous Tense");
            }
            for (int j = 0; j < 3; j++) {
                Log.d("VERB", "onCreate: "+i+"|"+j+":"+scoreList.get(i)[j]); //split
            }


        }




        */