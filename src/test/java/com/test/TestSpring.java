package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ruantong.supplier.Bean.Supplier;
import ruantong.supplier.Dao.SupplierDao;
import ruantong.supplier.Dao.UserDao;

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
	public void testData6() {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH) + 1);
		// ��ȡ��
		int year = calendar.get(Calendar.YEAR);

		// ��ȡ�£�������Ҫ��Ҫ�·ݵķ�ΧΪ0~11����˻�ȡ�·ݵ�ʱ����Ҫ+1���ǵ�ǰ�·�ֵ
		int month = calendar.get(Calendar.MONTH) + 1;

		// ��ȡ��
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// ��ȡʱ
		int hour = calendar.get(Calendar.HOUR);
		 int hour2 = calendar.get(Calendar.HOUR_OF_DAY); // 24Сʱ��ʾ

		// ��ȡ��
		int minute = calendar.get(Calendar.MINUTE);
		// ��ȡ��
		int second = calendar.get(Calendar.SECOND);
		
		int random = (int) (Math.random()*100);
		String s = year+""+month+""+day+""+hour+""+minute+""+second+""+random;
		String s2 = (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		System.out.println(s2+random);
	}
}
