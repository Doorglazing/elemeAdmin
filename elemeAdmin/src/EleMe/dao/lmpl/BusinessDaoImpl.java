package EleMe.dao.lmpl;

import EleMe.dao.BusinessDao;
import EleMe.domain.Business;
import EleMe.tools.GetLogin;
import EleMe.tools.Property;
import EleMe.utils.JDBCUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl extends Property implements BusinessDao {
    // 管理员操作页面
    // 查询所有商家
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
    // 添加商家
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
            System.out.println(pst);
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
    // 删除商家
    @Override
    public void businessDelete(int id) {
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from business where businessid = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            count = pst.executeUpdate();
            if(count > 0){
                conn.commit();
            }else{
                System.out.println("商家编号错误,删除失败");
            }
        } catch (SQLException e) {
            count = 0;
            try{
                conn.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst, conn);
        }
    }

    /**
     * ----------------------------------------------------------------------
     * 商家操作页面
     */
    //商家登录
    @Override
    public Business businessByNameByPassword(String businessId, String password) {
        Business business = null;
        try{
            rs = new GetLogin().getLogin(businessId, password, "business");
            System.out.println(rs);
            while (rs.next()){
                business = new Business(rs.getInt("businessId"),
                        rs.getString("password"),
                        rs.getString("businessName"),
                        rs.getString("businessAddress"),
                        rs.getString("businessExplain"),
                        rs.getDouble("starPrice"),
                        rs.getDouble("deliveryPrice"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return business;
    }
    // 修改商家信息
    @Override
    public int businessUpdate(Business business) {
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "update business set businessName = ?, businessAddress = ? ,businessExplain = ?, starPrice = ?, deliveryPrice = ?, password = ?  where businessid = ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, business.getBusinessName());
            pst.setString(2, business.getBusinessAddress());
            pst.setString(3, business.getBusinessExplain());
            pst.setDouble(4, business.getStarPrice());
            pst.setDouble(5, business.getDeliveryPrice());
            pst.setString(6, business.getPassword());
            pst.setInt(7, business.getBusinessId());
            count =  pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return count;
    }
    //修改商家密码

}
