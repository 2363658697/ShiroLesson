package cn.et.food.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.entity.Result;

import cn.et.food.service.EmpService;
import cn.et.food.utils.PageTools;

@Controller
public class EmpController {

	@Autowired
	EmpService empService;

	@ResponseBody
	@RequestMapping(value = "/queryEmp", method = RequestMethod.GET)
	public PageTools queryEmpByEname(String ename, Integer page, Integer rows,Integer id)
			throws Exception {
		PageTools ePageTools = empService.queryEmpByEname(ename, page, rows,id);

		return ePageTools;
	}
	
	@ResponseBody
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public Result saveEmp(Emp emp) throws Exception {

		Result result = new Result();
		result.setCode(0);

		try {
			empService.saveEmp(emp);
		} catch (Exception e) {
			result.setCode(1);
			result.setMessage(e);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/emp/{empno}", method = RequestMethod.PUT)
	public Result updateEmp(@PathVariable Integer empno, Emp emp)
			throws Exception {

		emp.setEmpno(empno);

		Result result = new Result();
		result.setCode(0);

		try {
			empService.updateEmp(emp);
		} catch (Exception e) {
			result.setCode(1);
			result.setMessage(e);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/emp/{empno}", method = RequestMethod.DELETE)
	public Result deleteEmp(@PathVariable String empno) throws Exception {
		Result result = new Result();
		result.setCode(0);

		try {
			empService.deleteEmp(empno);
		} catch (Exception e) {
			result.setCode(1);
			result.setMessage(e);
		}
		return result;
	}
}
