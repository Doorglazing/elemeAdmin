package EleMe.dao;

import EleMe.domain.Business;

import java.util.List;

public interface BusinessDao {
    //查询商家
    List<Business> businessFind(String businessName,String businessAddress);
    //增加商家
    int businessAdd(String businessName);
    //删除商家
    void businessDelete(int id);


    //商家登录
    Business businessByNameByPassword(String businessId, String password);


    // 修改商家信息
    int businessUpdate (Business business);
    // 删除商家信息
}
