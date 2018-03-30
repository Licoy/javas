package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author licoy.cn
 * @version 2018/3/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableColumn implements Serializable {

    private Integer jdbcType;

    private Boolean primaryKey;

    private String columnName;

    private String className;

    private String remarks;

    private String defaultValue;

    private String javaType;

}
