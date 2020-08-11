package EleMe.view;

import EleMe.domain.Business;

public interface BusinessView {
    void businessViewFindAll();
    void businessViewSelect();
    void businessViewAdd();
    void businessViewDelete();

    Business login();
    //显示商家信息
    void BusinessShowInfo(Business business);
    //修改商家信息
    void BusinessUpdate(Business business);
    //修改密码
    void BusinessPassWordUpdate(Business business);
}
