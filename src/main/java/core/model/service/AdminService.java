package core.model.service;

import core.model.bean.AdminBean;
import core.model.bean.ResultBean;
import core.model.domain.TbAdmin;
import core.utils.Md5Utils;
import core.utils.database.DBUtils;
import net.atomarrow.bean.Pager;
import net.atomarrow.bean.ServiceResult;
import net.atomarrow.db.parser.Conditions;
import net.atomarrow.db.parser.Expression;
import net.atomarrow.services.Service;
import net.atomarrow.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//先要继承框架里面的service
@Component
public class AdminService extends Service {
    /**
     * 根据用户名查找一个用户(service)
     *
     * @param adminBean
     * @return
     */
    public ServiceResult getAdmin(AdminBean adminBean) {
        /*AdminBean adminBeaDB = get(adminBean);
        if (adminBeaDB == null) {

            return error("该用户不存在");//返回错误信息,并开启事物回滚
            return  warn("警告");
            return  success(adminBeaDB.getId(),"成功");//返回成功信息
        } else {
            String old_pass = Md5Utils.md5Utils(adminBean.getPassword());
            if (old_pass.equals(adminBeaDB.getPassword())) {
                return success(adminBeaDB);
            } else {
                return failure("密码错误!");
            }
        }
        ServiceResult result = getA(adminBean);
        if(!result.isSuccess()){
            return error(result.getMsg());
        }*/
        return null;
    }

    public ServiceResult getA(AdminBean adminBean) {
        return null;

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


    public ResultBean addAdmin(TbAdmin admin) { //新增用户(service)
        //新增一条数据
        add(admin);

        List<TbAdmin> list = new ArrayList<>();
        /*add(list);*/
        boolean b = addByBatch(list);//添加list类型

        Conditions conditions = new Conditions(TbAdmin.class);//

        conditions.setReturnClass(AdminBean.class);
        conditions.setSelectValue("select b.name as name");
        /*conditions.putEW("userName", admin.getUserName());
        conditions.putEW("password",admin.getPassword());
        int count = getCount(conditions);*/
        boolean notBlank = StringUtil.isNotBlank(admin.getUserName());
        if (notBlank) {
            conditions.putLIKE("name", admin.getUserName());
        }
        conditions.setExpression(new Expression("Inser..."));
        conditions.setJoin("admin LEFT JOIN tbstudent  student");//多表查询
        conditions.putEW("admin.gradeId", admin.getId());//EW等于 判断数据库中的字段内容是否等于
        conditions.putEW("classId", admin.getId());//中间什么都没有相当于and
        conditions.putEW("student.name", "123");
        conditions.or();//sql中的or
        conditions.parenthesesStart();//相当于开始括号
        conditions.putEW("gradeId", 123);
        conditions.putEW("classid", 123);
        conditions.parenthesesEnd();//结束括号
        List<TbAdmin> list1 = getList(conditions);
        int count = getCount(conditions);//
        Pager pager = new Pager();
        pager.setDataTotal(count);
        getListByPage(conditions, pager);
       /* //先判断用户名有误重复
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
        }*/
        return null;
    }

    /*public int add(TbAdmin adminBean) {//新增用户(dao)
        String sql = "INSERT INTO tbadmin (userName,password) VALUES ('" + adminBean.getUserName() + "','" + adminBean.getPassword() + "')";
        int update = DBUtils.update(sql);
        return update;

    }*/
}
