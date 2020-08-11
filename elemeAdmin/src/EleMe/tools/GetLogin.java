package EleMe.tools;

import EleMe.utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLogin extends Property {
    /**
     *  登录方法
     *
     */
    // 登录方法 商家和管理员使用
    public ResultSet getLogin(String name, String password, String loginType) throws SQLException{
        if(name == null){
            name = "";
        }
        String sql = "";
            if(loginType == "admin"){
                sql = "select * from  admin  where adminName = ? and password = ?";
            }else {
                sql = "select * from business where businessId = ? and password = ?";
            }
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, password);
            rs = pst.executeQuery();
        return rs;
    }
}
