package additionalFunc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 09.04.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class PlantRecord {
    private String mark;
    private String fabricId;
    private String plantType;
    private String perfomance;
    private String smell;
    private String turbidity;
    private String chroma;
    private String oxidation;
    private String ph;
    private String hardness;
    private String mineralization;
    private String fe;
    private String mn;
    private String chloride;
    private String sulfide;
    private String ammonia;
    private String fontain;
    private String koe;
    private String alkaline;
    private String b;
    private String br;
    private String li;
    private String ba;
    private String si;

    public void setFieldsFromResultSet(ResultSet resultSet){
        try {
            mark            = resultSet.getString("MARK");
            fabricId        = resultSet.getString("FABRIC_ID");
            plantType       = resultSet.getString("PLANT_TYPE");
            perfomance      = resultSet.getString("PERFOMANCE");
            smell           = resultSet.getString("SMELL");
            turbidity       = resultSet.getString("TURBIDITY");
            chroma          = resultSet.getString("CHROMA");
            oxidation       = resultSet.getString("OXIDATION");
            ph              = resultSet.getString("PH");
            hardness        = resultSet.getString("HARDNESS");
            mineralization  = resultSet.getString("MINERALIZATION");
            fe              = resultSet.getString("FE");
            mn              = resultSet.getString("MN");
            chloride        = resultSet.getString("CHLORIDE");
            sulfide         = resultSet.getString("SULFIDE");
            ammonia         = resultSet.getString("AMMONIA");
            fontain         = resultSet.getString("FONTAIN");
            koe             = resultSet.getString("KOE");
            alkaline        = resultSet.getString("ALKALINE");
            b               = resultSet.getString("B");
            br              = resultSet.getString("BR");
            li              = resultSet.getString("LI");
            ba              = resultSet.getString("BA");
            si              = resultSet.getString("SI");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    @Override
    public String toString() {
        return "PlantRecord{" +
                "mark='" + mark + '\'' +
                ", fabricId='" + fabricId + '\'' +
                ", plantType='" + plantType + '\'' +
                ", perfomance='" + perfomance + '\'' +
                ", smell='" + smell + '\'' +
                ", turbidity='" + turbidity + '\'' +
                ", chroma='" + chroma + '\'' +
                ", oxidation='" + oxidation + '\'' +
                ", ph='" + ph + '\'' +
                ", hardness='" + hardness + '\'' +
                ", mineralization='" + mineralization + '\'' +
                ", fe='" + fe + '\'' +
                ", mn='" + mn + '\'' +
                ", chloride='" + chloride + '\'' +
                ", sulfide='" + sulfide + '\'' +
                ", ammonia='" + ammonia + '\'' +
                ", fontain='" + fontain + '\'' +
                ", koe='" + koe + '\'' +
                ", alkaline='" + alkaline + '\'' +
                ", b='" + b + '\'' +
                ", br='" + br + '\'' +
                ", li='" + li + '\'' +
                ", ba='" + ba + '\'' +
                ", si='" + si + '\'' +
                '}';
    }

}
