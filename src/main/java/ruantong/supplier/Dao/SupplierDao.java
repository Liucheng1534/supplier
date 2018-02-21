package ruantong.supplier.Dao;

import java.util.List;
import java.util.Map;

import ruantong.supplier.Bean.Supplier;

public interface SupplierDao {

	//查询所有供应商信息
	public List<Supplier> selectAll(Map<String, Object> params);

	//统计记录条数
	public Integer countAll();

	//插入供应商信息
	public boolean insert(Supplier supplier);

	//删除供应商信息
	public boolean delete(String supplierid);

	//修改供应商信息
	public boolean update(Supplier supplier);
}
