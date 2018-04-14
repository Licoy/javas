import bean.ConfigForProperties;
import bean.TableEntity;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

/**
 * @author licoy.cn
 * @version 2018/3/29
 */
public class CodeGenerate {

    private static String classPath;

    {
        classPath = CodeGenerate.class.getClassLoader().getResource("").getPath();
    }

    public static void main(String[] args) throws Exception {
        new CodeGenerate().run();
    }

    public void run() throws Exception {
        ConfigForProperties config = getConfig(classPath);
        List<TableEntity> tableEntities = DataBaseInfo.get(config);
        System.out.println();
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(classPath + "/ftl")); //需要文件夹绝对路径
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        assert tableEntities != null;
        tableEntities.forEach((v)->{
            try {
                generateEntity(v,config,cfg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void generateEntity(TableEntity entity,ConfigForProperties config,Configuration cfg) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("entity",entity);
        map.put("config",config);
        write(config.getEntityPackage(),"","entity.ftl",entity,config,cfg,map);
        generateMapper(map,entity,config,cfg);
    }

    private static void generateMapper(Map<String,Object> map,TableEntity entity,ConfigForProperties config,
                                       Configuration cfg) throws Exception{
        write(config.getMapperPackage(),"Mapper","mapper.ftl",entity,config,cfg,map);
        generateService(map,entity,config,cfg);
    }

    private static void generateService(Map<String,Object> map,TableEntity entity,ConfigForProperties config,
                                        Configuration cfg) throws Exception{
        write(config.getServicePackage(),"Service","service.ftl",entity,config,cfg,map);
        generateServiceImpl(map,entity,config,cfg);
    }

    private static void generateServiceImpl(Map<String,Object> map,TableEntity entity,ConfigForProperties config,
                                            Configuration cfg) throws Exception{
        write(config.getServiceImplPackage(),"ServiceImpl","service-impl.ftl",entity,config,cfg,map);
    }

    private static void write(String packagePath,String suffix,String templateName,TableEntity entity
            ,ConfigForProperties config,Configuration cfg,Map<String,Object> map)  throws Exception{
        packagePath = packagePath.replaceAll("\\.","/");
        String outPath = config.getProjectDirectory()+packagePath+File.separator+entity.getClassName()+suffix+".java";
        File file = new File(outPath);
        if(!file.exists()){
            Files.createParentDirs(file);
            file.createNewFile();
        }
        Writer writer = new FileWriter(outPath);
        Template template = cfg.getTemplate(templateName);
        template.process(map,writer);
        writer.flush();
    }

    private static ConfigForProperties getConfig(String classPath) throws Exception{
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(classPath+"/config.properties");
        properties.load(inputStream);
        return ConfigForProperties.builder()
                .entityPackage(properties.getProperty("entity-package"))
                .mapperPackage(properties.getProperty("mapper-package"))
                .servicePackage(properties.getProperty("service-package"))
                .serviceImplPackage(properties.getProperty("service-impl-package"))
                .projectDirectory(properties.getProperty("project-directory"))
                .dbDriveClass(properties.getProperty("db-driver-class"))
                .dbUsername(properties.getProperty("db-username"))
                .dbPassword(properties.getProperty("db-password"))
                .dbUrl(properties.getProperty("db-url"))
                .build();
    }
}
