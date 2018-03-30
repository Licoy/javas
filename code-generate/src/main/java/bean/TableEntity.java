package bean;

import lombok.Data;

import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/3/29
 */
@Data
public class TableEntity {

    private String className;

    private String tableName;

    private List<TableColumn> columns;
}
