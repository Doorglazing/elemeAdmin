package EleMe;

import EleMe.dao.BusinessDao;
import EleMe.dao.lmpl.BusinessDaoImpl;
import EleMe.domain.Business;
import EleMe.view.BusinessView;
import EleMe.view.FoodView;
import EleMe.view.impl.BusinessViewImpl;
import EleMe.view.impl.FoodViewImpl;

import java.util.Scanner;

public class EleBusiness {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        businessWork();
    }
    public static void businessWork(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 V1.0\t\t\t\t|");
        System.out.println("-----------------------------------------------------------");
        // 登录
        BusinessDao businessDao = new BusinessDaoImpl();
        BusinessView businessView = new BusinessViewImpl();
        Business business = businessView.login();
        int menu = 0;
        if (business!=null){
            while (true){
                System.out.println("========= 一级菜单(商家管理) 1.查看商家信息=2.修改商家信息=3.更改密码=4.所属商品管理=5.退出系统 =========");
                System.out.println("请选择相应的编号");
                menu = input.nextInt();
                switch (menu){
                    case 1 : businessView.BusinessShowInfo(business);
                        break;
                    case 2 : businessView.BusinessUpdate(business);
                        break;
                    case 3 : businessView.BusinessPassWordUpdate(business);
                        break;
                    case 4 : foodWork(business);
                        break;
                    case 5 :
                        System.out.println("已推出商家管理系统");
                        return;
                    default:
                        System.out.println("编号输入不正确请重新输入");
                        break;
                }
            }
        }else {
            System.out.println("账号密码错误");
        }
    }
    private static void foodWork(Business business){
        int twoMenu = 0;
        FoodView foodView = new FoodViewImpl();
        while (true){
            System.out.println("========= 二级菜单(商家管理) 1.查看食品列表=2.新增食品=3.修改食品=4.删除食品=5.返回一级菜单 =========");
            System.out.println("请选择相应的编号");
            twoMenu = input.nextInt();
            switch (twoMenu){
                case 1: foodView.FoodShowAll(business);
                    break;
                case 2: foodView.FoodAddView(business);
                    break;
                case 3: foodView.FoodUpdateView(business);
                    break;
                case 4: foodView.FoodRemoveView(business);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("编号输入不正确请重新输入");
                    break;
            }

        }
    }
}
