package org.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class JsonCreator {

    private static void addValueToJson(Object value, StringBuffer sb) {
        if (value == null) {
            sb.append(createValue("null"));
        } else if (value instanceof Map) {
            Map<String, Object> castMap = (Map<String, Object>) value;
            sb.append("{");
            for (Map.Entry<String, Object> entry : castMap.entrySet()) {
                String key = entry.getKey();
                Object mapVal = entry.getValue();
                sb.append(createKey(key));
                addValueToJson(mapVal, sb);
            }
            sb.append("}");
        } else if (value instanceof Boolean) {
            Boolean castVal = (Boolean) value;
            if (Boolean.TRUE.equals(castVal)) {
                sb.append(createValue("true"));
            } else {
                sb.append(createValue("false"));
            }
        } else if (value instanceof String) {
            String castVal = (String) value;
            sb.append(createValue(castVal));
        } else if (value instanceof Integer) {
            Integer castVal = (Integer) value;
            sb.append(createValue(castVal.toString()));
        } else if (value instanceof List) {
            List<Object> castList = (List<Object>) value;
            sb.append(" [");
            for (int i = 0; i < castList.size(); i++) {
                addValueToJson(castList.get(i), sb);
            }
            sb.replace(sb.length() - 1, sb.length(), "");
            sb.append("]");
        } else {
            throw new IllegalArgumentException("no definition found");
        }
    }

    private static String createKey(String key) {
        return "\"" + key + "\" : ";
    }

    private static String createValue(String val) {
        return val + " ,";
    }

    public static void main(String args[]) {
        Map<String, Object> jsonMem = new HashMap<String, Object>();
        jsonMem.put("k1", 3);
        jsonMem.put("k2", "ser");
        Map<String, Object> subMap = new HashMap<String, Object>();
        List<Object> value = new ArrayList<Object>();

        Map<String, Object> testMap = new HashMap<String, Object>();
        testMap.put("k6", "str6");

        value.addAll(Arrays.asList(true, 4, testMap));
        subMap.put("k4", value);
        jsonMem.put("k3", subMap);
        StringBuffer sb = new StringBuffer();
        addValueToJson(jsonMem, sb);
        System.out.println(sb);
    }
}
