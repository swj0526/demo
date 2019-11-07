package core.model.domain;

import net.atomarrow.domains.Domain;
import org.springframework.stereotype.Component;

@Component
public class TbAdmin extends Domain {  //管理员账号表
    private Integer id;
    private String userName; //用户名
    private String password; //密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "TbAdmin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public TbAdmin(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public TbAdmin() {
    }
}
