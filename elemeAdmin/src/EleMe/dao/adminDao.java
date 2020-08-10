package EleMe.dao;

import EleMe.domain.Admin;

public interface adminDao {
    //管理员登录
    Admin getAdminByNameByPass(String adminName, String password);
}
