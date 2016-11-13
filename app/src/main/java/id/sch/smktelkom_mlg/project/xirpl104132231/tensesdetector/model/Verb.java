package id.sch.smktelkom_mlg.project.xirpl104132231.tensesdetector.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Faychan on 11/12/2016.
 */
// /cek log
@Table(name = "Verb")
public class Verb extends Model implements Serializable{
    @Column
    public String v1;
    @Column
    public String v2;
    @Column
    public String v3;

    public Verb(){
        super();
    }

    public static List<Verb> getAll(String x, String y)
    {
        return new Select()
                .from(Verb.class)
                .where("nama=? AND kels=?",x,y)
                .execute();
    }
}
