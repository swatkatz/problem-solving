package org.swati.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * A CSV Parser
 *  1. John,Smith,john.smith@gmail.com,Los Angeles,1
    2. Jane,Roberts,janer@msn.com,"San Francisco, CA",0
    3. "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
    one,two,,four,"five"
    ex.
    "San Francisco, CA" --> [San Francisco, CA]
    "Alexandra ""Alex""" --> [Alexandra "Alex"]
    five
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class CsvParser {

    public List<String> decodeCSV(String csvString) {
        List<String> individualValues = new ArrayList<String>();
        char[] arr = csvString.toCharArray();
        boolean inQuotes = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            char value = arr[i];
            if (inQuotes) {
                if (value == '"') {
                    if (i == arr.length - 1) {
                        individualValues.add(sb.toString());
                        return individualValues;
                    } else if (arr[i + 1] == '"') {
                        sb.append('"');
                        i++;
                    } else {
                        individualValues.add(sb.toString());
                        sb.setLength(0);
                        inQuotes = false;
                        i++;
                    }
                } else {
                    sb.append(value);
                }
            } else if (value == '"') {
                inQuotes = true;
            } else if (value == ',') {
                individualValues.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(value);
            }
        }
        individualValues.add(sb.toString());
        return individualValues;
    }

    public static void main(String args[]) {
        CsvParser csvParser = new CsvParser();
        List<String> values = csvParser.decodeCSV("John,Smith,john.smith@gmail.com,Los Angeles");
        System.out.println("values size " + values.size());
        values = csvParser.decodeCSV("John,Smith,\"San Francisco, CA\",john.smith@gmail.com");
        System.out.println("values size " + values.size());
        values = csvParser.decodeCSV("\"Alexandra \"\"Alex\"\"\"");
        System.out.println("values size " + values.size());
    }

}
