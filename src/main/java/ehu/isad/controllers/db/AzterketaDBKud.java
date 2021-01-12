package ehu.isad.controllers.db;

import ehu.isad.models.URLModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AzterketaDBKud {

    // singleton patroia

    private static AzterketaDBKud instantzia = new AzterketaDBKud();

    public static AzterketaDBKud getInstantzia() {
        return instantzia;}

    private AzterketaDBKud() {
    }

    public URLModel dago (String pMd5) {

        String md5="";
        String version="";
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

        String query = "select md5, version from checksums  WHERE md5='"+pMd5+"';";
        ResultSet rs = dbkud.execSQL(query);

        try {
            if (rs.next()) {

                md5 = rs.getString("md5");
                version = rs.getString("version");

            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        URLModel emaitza=new URLModel("",md5,version);
        return emaitza;
    }


    public void md5Sartu(String pMd5,String pVersion){

        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

        String query = "insert into checksums (idCMS, version, md5, path) values (1,'"+pVersion+"','"+pMd5+"','README')";
        dbkud.execSQL(query);

    }
}
