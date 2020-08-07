package EleMe.view.impl;

import EleMe.dao.lmpl.adminDaolmpl;
import EleMe.domain.Admin;
import EleMe.view.AdminView;

import java.util.Scanner;

public class AdminViewImpl implements AdminView {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public Admin login() {
        System.out.println("请输入你的账号");
        String adminName = scanner.next();
        System.out.println("请输入你的密码");
        String password = scanner.next();
        // 返回一个Admin对象
        return new adminDaolmpl().getAdminByNameByPass(adminName, password);
    }
}
