package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author licoy.cn
 * @version 2018/3/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigForProperties {

    private String entityPackage;
    private String mapperPackage;
    private String servicePackage;
    private String serviceImplPackage;
    private String projectDirectory;
    private String dbDriveClass;
    private String dbUsername;
    private String dbPassword;
    private String dbUrl;


}
