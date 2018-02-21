package ruantong.supplier.Servser;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ruantong.supplier.Bean.Supplier;

public interface SupplierService {

	//查询所有供应商信息
	public List<Supplier> selectAll(Integer currpage,Integer pageSize,String concent);

	//统计记录条数
	public Integer totalPage(Integer pageSize);

	//插入供应商信息
	public boolean insert(Supplier supplier);

	//删除供应商信息
	public boolean delete(String supplierid);

	//修改供应商信息
	public boolean update(Supplier supplier);

	//导出表格模板
	public HSSFWorkbook export(Class c, List<Supplier> list, String sheetName);
}
