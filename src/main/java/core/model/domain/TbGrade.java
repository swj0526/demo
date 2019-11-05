package core.model.domain;

public class TbGrade {
    private int gradeId;
    private String gradeName;  //年级名称

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String name) {
        this.gradeName = gradeName;
    }
}
