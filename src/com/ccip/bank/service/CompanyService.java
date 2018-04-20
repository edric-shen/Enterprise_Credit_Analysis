/**
 * 
 */
package com.ccip.bank.service;

import java.util.ArrayList;

import com.ccip.bank.model.Company;
import com.jfinal.plugin.activerecord.Page;

/**
 * @date 2018年3月26日 下午8:00:59 
 */
/**
 * @author Mason
 *
 */
public class CompanyService {

	
	private static final Company dao = new Company().dao();
	
	public Page<Company> paginate(int pageNumber, int pageSize, String key) {
		return dao.paginate(pageNumber, pageSize, "select *", 
				"from en_all_company where concat(IFNULL(cname,''),IFNULL(industry,''),IFNULL(registerAuth,'')) like ?","%"+key+"%");
//		return dao.paginate(pageNumber, pageSize, "select *", 
//				"from curya工商  where concat(IFNULL(公司名称,''),IFNULL(行业,''),IFNULL(start_time,''),IFNULL(登记机关,'')) like ?","%"+key+"%");
//
	}
	
	public Company findById(int id) {
		return dao.findById(id);
	}
	
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	//search company by  统一信用代码
	public Company getByCreditNum (String creditCode) {
		return  dao.findFirst("select * from en_all_company where code = ?" , creditCode);
	}
	
	//获取法律诉讼
	public Page<Company> paginats(int pageNumber, int pageSize,String code){
		return dao.paginate(pageNumber, pageSize,"select *","from 法律诉讼  where 统一信用代码 = ?" , code);
        
    	}
	//获取法院公告
		public Page<Company> paginat_notice(int pageNumber, int pageSize,String code){
			return dao.paginate(pageNumber, pageSize,"select *","from 法院公告  where 统一信用代码 = ?" , code);
	        
	    }
	//获取被执行人
	public Page<Company> paginat_preson(int pageNumber, int pageSize,String code){
		return dao.paginate(pageNumber, pageSize,"select *","from 被执行人  where 统一信用代码 = ?" , code);
			        
		}
	//获取开庭公告
		public Page<Company> paginat_session(int pageNumber, int pageSize,String code){
			return dao.paginate(pageNumber, pageSize,"select *","from 开庭公告  where 统一信用代码 = ?" , code);
			        
		}

 //知识产权数据
		//获取专利
		public Page<Company> paginat_zhuanli(int pageNumber, int pageSize,String code){
			return dao.paginate(pageNumber, pageSize,"select *","from 专利  where 统一信用代码 = ?" , code);
	        
	    	}
		//获取软件著作
			public Page<Company> paginat_soft(int pageNumber, int pageSize,String code){
				return dao.paginate(pageNumber, pageSize,"select *","from 软件著作权   where 统一信用代码 = ?" , code);
		        
		    }
		//获取作品著作
		public Page<Company> paginat_works(int pageNumber, int pageSize,String code){
			return dao.paginate(pageNumber, pageSize,"select *","from 作品著作权  where 统一信用代码 = ?" , code);
				        
			}
		//获取网站备案
			public Page<Company> paginat_web(int pageNumber, int pageSize,String code){
				return dao.paginate(pageNumber, pageSize,"select *","from 网站备案  where 统一信用代码 = ?" , code);
				        
			}
		//商标信息
			public Page<Company> paginat_brand(int pageNumber, int pageSize,String code){
				return dao.paginate(pageNumber, pageSize,"select *","from 商标信息  where 统一信用代码 = ?" , code);
				        
			}			
}

