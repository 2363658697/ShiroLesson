package cn.et.food.service;

import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.utils.PageTools;

public interface EmpService {

	public abstract int getEmpCount(EmpExample example);

	public abstract PageTools queryEmpByEname(String ename, Integer page,
			Integer rows,Integer deptno);

	public abstract void saveEmp(Emp emp);

	public abstract void updateEmp(Emp emp);

	public abstract void deleteEmp(String empno);

}