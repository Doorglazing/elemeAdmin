package EleMe.view.impl;

import EleMe.dao.lmpl.FoodListDaoImpl;
import EleMe.domain.Business;
import EleMe.domain.Food;
import EleMe.view.FoodView;

import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    private static Scanner input = new Scanner(System.in);
    @Override
    public void FoodShowAll(Business business) {
        List<Food> list = new FoodListDaoImpl().foodList(business);
        for(Food li : list){
            System.out.println(li.getFoodID()+"\t"+li.getFoodName()+"\t"+li.getFoodExplain()+"\t" + li.getFoodPrice());
        }
    }

    @Override
    public void FoodAddView(Business business) {
        System.out.println("请输入新增食物名字，简介，价格(以回车分隔)");
        new FoodListDaoImpl().foodAdd(new Food(0, input.next(),input.next(),input.nextDouble(), business.getBusinessId()));
    }

    @Override
    public void FoodSelectView(Business business) {
        System.out.println("请输入要查询的食物id");
        int id = input.nextInt();
       for(Food food:new FoodListDaoImpl().foodList(business)){
           if(food.getFoodID() == id){
               System.out.println(food.getFoodID()+"\t"+food.getFoodName()+"\t"+food.getFoodExplain()+"\t" + food.getFoodPrice());
           }
       }
    }

    @Override
    public void FoodUpdateView(Business business) {
        System.out.println("请输入要修改的食物id");
        int id = input.nextInt();
        System.out.println("请输入要修改的食物名字，简介，价格（以回车分隔）");
        new FoodListDaoImpl().updateFood(new Food(id, input.next(), input.next(), input.nextDouble(), business.getBusinessId()));
    }

    @Override
    public void FoodRemoveView(Business business) {
        System.out.println("请输入要删除的ID");
        new FoodListDaoImpl().removeFood(new Food(input.nextInt(), null, null, 0.0, business.getBusinessId()));
    }
}
