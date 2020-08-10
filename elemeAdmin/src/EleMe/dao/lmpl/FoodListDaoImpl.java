package EleMe.dao.lmpl;

import EleMe.dao.FoodDao;
import EleMe.domain.Business;
import EleMe.domain.Food;
import EleMe.tools.Property;
import EleMe.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodListDaoImpl extends Property implements FoodDao {
    // 显示商品
    @Override
    public List<Food> foodList(Business business) {
        List<Food> list = new ArrayList<>();
        try{
            conn = JDBCUtils.getConnection();
            String sql = "select * from food where businessid = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, business.getBusinessId());
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Food(rs.getInt("foodid"),rs.getString("foodName"),rs.getString("foodExplain"),rs.getDouble("foodPrice"),rs.getInt("businessid")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return list;
    }

    @Override
    public int foodAdd(Food food) {
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "insert into food(foodName, foodExplain, foodPrice, businessid) values (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, food.getFoodName());
            pst.setString(2, food.getFoodExplain());
            pst.setDouble(3, food.getFoodPrice());
            pst.setDouble(4, food.getBusinessId());
            count = pst.executeUpdate();
            if(count > 0){
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return 0;
    }

    @Override
    public int updateFood(Food food) {
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "update food set foodname = ?, foodExplain = ?, foodPrice = ? where businessid = ? and foodid = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, food.getFoodName());
            pst.setString(2, food.getFoodExplain());
            pst.setDouble(3, food.getFoodPrice());
            pst.setInt(4,  food.getBusinessId());
            pst.setInt(5, food.getFoodID());
            count = pst.executeUpdate();
            if(count > 0){
                conn.commit();
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return 0;
    }

    @Override
    public int removeFood(Food food) {
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from food where foodid = ? and businessid = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,food.getFoodID());
            pst.setInt(2, food.getBusinessId());
            count = pst.executeUpdate();
            if(count > 0){
                conn.commit();
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return 0;
    }
}
