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
        // toLowerCase 解决假如输入大写Y时候 不会执行的问题
        key = input.next().toLowerCase();
        String businessName = "";
        String businessAddress = "";
        if(key.equals("y")){
            System.out.println("请输入商家名称关键词:");
            businessName = input.next();
        }
        System.out.println("是否需要输入商家地址关键词(y/n):");
        key = input.next().toLowerCase();
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
        key = input.next().toLowerCase();
        if(key.equals("y")){
            businessDao.businessDelete(businessId);
        }else{
            System.out.println("删除失败");
        }
    }


    // --------------------------------------------------------------------------
    // 商家登录
    @Override
    public Business login() {
        System.out.println("请输入账号");
        String businessid = input.next();
        System.out.println("请输入密码");
        String password = input.next();
        return new BusinessDaoImpl().businessByNameByPassword(businessid, password);
    }
    // 查询当前商家信息  business表
    @Override
    public void BusinessShowInfo(Business business) {
        System.out.println(business);
    }
    // 更改商家名称
    @Override
    public void BusinessUpdate(Business business) {
        String str;
        BusinessShowInfo(business);
        System.out.println("是否修改商家名称(y/n):");
        str = input.next().toLowerCase();
        if(str.equals("y")){
            System.out.println("请输入商家名称");
            business.setBusinessName(input.next());
        }
        System.out.println("是否修改商家地址(y/n):");
        str = input.next().toLowerCase();
        if(str.equals("y")){
            System.out.println("请输入商家地址");
            business.setBusinessAddress(input.next());
        }
        System.out.println("是否修改商家简介(y/n):");
        str = input.next().toLowerCase();
        if(str.equals("y")){
            System.out.println("请输入商家简介");
            business.setBusinessExplain(input.next());
        }
        System.out.println("是否修改起送费(y/n):");
        str = input.next().toLowerCase();
        if(str.equals("y")){
            System.out.println("请输入新的起送费");
            business.setStarPrice(input.nextDouble());
        }
        System.out.println("是否修改配送费(y/n):");
        str = input.next().toLowerCase();
        if(str.equals("y")){
            System.out.println("请输入新的配送费");
            business.setDeliveryPrice(input.nextDouble());
        }
        int count = new BusinessDaoImpl().businessUpdate(business);
        if(count > 0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }
    // 修改密码
    @Override
    public void BusinessPassWordUpdate(Business business) {
        String password;
        System.out.println("请输入旧密码");
        password = input.next();
        if(!password.equals(business.getPassword())){
            System.out.println("密码输入不正确");
            return;
        }
        System.out.println("请输入新密码");
        password = input.next();
        if(password==null || password.equals("")){
            System.out.println("密码不能为空");
            return;
        }
        System.out.println("请再次输入新密码");
        String password1 = input.next();
        if(!password.equals(password1)){
            System.out.println("两次输入密码不一致");
            return;
        }
        business.setPassword(password1);
        int count =  new BusinessDaoImpl().businessUpdate(business);
        if(count > 0){
            System.out.println("修改密码成功");
        }else {
            System.out.println("修改密码失败");
        }
    }
}
