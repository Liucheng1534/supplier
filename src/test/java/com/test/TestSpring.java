package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.DataSource;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ruantong.supplier.Bean.Supplier;
import ruantong.supplier.Dao.SupplierDao;
import ruantong.supplier.Dao.UserDao;
import ruantong.supplier.Util.PoiUtil;

public class TestSpring {

	ClassPathXmlApplicationContext ctx = null;
	DataSource dataSource = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// dataSource = (DataSource) ctx.getBean("pooledDataSource");
		dataSource = (DataSource) ctx.getBean("pooledDataSource");
	}

	@Test
	public void testData() {
		System.out.println(dataSource);
	}
	
	@Test
    public void sqlSessionFactoryTest() {
    	UserDao userMapper = (UserDao) ctx.getBean("userDao");
//    	System.out.println(userMapper.selectUser("zhang"));
    	/*User user = new User(null, "li", "123456", "66666","666");
    	System.out.println(userMapper.insert(user));*/
    	System.out.println(userMapper.select("li"));
    }

    @Test
    public void userMapperTest() {
    	SimpleDateFormat format = new SimpleDateFormat();
    	
    	System.out.println(format.format(new Date()));
    }
	
	@Test
	public void testData7() {
		SupplierDao supplierDao = (SupplierDao) ctx.getBean("supplierDao");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startIndex", 0);
		params.put("pageSize", 10);
//		params.put("supplierid", "20180204");
		params.put("supplylegaler", "����");
		System.out.println(supplierDao.selectAll(params).size());
	}
	//20180204
	/*@Test
	public void testData2() {
		Map<String, Integer> params = new HashMap<>();
		params.put("startIndex", 0);
		params.put("pageSize", 10);
		SupplierDao supplierDao = (SupplierDao) ctx.getBean("supplierDao");
		System.out.println(supplierDao.selectAll(params));
		System.out.println(supplierDao.countAll());
	}*/

	@Test
	public void testData3() {
		Supplier supplier2 = new Supplier("3566", "3566", "3566", "3566", "3566", "3566", "3566", "3566");
		Supplier supplier = new Supplier("3566", "����", "�Ŷ�", "����", "15711233322", "3566", "1533", null);
		SupplierDao supplierDao = (SupplierDao) ctx.getBean("supplierDao");
		System.out.println(supplierDao.insert(supplier));
	}

	@Test
	public void testData4() {
		SupplierDao supplierDao = (SupplierDao) ctx.getBean("supplierDao");
		System.out.println(supplierDao.delete("11"));
	}

	@Test
	public void testData5() {
		Supplier supplier = new Supplier("3566", "����1", "�Ŷ�2", "����3", "15711233323", "3567", "1535", null);
		SupplierDao supplierDao = (SupplierDao) ctx.getBean("supplierDao");
		System.out.println(supplierDao.update(supplier));
	}

	@Test
	public void testData6() throws Exception {
		PoiUtil poiUtil = new PoiUtil();
        HSSFWorkbook workbook= null;
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        Supplier supplier = new Supplier();
        supplier.setBusinesser("1");
        supplier.setLicenseid("2");
        supplier.setPhonenumber("3");
        supplier.setRegistertime("4");
        supplier.setRevenueid("5");
        supplier.setSupplierid("6");
        supplier.setSuppliername("7");
        suppliers.add(supplier);
        try {
            workbook = poiUtil.getWorkbook(Supplier.class, suppliers, "test");
        } catch (IllegalAccessException e) {
			e.printStackTrace();
		}
//将表格输出
        FileOutputStream outputStream = new FileOutputStream(new File("d://供应商表格.xls"));
        workbook.write(outputStream);
        outputStream.close();
	}
}
