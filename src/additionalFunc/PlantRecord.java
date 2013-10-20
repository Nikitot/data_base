package additionalFunc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 09.04.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class PlantRecord {
    public static final String[] COLUMNS = {"MARK", "FABRIC_ID", "PLANT_TYPE", "PERFOMANCE", "SMELL", "TURBIDITY"
            , "CHROMA", "OXIDATION", "PH", "HARDNESS", "MINERALIZATION", "FE"
            , "MN", "CHLORIDE", "SULFIDE", "AMMONIA", "FONTAIN", "KOE"
            , "ALKALINE", "B", "BR", "LI", "BA", "SI"};

    private String[] values;

    public void setValuesFromResultSet(ResultSet resultSet){
        values = new String[COLUMNS.length];
        try {
            for (int i=0; i< COLUMNS.length; i++) {
                values[i] = resultSet.getString(COLUMNS[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setValuesFromStrings(String[] strings){
        values = new String[COLUMNS.length];
        for (int i=0; i<strings.length && i<values.length; i++){
            values[i] = strings[i];
        }
    }

    public int putRecordInDb(boolean forceLoad){
        ResultSet resultSet = DataBaseInteraction.getFromDb(null, "PLANT", "FABRIC_ID = '" + values[1] + "'");
        try {
            if (resultSet.next()){
                if (forceLoad) {
                    DataBaseInteraction.updateTableRow("PLANT", new ArrayList<String>(Arrays.asList(COLUMNS))
                            , new ArrayList<String>(Arrays.asList(values)), COLUMNS[1] + " = '" + values[1] + '\'');
                } else {
                    return -1;
                }

            } else {
                DataBaseInteraction.loadTableRow("PLANT", new ArrayList<String>(Arrays.asList(COLUMNS))
                        , new ArrayList<String>(Arrays.asList(values)));
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return -2;
        }
    }

    public String[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        for (int i = 0; i< COLUMNS.length; i++){
            System.out.println(COLUMNS[i] + "\t" + values[i]);
        }
        return "PlantRecord{" +
                "values=" + values +
                '}';
    }
}
