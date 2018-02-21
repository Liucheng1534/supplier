package ruantong.supplier.Util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 67471 on 2017/11/10.
 */
public class PoiUtil {
    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFSheet sheet = workbook.createSheet();
    HSSFCellStyle cellStyle = workbook.createCellStyle();
    HSSFFont font = workbook.createFont();

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    //拿到模板表格
    public void getTitleRow(Field[] fields) {
        //设置默认列宽
        sheet.setDefaultColumnWidth(30);
        HSSFRow row = sheet.createRow(0);
        //设置字体样式
        font.setColor(Font.COLOR_RED);
        font.setBold(true);
        font.setFontName("宋体");
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setFont(font);

        //为标题行赋值
        for (int i = 0; i < fields.length; i++) {
            HSSFCell cell = row.createCell(i);
            Comment annotation = fields[i].getAnnotation(Comment.class);
            if (annotation != null) {
                cell.setCellValue(annotation.value());
            } else {
                cell.setCellValue(fields[i].getName());
            }
            cell.setCellStyle(cellStyle);
        }
    }

    //拿到完整的表格
    public HSSFWorkbook getWorkbook(Class c, List list, String sheetName) throws IllegalAccessException {
        //为sheet命名
        workbook.setSheetName(0, sheetName);
        //拿到属性数组
        Field[] fields = c.getDeclaredFields();
        //创建标题行
        getTitleRow(fields);
        for (int i = 0; i < list.size(); i++) {
            //创建行
            HSSFRow row1 = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                HSSFCell cell = row1.createCell(j);
                //打破封装
                fields[j].setAccessible(true);
                //拿到属性的值
                Object o1=list.get(i);
                Object o = fields[j].get(o1);
                //为单元格添加内容
                if (o instanceof Date) {
                    Date date = (Date) o;
                    String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    cell.setCellValue(format);
                } else if (o!=null){
                    cell.setCellValue(o.toString());
                }
            }
        }
        return workbook;
    }

    public static List importExcel(Class clasz, InputStream io) throws Exception {
        //获得Excel表格对象
        HSSFWorkbook wb = new HSSFWorkbook(io);
        HSSFSheet sheet = wb.getSheetAt(0);
        ArrayList list = new ArrayList();
        Field[] fields = clasz.getDeclaredFields();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            // 把row对象转换成对象
            HSSFRow row = sheet.getRow(i);
            Object object = clasz.newInstance();
            for (int j = 0; j < fields.length; j++) {
                HSSFCell cell = row.getCell(j);
                Field field = fields[j];
                field.setAccessible(true);
                //判断对象属性类型 如果类型为Integer类型转换为Integer类型赋值
                //如果不是Integer类型转换为其他string类型赋值
                if (field.getType() == Integer.class) {
                    field.set(object, new Integer(cell.toString()));
                } else if (field.getType() == Date.class) {
                    field.set(object, new SimpleDateFormat("yyyy-MM-dd").parse(cell.toString()));
                } else if (field.getType() == Double.class) {
                    field.set(object, new Double(cell.toString()));
                } else {
                    field.set(object, cell.toString());
                }
            }
            list.add(object);
        }
        return list;
    }
}
