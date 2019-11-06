package core.utils;

import core.model.bean.ExcelBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelUtils {
    public static void createExcel(ExcelBean excel) throws IllegalAccessException, IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建一个工作部
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(excel.getSheetName());//创建一个工作表(参数是sheet表的名称)
        //创建第一行sheet表中的内容,索引是从0开始,第一行是sheet表的标题栏
        HSSFRow hssfRowHeader = hssfSheet.createRow(0);
        String[] title = excel.getTitle(); //标题数组
        HSSFCell cell = null; //创建单元格对象
        //遍历生成标题栏里面的标题单元格
        for (int i = 0; i < title.length; i++) {
            cell = hssfRowHeader.createCell(i); //遍历生成索引是0行的每一个标题单元格对象
            cell.setCellValue(title[i]);  //添加数据
        }
        List students = excel.getList();//内容集合
        String[] keys = excel.getKeys();//属性名的数组,判断到底要在excel呈现出多少数据
        //导出内容
        for (int i = 0; i < students.size(); i++) {  //集合的长度就是sheet表中内容的条数,比如(长度为3,内容就有三条,索引从1开始)
            HSSFRow hssfRowContext = hssfSheet.createRow(i + 1);
            Field[] studentArray = students.get(i).getClass().getDeclaredFields(); //利用反射将对象属性变成一个数组
            for (int j = 0; j < studentArray.length; j++) {
                System.out.println();
                studentArray[j].setAccessible(true); //这句很重要,意思是设置是否允许访问,而不是修改原来的访问权限修饰词。
                for (int k = 0; k < keys.length; k++) {
                    if (keys[k].equals(studentArray[j].getName())) {
                        cell = hssfRowContext.createCell(k);
                        cell.setCellValue(studentArray[j].get(students.get(i)).toString());
                    }
                }
            }
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(excel.getPath() + "/" + excel.getExcelName() + ".xlsx");
            hssfWorkbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            hssfWorkbook.close();
            fos.close();
        }



    }
}
