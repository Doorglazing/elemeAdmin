package EleMe;

import EleMe.domain.Admin;
import EleMe.view.AdminView;
import EleMe.view.BusinessView;
import EleMe.view.impl.AdminViewImpl;
import EleMe.view.impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdmin {
    public static void main(String[] args) {
        work();
    }
    public static void work(){
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 V1.0\t\t\t\t|");
        System.out.println("-----------------------------------------------------------");
        // 登录
        AdminView adminView = new AdminViewImpl();
        Admin admin = adminView.login();
        BusinessView businessView = new BusinessViewImpl();
        if(admin != null){
            System.out.println("欢迎来到饿了么商家系统");
            int menu;
            while (true){
                System.out.println("========= 1.所有商家列表=2.搜索商家=3.新建商家=4.删除商家=5.退出系统 =========");
                System.out.println("请选择相应的编号");
                menu = input.nextInt();
                switch(menu){
                    case 1:
                        businessView.businessViewFindAll();
                        break;
                    case 2:
                        businessView.businessViewSelect();
                        break;
                    case 3:
                        businessView.businessViewAdd();
                        break;
                    case 4:
                        businessView.businessViewDelete();
                        break;
                    case 5:
                        System.out.println("退出系统,欢迎下次光临");
                        return;
                    default:
                        System.out.println("编号输入错误");
                        break;
                }
            }
        }else{
            System.out.println("账号密码有错误");
        }
    }
}
