import bean.TableColumn;
import bean.ConfigForProperties;
import bean.TableEntity;
import com.google.common.base.CaseFormat;
import util.TypeConversion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/3/29
 */
public class DataBaseInfo {

    public static List<TableEntity> get(ConfigForProperties config){
        try {
            Class.forName(config.getDbDriveClass());
            Connection connection = DriverManager
                    .getConnection(config.getDbUrl(),config.getDbUsername(),config.getDbPassword());
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null,"MYSQL",null,new String[]{"TABLE"});
            List<TableEntity> entities = new ArrayList<TableEntity>();
            while (tables.next()){
                if (tables.getString(4) != null && (tables.getString(4).equalsIgnoreCase("TABLE") || tables
                        .getString(4).equalsIgnoreCase("VIEW"))) {
                    String tableName = tables.getString(3).toLowerCase();
                    TableEntity entity = new TableEntity();
                    entity.setTableName(tableName);
                    entity.setClassName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,tableName));
                    // 根据表名提前表里面信息：
                    ResultSet colRet = metaData.getColumns(null, "%", tableName,"%");
                    // 列
                    List<TableColumn> columns = new ArrayList<TableColumn>();
                    TypeConversion typeConversion = new TypeConversion();
                    while (colRet.next()) {
                        TableColumn column = TableColumn.builder()
                                .primaryKey(false)
                                .columnName(colRet.getString("COLUMN_NAME"))
                                .jdbcType(colRet.getInt("DATA_TYPE"))
                                .remarks(colRet.getString("REMARKS"))
                                .defaultValue(colRet.getString("COLUMN_DEF"))
                                .build();
                        column.setJavaType(typeConversion.get(column));
                        column.setClassName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,column.getColumnName()));
                        columns.add(column);
                    }
                    entity.setColumns(columns);
                    entities.add(entity);
                }
            }
            return entities;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
