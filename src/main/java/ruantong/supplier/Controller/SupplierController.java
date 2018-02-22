package ruantong.supplier.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import ruantong.supplier.Bean.Supplier;
import ruantong.supplier.Servser.SupplierService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/supplier")
public class SupplierController {

	//自动初始化类
	@Autowired
	SupplierService supplierService;

	/**
	 * 查询所有供应商信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public Map<String, Object> getAll(@RequestParam(value="currpage",defaultValue="1")
	String currpage,@RequestParam(value="pageSize",defaultValue="10")Integer pageSize,
	String concent){
		
		Integer currpage2 = Integer.valueOf(currpage);
		List<Supplier> suppliers = supplierService.selectAll(currpage2,pageSize,concent);
		Integer totalPage = supplierService.totalPage(pageSize);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", suppliers);
		result.put("totalPage", totalPage);
		result.put("size", supplierService.totalPage(1));
		
		return result;
	}

	/**
	 * 插入供应商信息
	 * @param supplier
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Boolean insert(Supplier supplier){
		
		Boolean judge = supplierService.insert(supplier);
		
		return judge;
	}

	/**
	 * 删除供应商信息
	 * @param supplierid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public Boolean delete(String supplierid){
		
		Boolean judge = supplierService.delete(supplierid);
		
		return judge;
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public Boolean update(Supplier supplier){
		Boolean judge = supplierService.update(supplier);
		
		return judge;
	}
	
	@RequestMapping(value="/test")
	public String test(){
		return "demo1-22";
	}

	/**
	 * 导出模板
	 * @return
	 */
	@RequestMapping("/export")
	public void export(String excelName, HttpServletResponse response){
		ArrayList list = new ArrayList();
		try {
			HSSFWorkbook export = supplierService.export(Supplier.class,list , "supliier");
			response.setHeader("Content-Disposition", "attachment;filename="+excelName);

			ServletOutputStream outputStream = response.getOutputStream();
			export.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/import")
	public void importExcel(MultipartFile upload, HttpSession session) throws Exception {
        String name = upload.getName();
        supplierService.imports(upload.getInputStream());
	}
	
}
