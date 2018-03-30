package com.example.demo.util;

/**
 * @author licoy.cn
 * @version 2018/3/30
 */
public class BeanInfo {
    /*private static final String url = "jdbc:mysql://127.0.0.1/beaninfo";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "root";

    private Connection conn = null;
    private PreparedStatement pst = null;

    public BeanInfoTest(){
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql){
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void main(String[] args) throws Exception {
        BeanInfoTest beanInfoTest = new BeanInfoTest();
        ResultSet set = beanInfoTest.query("select * from student");
        ResultSetMetaData metaData = set.getMetaData();
        List<Map<String,Object>> list = new ArrayList<>();
        while(set.next()){
            Map<String,Object> keys = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                keys.put(metaData.getColumnName(i),set.getObject(metaData.getColumnName(i)));
            }
            list.add(keys);
        }


        List<Student> res = beanInfoTest.toObject(list,Student.class);

        res.forEach(System.out::println);

    }

    public <T> List<T> toObject(List<Map<String,Object>> list,Class<?> clazz){
        List<T> result = new ArrayList<T>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            List<PropertyDescriptor> pdList = Arrays.asList(beanInfo.getPropertyDescriptors());
            Map<String,Method> methodMap = new HashMap<>();
            pdList.forEach((v)-> methodMap.put(v.getName(),v.getWriteMethod()));
            list.forEach((v)-> {
                try {
                    T obj = (T) clazz.newInstance();
                    v.forEach((k, va)->{
                        methodMap.forEach((mk,mv)->{
                            if(mk.equals(k)){
                                try {
                                    mv.invoke(obj,va);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    });
                    result.add(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }*/
}
