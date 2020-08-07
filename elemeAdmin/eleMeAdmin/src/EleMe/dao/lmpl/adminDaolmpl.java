package EleMe.dao.lmpl;

import EleMe.dao.adminDao;
import EleMe.domain.Admin;
import EleMe.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class adminDaolmpl implements adminDao {

    @Override
    public Admin getAdminByNameByPass(String adminName, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "select * from admin where ";

        }catch (Exception e){
            e.printStackTrace();
        }



        return null;
    }
}
