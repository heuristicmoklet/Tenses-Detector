package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector;

import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by User on 30/11/2016.
 */

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.slide_1));
        addSlide(SampleSlide.newInstance(R.layout.slide_2));
        addSlide(SampleSlide.newInstance(R.layout.slide_3));
    }

    private void addSlide(SampleSlide sampleSlide) {
    }
}
