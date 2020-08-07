package EleMe.view.impl;

import EleMe.dao.BusinessDao;
import EleMe.dao.lmpl.BusinessDaoImpl;
import EleMe.domain.Business;
import EleMe.view.BusinessView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    private BusinessDao businessDao = new BusinessDaoImpl();
    private Scanner input = new Scanner(System.in);
    private List<Business> list ;
    private String key;
    //查询所有商家
    @Override
    public void businessViewFindAll() {
        System.out.println("商家编号"+"\t"+"商家名称"+"\t"+"商家地址"+"\t"+"商家介绍"+"\t"+"起送费"+"\t"+"配送费");
        for(Business b : businessDao.businessFind(null, null)){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice()+"\t");
        }
    }
    //模糊查询商家
    @Override
    public void businessViewSelect() {
        System.out.println("是否需要输入商家名称关键词(y/n):");
        key = input.next();
        String businessName = "";
        String businessAddress = "";
        if(key.equals("y")){
            System.out.println("请输入商家名称关键词:");
            businessName = input.next();
        }
        System.out.println("是否需要输入商家地址关键词(y/n):");
        key = input.next();
        if (key.equals("y")){
            System.out.println("请输入商家地址关键词:");
            businessAddress = input.next();
        }
        list = businessDao.businessFind(businessName,businessAddress);
        for(Business b : list){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice()+"\t");
        }
    }
    //增加商家 名称 和 password
    @Override
    public void businessViewAdd() {
        System.out.println("请输入你的商家名称");
        String businessName = input.next();
        int i = businessDao.businessAdd(businessName);
        if(i != 0){
            System.out.println("新建商家成功！商家编号为："+ i);
        }
    }
    //删除输入id的商家
    @Override
    public void businessViewDelete() {
        System.out.println("请输入商家编号");
        int businessId = input.nextInt();
        System.out.println("确定要删除吗(y/n)");
        key = input.next();
        if(key.equals("y")){
            businessDao.businessDelete(businessId);
        }else{
            System.out.println("删除失败");
        }
    }
}
