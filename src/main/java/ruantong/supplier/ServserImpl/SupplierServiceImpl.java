package ruantong.supplier.ServserImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruantong.supplier.Bean.Supplier;
import ruantong.supplier.Dao.SupplierDao;
import ruantong.supplier.Servser.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierDao supplierDao;

	/**
	 * 查询所有供应商信息
	 */
	@Override
	public List<Supplier> selectAll(Integer currpage, Integer pageSize, String concent) {

		if (0 >= currpage) {
			currpage = 1;
		}

		Integer startIndex = (currpage-1)*pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);

		try {
			Long.valueOf(concent);
			params.put("supplierid", concent);
		} catch (Exception e) {
			params.put("supplylegaler", concent);
		}


		return supplierDao.selectAll(params);
	}

	/**
	 * 插入供应商信息
	 */
	@Override
	public boolean insert(Supplier supplier) {

		int random = (int) (Math.random()*100);
		String supplierid = (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		supplier.setSupplierid(supplierid+random);

		return supplierDao.insert(supplier);
	}

	/**
	 * 删除供应商信息
	 */
	@Override
	public boolean delete(String supplierid) {

		return supplierDao.delete(supplierid);
	}

	/**
	 * 修改供应商信息
	 */
	@Override
	public boolean update(Supplier supplier) {

		return supplierDao.update(supplier);
	}

	/**
	 * 统计记录条数
	 */
	@Override
	public Integer totalPage(Integer pageSize) {

		Integer countAll = supplierDao.countAll();

		Integer totalPage = (countAll%pageSize)==0?(countAll/pageSize):(countAll/pageSize)+1;

		return totalPage;
	}

}
