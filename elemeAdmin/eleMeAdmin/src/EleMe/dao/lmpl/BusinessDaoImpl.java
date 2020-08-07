package EleMe.dao.lmpl;

import EleMe.dao.BusinessDao;
import EleMe.domain.Business;
import EleMe.utils.JDBCUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public List<Business> businessFind(String businessName, String businessAddress) {
        StringBuilder sql = new StringBuilder("select * from business where 1=1");
        List<Business> list = new ArrayList<>();

        if(businessName != null && !businessName.equals("")){
            sql.append(" and businessName like '%").append(businessName).append("%'");
        }
        if(businessAddress != null && !businessAddress.equals("")){
            sql.append(" and businessAddress like '%").append(businessAddress).append("%'");
        }
        try{
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Business(rs.getInt("businessId"),
                        rs.getString("password"),
                        rs.getString("businessName"),
                        rs.getString("businessAddress"),
                        rs.getString("businessExplain"),
                        rs.getDouble("starPrice"),
                        rs.getDouble("deliveryPrice")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }

        return list;
    }

    @Override
    public int businessAdd(String businessName) {
        int id = 0;
        try{
            String sql = "insert into business(businessName, password) values(?,?)";
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, businessName);
            pst.setString(2,"123");
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return id;
    }

    @Override
    public void businessDelete(int id) {
        try{
            conn = JDBCUtils.getConnection();
            String sql = "delete from business where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
