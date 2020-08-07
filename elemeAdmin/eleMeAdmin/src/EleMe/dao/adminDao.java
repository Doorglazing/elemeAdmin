package EleMe.dao;

import EleMe.domain.Admin;

public interface adminDao {
    Admin getAdminByNameByPass(String adminName, String password);
}
