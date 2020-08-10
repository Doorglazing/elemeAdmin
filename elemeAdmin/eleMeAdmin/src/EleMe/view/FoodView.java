package EleMe.view;

import EleMe.domain.Business;
import EleMe.domain.Food;

import java.util.List;

public interface FoodView {
    // 显示商家所有商品
   void FoodShowAll(Business business);
    // 新增食品
    void FoodAddView(Business business);
    // 查询食品
    void FoodSelectView(Business business);
    // 修改食物
    void FoodUpdateView(Business business);
    // 删除食物
    void FoodRemoveView(Business business);
}
