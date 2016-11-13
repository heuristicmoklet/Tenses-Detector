package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector.util;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector.model.Verb;
/**
 * Created by Faychan on 11/13/2016.
 */



public class DummyData {
    public DummyData() {
    }

    public static ArrayList<Verb> getDataVerb() {
        ArrayList list = new ArrayList();
        Verb verb = new Verb();
        verb.v1 = "";
        verb.v2 = "";
        verb.v3 = "";

        list.add(verb);
        return list;
    }
}
