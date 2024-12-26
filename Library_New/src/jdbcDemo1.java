import java.sql.*;
public class jdbcDemo1 {
    private static Connection dbConn = null;

    public static void main(String[] args) {
        String dbURL="jdbc:sqlserver://localhost:1433;databaseName=BM;encrypt=false";

        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            dbConn = DriverManager.getConnection(dbURL, "sa", "123456");
            System.out.println("连接数据库成功！");
            String sql="select * from yonghu";
            PreparedStatement statement=null;
            statement=dbConn.prepareStatement(sql);
            ResultSet res=null;
            res=statement.executeQuery();
            while(res.next()){
                String title=res.getString("user_name");
                System.out.println(title);
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }

    }
}
