package ruantong.supplier.Util;

import javax.servlet.http.HttpSession;

public class FileUploadAndDownLoadUtils {
   public static String getRealPath(HttpSession session, String relativePath){
	   return session.getServletContext().getRealPath(relativePath) ;  
	}
}
