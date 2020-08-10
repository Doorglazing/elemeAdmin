package EleMe.dao;

import EleMe.domain.Business;
import EleMe.domain.Food;

import java.util.List;

public interface FoodDao {
    // 列出food食品
    List<Food> foodList(Business business);
    // 新增food食品
    int foodAdd(Food food);
    // 修改食品
    int updateFood(Food food);
    // 删除食品
    int removeFood(Food food);
}
