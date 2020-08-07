package EleMe.dao.lmpl;

import EleMe.dao.adminDao;
import EleMe.domain.Admin;
import EleMe.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class adminDaolmpl implements adminDao {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @Override
    public Admin getAdminByNameByPass(String adminName, String password) {

        Admin admin = null;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "select * from admin where adminName = ? and password = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, adminName);
            pst.setString(2, password);
            rs = pst.executeQuery();

            while (rs.next()){
                admin = new Admin(rs.getInt("adminid"),rs.getString("adminName"),rs.getString("password"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst, conn);
        }
        return admin;
    }
}
