import bean.ConfigForProperties;
import bean.TableEntity;
import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
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
public class Code {

    private static String classPath;

    {
        classPath = Code.class.getClassLoader().getResource("").getPath();
    }

    public static void main(String[] args) throws Exception {
        new Code().run();
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
        String packageDir = config.getEntityPackage();
        packageDir = packageDir.replaceAll("\\.","/");
        String outPath = config.getProjectDirectory()+packageDir+File.separator+entity.getClassName()+".java";
        File file = new File(outPath);
        if(!file.exists()){
            Files.createParentDirs(file);
            file.createNewFile();
        }
        Writer writer = new FileWriter(outPath);
        Map<String,Object> map = new HashMap<>();
        map.put("entity",entity);
        map.put("config",config);
        Template template = cfg.getTemplate("entity.ftl");
        template.process(map,writer);
        writer.flush();
        generateMapper(map,entity,config,cfg);
    }

    private static void generateMapper(Map<String,Object> map,TableEntity entity,ConfigForProperties config,
                                       Configuration cfg) throws Exception{
        String packageDir = config.getMapperPackage();
        packageDir = packageDir.replaceAll("\\.","/");
        String outPath = config.getProjectDirectory()+packageDir+File.separator+entity.getClassName()+"Mapper.java";
        File file = new File(outPath);
        if(!file.exists()){
            Files.createParentDirs(file);
            file.createNewFile();
        }
        Writer writer = new FileWriter(outPath);
        Template template = cfg.getTemplate("mapper.ftl");
        template.process(map,writer);
        writer.flush();
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
                .dbPassword(properties.getProperty("db-username"))
                .dbUsername(properties.getProperty("db-password"))
                .dbUrl(properties.getProperty("db-url"))
                .build();
    }
}
