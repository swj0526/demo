package core.model.service;

import core.model.bean.AdminBean;
import core.model.bean.ResultBean;
import core.utils.Md5Utils;
import core.utils.database.DBUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService extends BaseService {
    /**
     * 根据用户名查找一个用户(service)
     *
     * @param adminBean
     * @return
     */
    public ResultBean getAdmin(AdminBean adminBean) {
        AdminBean adminBeaDB = get(adminBean);
        if (adminBeaDB == null) {
            return failure("该用户不存在");
        } else {
            String old_pass = Md5Utils.md5Utils(adminBean.getPassword());
            if (old_pass.equals(adminBeaDB.getPassword())) {
                return success(adminBeaDB);
            } else {
                return failure("密码错误!");
            }
        }
    }

    public AdminBean get(AdminBean adminBean) {  //根据用户名查找一个用户(dao)
        String sql = "SELECT * FROM tbadmin WHERE userName = '" + adminBean.getUserName() + "'";
        AdminBean adminBeanDB = null;
        try {
            List<Map<String, Object>> list = DBUtils.getList(sql);
            if (list.size() != 0) {
                Map<String, Object> map = list.get(0);
                adminBeanDB = new AdminBean();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("password")) {
                        adminBeanDB.setPassword((String) entry.getValue());
                    }
                    if (entry.getKey().equals("userName")) {
                        adminBeanDB.setUserName((String) entry.getValue());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return adminBeanDB;

    }


    public ResultBean addAdmin(AdminBean adminBean) { //新增用户(service)
        //先判断用户名有误重复
        AdminBean adminBeanDB = get(adminBean);
        if (adminBeanDB == null) {
            //没有重复的名称,在继续新增
            int add = add(adminBean);
            if (add == 0) {
                return failure("系统异常,注册失败!");
            } else {
                return success();
            }
        } else {
            return failure("该用户名已存在,重新填写!");
        }
    }

    public int add(AdminBean adminBean) {//新增用户(dao)
        String sql = "INSERT INTO tbadmin (userName,password) VALUES ('" + adminBean.getUserName() + "','" + adminBean.getPassword() + "')";
        int update = DBUtils.update(sql);
        return update;

    }
}
