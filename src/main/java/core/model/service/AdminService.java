package core.model.service;

import core.model.domain.TbAdmin;
import core.model.util.Md5Utils;
import net.atomarrow.bean.ServiceResult;
import net.atomarrow.db.parser.Conditions;
import net.atomarrow.services.Service;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//先要继承框架里面的service
@Component
public class AdminService extends Service {
    /**
     * 登录service
     *
     * @param admin
     * @return
     */
    public ServiceResult getAdminService(TbAdmin admin) {
        ServiceResult result = getAdimDao(admin);
        if (result.isSuccess() == true) {  //找到返回的数据,
            //找到数据之后,在取出密码跟现在的密码用md5进行加密
            TbAdmin adminDB = (TbAdmin) result.getResult();
            String passwordDB = adminDB.getPassword();
            String password = admin.getPassword();
            String passwordMD5 = Md5Utils.md5Utils(password);
            if (passwordDB.equals(passwordMD5)) {
                return success(adminDB.getUserName(), "登录成功");
            } else {
                return error("密码错误");
            }
        } else { //没有找到数据
            return error("用户不存在");
        }
    }

    /**
     * 登录dao
     *
     * @param admin
     * @return
     */
    public ServiceResult getAdimDao(TbAdmin admin) {
        Conditions conditions = new Conditions(TbAdmin.class);//创建一个条件对象
        conditions.putEW("userName", admin.getUserName());
        TbAdmin adminDB = getOne(conditions);//查找一个对象
        if (adminDB == null) {
            return error("登录失败");
        } else {
            return success(adminDB);
        }
    }

    /**
     * 注册用户service
     *
     * @param admin
     * @return
     */
    public ServiceResult addAdminService(TbAdmin admin) {
        //因为是管理员,所以在新增之前要判断用户名存不存在
        boolean userName = checkExist(TbAdmin.class, "userName", admin.getUserName());
        if (userName == false) { //没有找到相同的时候,就可以注册
            //注册之前,要将密码进行加密
            String password = admin.getPassword();
            String passwordMD5 = Md5Utils.md5Utils(password);
            admin.setPassword(passwordMD5);
             add(admin);//注册用户
            return success("新增成功");
        } else { //找到了,返回失败,因为用户名不可重复
            return error("用户名已存在,请重新注册用户名");
        }

       /* //新增一条数据
        add(admin);

        List<TbAdmin> list = new ArrayList<>();
        *//*add(list);*//*
        boolean b = addByBatch(list);//添加list类型

       Conditions  conditions = new Conditions(TbAdmin.class);//

        conditions.setReturnClass(AdminBean.class);
        conditions.setSelectValue("select b.name as name");
        *//*conditions.putEW("userName", admin.getUserName());
        conditions.putEW("password",admin.getPassword());
        int count = getCount(conditions);*//*
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
       *//* //先判断用户名有误重复
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
        }*//*
        return null;*/
    }
}
