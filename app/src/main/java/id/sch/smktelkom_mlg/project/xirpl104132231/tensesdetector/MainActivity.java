package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream = getResources().openRawResource(R.raw.verb0);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();

        for (int i = 0; i < scoreList.size(); i++) {

            for (int j = 0; j < 3; j++) {
                Log.d("VERB", "onCreate: "+i+"|"+j+":"+scoreList.get(i)[j]); //split
            }


        }
    }
}
