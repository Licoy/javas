package util;

import bean.TableColumn;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author licoy.cn
 * @version 2018/3/29
 */
public class TypeConversion {

    private Map<Integer, String> typeMap;

    public TypeConversion() {
        typeMap = new HashMap<Integer, String>();
        typeMap.put(Types.ARRAY, "Object");
        typeMap.put(Types.BIGINT, "Long");
        typeMap.put(Types.BINARY, "byte[]");
        typeMap.put(Types.BIT, "Boolean");
        typeMap.put(Types.BLOB, "byte[]");
        typeMap.put(Types.BOOLEAN, "Boolean");
        typeMap.put(Types.CHAR, "String");
        typeMap.put(Types.CLOB, "String");
        typeMap.put(Types.DATALINK, "Object");
        typeMap.put(Types.DATE, "Date");
        typeMap.put(Types.DECIMAL, "BigDecimal");
        typeMap.put(Types.DISTINCT, "Object");
        typeMap.put(Types.DOUBLE, "Double");
        typeMap.put(Types.FLOAT, "Double");
        typeMap.put(Types.INTEGER, "Integer");
        typeMap.put(Types.JAVA_OBJECT, "Object");
        typeMap.put(Types.LONGVARBINARY, "byte[]");
        typeMap.put(Types.LONGVARCHAR, "String");
        typeMap.put(Types.NCHAR, "String");
        typeMap.put(Types.NCLOB, "String");
        typeMap.put(Types.NVARCHAR, "String");
        typeMap.put(Types.NULL, "Object");
        typeMap.put(Types.NUMERIC, "BigDecimal");
        typeMap.put(Types.OTHER, "Object");
        typeMap.put(Types.REAL, "Float");
        typeMap.put(Types.REF, "Object");
        typeMap.put(Types.SMALLINT, "Short");
        typeMap.put(Types.STRUCT, "Object");
        typeMap.put(Types.TIME, "Date");
        typeMap.put(Types.TIMESTAMP, "Date");
        typeMap.put(Types.TINYINT, "Byte");
        typeMap.put(Types.VARBINARY, "byte[]");
        typeMap.put(Types.VARCHAR, "String");
    }

    public String get(TableColumn column){
        return typeMap.get(column.getJdbcType());
    }
}
