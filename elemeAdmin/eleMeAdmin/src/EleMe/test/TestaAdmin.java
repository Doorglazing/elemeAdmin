package EleMe.test;

import EleMe.dao.adminDao;
import EleMe.dao.lmpl.BusinessDaoImpl;
import EleMe.dao.lmpl.adminDaolmpl;
import EleMe.domain.Admin;

public class TestaAdmin {
    public static void main(String[] args) {
        adminDao adminDaolmpl = new adminDaolmpl();
        Admin admin = adminDaolmpl.getAdminByNameByPass("王磊","123");
        System.out.println(admin);
        new BusinessDaoImpl().businessFind("饺子","");
    }
}
