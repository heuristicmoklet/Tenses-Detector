package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;


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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Tips to use~");
                builder.setMessage("It is better if you use PRONOUNS rather than using names and objects");
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doProses();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

//        Pattern SimplePresent = Pattern.compile("(i|they|we|you) do");
//        Pattern SimplePresent2 = Pattern.compile("(she|he|it) does");
        Pattern PresentCont = Pattern.compile("i am .+ing .+");
        Pattern PresentCont2 = Pattern.compile("(you|they|we) are .+ing .+");
        Pattern PresentCont3 = Pattern.compile("(he|she|it) is .+ing .+");
        Pattern PresentPerfectC = Pattern.compile("(i|you|they|we) have been .+ing .+");
        Pattern PresentPerfectC2 = Pattern.compile("(he|she|it) has been .+ing .+");

        //PAST TENSES
        Pattern SimplePast2 = Pattern.compile("(i|you|they|we|he|she|it) .+ed .+");
        Pattern PastC = Pattern.compile("(i|he|she|it) was .+ing .+");
        Pattern PastC2 = Pattern.compile("(you|they|we) were .+ing .+");
        Pattern PastPerfectC = Pattern.compile("(i|you|they|we|he|she|it) had been .+ing .+");

        //FUTURE TENSES
        Pattern SimpleFuture = Pattern.compile("(i|you|they|we|he|she|it) (will|shall|must|can|may) .+");
        Pattern FutureC = Pattern.compile("(i|you|they|we|he|she|it) (will|shall|must|can|may) be .+ing .+");
        Pattern FuturePrefectC = Pattern.compile("(i|you|they|we|he|she|it) (will|shall|must|can|may) have been .+ing .+");

        //PAST FUTURE tenses
        Pattern PastFuture = Pattern.compile("(i|you|they|we|he|she|it) (would|should|might|could|must) .+");
        Pattern PastFutureC = Pattern.compile("(i|you|they|we|he|she|it) (would|should|might|could|must) be .+ing .+");
        Pattern PastFuturePerfect = Pattern.compile("(i|you|they|we|he|she|it) (would|should|might|could|must) have been .+"); //+verb3
        Pattern PastFuturePerfectC = Pattern.compile("(i|you|they|we|he|she|it) (would|should|might|could|must) have been .+ing .+");

        Pattern PresentPerfect = Pattern.compile("(i|you|they|we) (have)"); //+verb3
        Pattern PresentPerfect2 = Pattern.compile("(he|she|it) (has)"); //verb3

        Pattern p = Pattern.compile("(i|you|they|we|he|she|it)");

        String subjek1[] = {"i", "you", "they", "we"};
        String subjek2[] = {"he","she","it"};
        String modal1[] = {"will","shall","must","can","may"};
        String modal2[] = {"would","should","might","could","might"};


        if (PresentCont.matcher(input).matches()){
            Hasil.setText("This is Present Continuous Tense");
        } else if (PresentCont2.matcher(input).matches()){
            Hasil.setText("This is Present Continuous Tense");
        } else if (PresentCont3.matcher(input).matches()){
            Hasil.setText("This is Present Continuous Tense");
        } else if (PresentPerfectC.matcher(input).matches()){
            Hasil.setText("This is Present Perfect Continuous Tense");
        } else if (PresentPerfectC2.matcher(input).matches()){
            Hasil.setText("This is Present Perfect Continuous Tense");
        } else if (SimplePast2.matcher(input).matches()){
            Hasil.setText("This is Simple Past Tense");
        } else if (PastC.matcher(input).matches()){
            Hasil.setText("This is Past Continuous Tense");
        } else  if (PastC2.matcher(input).matches()){
            Hasil.setText("This is Past Continuous Tense");
        } else if (PastPerfectC.matcher(input).matches()){
            Hasil.setText("This is Past Perfect Continuous Tense");
        } else if (FuturePrefectC.matcher(input).matches()){
            Hasil.setText("This is Future Perfect Continuous Tense");
        } else if (FutureC.matcher(input).matches()){
            Hasil.setText("This is Future Continuous Tense");
        } else if (PastFuturePerfectC.matcher(input).matches()){
            Hasil.setText("This is Past Future Perfect Continuous Tense");
        } else if (PastFuturePerfect.matcher(input).matches()){
            Hasil.setText("This is Past Future Rerfect Tense");
        } else if (PastFutureC.matcher(input).matches()){
            Hasil.setText("This is Past Future Continuous Tense");
        } else if(PastFuture.matcher(input).matches()){
            Hasil.setText("This is Simple Past Future Tense");
        } else {
            Hasil.setText("Incorrect Input");
            for (int i = 0; i < separated.length; i++){
                for (int j=0; j<scoreList.size(); j++){

                    for (int k=0; k<subjek1.length;k++){

                        for (int mod1=0; mod1<modal1.length;mod1++){
                            if (separated[i].equals(subjek1[k]) && separated[i+1].equals(modal1[mod1]) && separated[i+2].equals("have") && (separated[i+3].equals(scoreList.get(j)[2]) || (separated[i+3].endsWith("ed")))){
                                Hasil.setText("This is Future Perfect Tense");
                            } else if (separated[i].equals(subjek1[k]) && separated[i+1].equals(modal1[mod1])){
                                Hasil.setText("This is Simple Future Tense");
                            }
                        }

                        if (separated[i].equals(subjek1[k]) && separated[i+1].equals("have") && separated[i+2].equals(scoreList.get(j)[2])){
                            Hasil.setText("This is Present Perfect Tense");
                        } else if (separated[i].equals(subjek1[k]) && separated[i+1].equals("have") && separated[i+2].endsWith("ed")){
                            Hasil.setText("This is Present Perfect Tense");
                        } else if (separated[i].equals(subjek1[k]) && separated[i+1].equals("had") && (separated[i+2].equals(scoreList.get(j)[2]) || separated[i+2].endsWith("ed"))){
                            Hasil.setText("This is Past Perfect Tense");
                        } else if (separated[i].equals(subjek1[k]) && (separated[i+1].equals(scoreList.get(j)[1]) || separated[i+1].endsWith("ed"))){
                            Hasil.setText("This is Simple Past Tense");
                        }
                    }
                    for (int l=0; l<subjek2.length;l++){

                        for (int mod1=0; mod1<modal1.length;mod1++){
                            if (separated[i].equals(subjek2[l]) && separated[i+1].equals(modal1[mod1]) && separated[i+2].equals("have") && (separated[i+3].equals(scoreList.get(j)[2]) || (separated[i+3].endsWith("ed")))){
                                Hasil.setText("This is Future Perfect Tense");
                            } else if (separated[i].equals(subjek2[l]) && separated[i+1].equals(modal1[mod1])){
                                Hasil.setText("This is Simple Future Tense");
                            }
                        }

                        if (separated[i].equals(subjek2[l]) && separated[i+1].equals("has") && (separated[i+2].equals(scoreList.get(j)[2])  || separated[i+2].endsWith("ed"))){
                            Hasil.setText("This is Present Perfect Tense");
                        } else if (separated[i].equals(subjek2[l]) && separated[i+1].equals("had") && (separated[i+2].equals(scoreList.get(j)[2]) || separated[i+2].endsWith("ed"))){
                            Hasil.setText("This is Past Perfect Tense");
                        } else if ((separated[i].equals(subjek2[l]) && (separated[i+1].equals(scoreList.get(j)[1]) || separated[i+1].endsWith("ed")))){
                            Hasil.setText("This is Simple Past Tense");
                        }
                    }
                }
            }
        }
        }
    }
