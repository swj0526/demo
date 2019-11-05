package core.model.domain;

public class TbStudent { //学生表
    private int id;
    private String name; //姓名
    private String address;//地址
    private int scoreId;//分数id
    private String time;//创建时间
    private int gradeId;//年级id

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TbStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", scoreId=" + scoreId +
                ", time='" + time + '\'' +
                ", gradeId=" + gradeId +
                '}';
    }
}
