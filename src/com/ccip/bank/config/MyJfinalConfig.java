package com.ccip.bank.config;

import com.ccip.bank.model.Company;
import com.ccip.bank.model.InvestFactor;
import com.ccip.bank.model.InvestPotential;
import com.ccip.bank.model.Market;
import com.ccip.bank.model.User;
import com.ccip.bank.routes.AdminRoutes;
import com.ccip.bank.routes.FrontRoutes;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class MyJfinalConfig extends JFinalConfig {
	public void configConstant(Constants me) {
		String paths = System.getProperty("user.dir"); //获取项目路径		
		PropKit.use("a_little_config");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setBaseUploadPath(paths + "/upload"); // 上传文件接收路径：项目所在文件盘upload下，也可自行修改
		//me.setBaseDownloadPath(paths + "/download");//配置信用评级模板文件下载路径
	}

	@Override
	public void configRoute(Routes me) {
		// 配置前端路由
		me.add(new FrontRoutes());
		// 配置后端路由
		me.add(new AdminRoutes());
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
				PropKit.get("password").trim());
	}

	public void configPlugin(Plugins me) {

		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.addMapping("en_user", "id", User.class); // 映射到用户表
		// 行业动态模型数据库表映射
		arp.addMapping("en_market", "id", Market.class); // 映射行业动态分布核心数据表
		arp.addMapping("en_hc_yj", "id", Market.class); // 映射合成指数分布核心数据表
		arp.addMapping("en_diffusion_index", "id", Market.class); // 映射扩散指数核心数据表
		arp.addMapping("en_all_company", "id", Company.class); // 映射公司信息数据表
		arp.addMapping("en_market_city", InvestPotential.class); // 映射投资潜力城市表
		arp.addMapping("en_market_city_factor", InvestFactor.class); // 映射各城市投资潜力因子
		// arp.addMapping("en_info_financial", Market.class); //映射制造业表
		// arp.addMapping("en_manufacture_financial", Market.class); //映射信息技术业表
		// 企业风险数据库表映射
		arp.addMapping("法律诉讼", "id", Company.class); // 映射公司法律诉讼数据表
		arp.addMapping("被执行人", "id", Company.class); // 映射公司被执行人数据表
		arp.addMapping("法院公告", "id", Company.class); // 映射公司法院公告数据表
		arp.addMapping("开庭公告", "id", Company.class); // 映射公司开庭公告数据表
		arp.addMapping("变更信息", "id", Company.class); // 映射公司变更信息数据表
		// 知识产权数据库表映射
		arp.addMapping("网站备案", "id", Company.class);
		arp.addMapping("作品著作权", "id", Company.class);
		arp.addMapping("软件著作权", "id", Company.class);
		arp.addMapping("商标信息", "id", Company.class);
		arp.addMapping("专利", "id", Company.class);
		// 财务数据表
		arp.addMapping("利润表", "year", Company.class);		
		// 新闻数据表
		arp.addMapping("en_news", "id", Company.class);		
		me.add(arp);
	}

	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor(true));
	}

	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctx_path"));
		me.add(new ContextPathHandler("basePath"));

	}

	@Override
	public void configEngine(Engine me) {
		me.setDevMode(true);
		me.addSharedFunction("/company/common/_menu.html");
		me.addSharedFunction("/company/common/predict_menu.html");
		me.addSharedFunction("/company/common/_paginate.html");
		me.addSharedFunction("/company/common/paginateContent.html");
	}
}
