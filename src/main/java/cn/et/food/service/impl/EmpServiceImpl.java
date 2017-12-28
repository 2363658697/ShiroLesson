package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.EmpMapper;
import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.entity.EmpExample.Criteria;
import cn.et.food.service.EmpService;
import cn.et.food.utils.PageTools;


@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	EmpMapper empMapper;

	
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#getEmpCount(cn.et.food.entity.EmpExample)
	 */
	public int getEmpCount(EmpExample example) {
		int total = (int) empMapper.countByExample(example);
		return total;
	}
	
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#queryFoodByFoodName(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public PageTools queryEmpByEname(String ename,Integer page,Integer rows,Integer deptno) {
		//PageTools pageTools=new PageTools(curPage, pageCount, totalCount);
		if(ename==null){
			ename="";
		}
		
		System.out.println("部门id："+deptno);
		
		EmpExample example=new EmpExample();
		
		Criteria criteria = example.createCriteria();
		//默认查所有的员工
		if(deptno!=null){
						
			criteria.andDeptnoEqualTo(deptno);
		}
			
		criteria.andEnameLike("%"+ename+"%");

		int total=getEmpCount(example);
		
		
		PageTools pageTools=new PageTools(page, rows, total);
		 
		RowBounds rowBounds=new RowBounds(pageTools.getStartIndex()-1,rows);
		
		
		List<Emp> foods = empMapper.selectByExampleWithRowbounds(example, rowBounds);

		System.out.println(foods.get(0).getJob()+foods.get(0).getEname());
		
		pageTools.setRows(foods);
		
		//自己写条件  FoodExample中方法addCriterion的权限改为public
		
		return pageTools;
	}

	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#saveEmp(cn.et.food.entity.Emp)
	 */
	public void saveEmp(Emp emp) {

		//foodMapper.insert(food); 该方法是插入表中所有的列
		
		empMapper.insertSelective(emp); //插入有数据的列
	}

	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#updateEmp(cn.et.food.entity.Emp)
	 */
	public void updateEmp(Emp emp) {
		
		//foodMapper.updateByExample(food, foodExample);  sql语句：修改所有的列
		
		empMapper.updateByPrimaryKeySelective(emp); //sql语句：修改插入数据的列	
	}


	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#deleteEmp(java.lang.String)
	 */
	public void deleteEmp(String empno) {
		String[] strings=empno.split(",");
		for(String id:strings){
			empMapper.deleteByPrimaryKey(Integer.valueOf(id));	
		}
	}

	
}
