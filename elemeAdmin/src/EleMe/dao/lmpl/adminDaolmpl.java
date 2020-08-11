package EleMe.dao.lmpl;

import EleMe.dao.adminDao;
import EleMe.domain.Admin;
import EleMe.tools.GetLogin;
import EleMe.tools.Property;
import EleMe.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDaolmpl extends Property implements adminDao {
    @Override
    // 管理员登录方法 are you sure？
    public Admin getAdminByNameByPass(String adminName, String password) {
        Admin admin = null;
        try{
            rs = new GetLogin().getLogin(adminName, password, "admin");
            while (rs.next()){
                admin = new Admin(rs.getInt("adminid"),rs.getString("adminName"),rs.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }

        return admin;
    }

}
