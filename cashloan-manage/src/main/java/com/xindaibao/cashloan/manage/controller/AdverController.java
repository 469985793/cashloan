package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Adver;
import com.xindaibao.cashloan.cl.file.FileHelper;
import com.xindaibao.cashloan.cl.model.UploadFileRes;
import com.xindaibao.cashloan.cl.service.AdverService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;

/**
 * 
 * @Description 广告管理
 * @author
 * @CreatTime 2017年6月21日 下午2:45:16
 * @since version 1.0.0
 */
@Controller
@Scope("prototype")
public class AdverController extends ManageBaseController {
	
	@Resource
	private AdverService adverService;

	/**
	 * 查询广告配置列表
	 * 
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/adver/page.htm")
	public void page(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "current") int current, @RequestParam(value = "pageSize") int pageSize)
			throws ServiceException {
		Map<String, Object> params = JsonUtil.parse(search, Map.class);
		String serverHost = Global.getValue("manage_host");
		Page<Adver> list = adverService.page(current, pageSize, params);
		Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		//设置分页参数
		page.setPageNum(list.getPageNum());
		page.setPageSize(list.getPageSize());
		page.setPageSizeZero(list.getPageSizeZero());
		page.setReasonable(list.getReasonable());
		page.setTotal(list.getTotal());
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = JsonUtil.domainToMap(list.get(i));
			String path = (String) map.get("path");
			map.put("viewPath", path != null ? serverHost + "/readFile.htm?path=" + path : "");
			page.add(map);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/modules/manage/adver/detail.htm")
	public void detail(@RequestParam(value = "id", required = true) Long id) throws ServiceException {

		Adver adver = adverService.getById(id);
		Map<String, Object> result = new HashMap<String, Object>();
		if (adver != null) {
			result.put(Constant.RESPONSE_DATA, adver);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 新增广告配置
	 * 
	 * @param title
	 * @param file
	 * @param sort
	 * @param state
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/modules/manage/adver/save.htm", method = RequestMethod.POST)
	public void save(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "bannerFile", required = true) MultipartFile file,
			@RequestParam(value = "sort", required = true) Long sort,
			@RequestParam(value = "state", required = true) String state) throws ServiceException {

		UploadFileRes model = FileHelper.uploadImg(file, "", "banner");
		int count = 0;
		if (!StringUtil.isBlank(model.getResPath())) {
			Adver adver = new Adver();
			adver.setTitle(title);
			adver.setLink(link);
			adver.setPath(model.getResPath());
			adver.setSort(sort);
			adver.setState(state);
		 count=adverService.insert(adver);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (count > 0L && !StringUtil.isBlank(model.getResPath())) { // 文件上传成功且保存成功
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL + model.getErrorMsg());
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 修改广告配置
	 * 
	 * @param id
	 * @param title
	 * @param file
	 * @param sort
	 * @param state
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/modules/manage/adver/update.htm", method = RequestMethod.POST)
	public void update(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "bannerFile", required = false) MultipartFile file,
			@RequestParam(value = "sort", required = false) Long sort,
			@RequestParam(value = "state", required = false) String state) throws ServiceException {
		Adver adver = adverService.getById(id);

		boolean flag = false;
		String error = "";
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != adver) {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			params.put("title", title);
			params.put("link", link);
			params.put("sort", sort);
			params.put("state", state);

			if (file != null) {
				UploadFileRes model = FileHelper.uploadImg(file, "adver_", "banner");
				error = model.getErrorMsg();
				if (!StringUtil.isBlank(model.getResPath())) {
					params.put("path", model.getResPath());
					flag = adverService.updateSelective(params);
				}
			} else {
				flag = adverService.updateSelective(params);
			}
			if (flag) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			} else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL + error);
			}
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 启用/禁用
	 * 
	 * @param id
	 * @param title
	 * @param file
	 * @param sort
	 * @param state
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/modules/manage/adver/onOrOff.htm", method = RequestMethod.POST)
	public void onOrOff(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "state", required = true) String state) throws ServiceException {
		Adver adver = adverService.getById(id);
		boolean flag = false;
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != adver) {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			params.put("state", state);

			flag = adverService.updateSelective(params);
			if (flag) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			} else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
			}
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}
}
