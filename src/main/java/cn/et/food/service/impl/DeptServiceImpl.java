package cn.et.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.DeptMapper;
import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;
import cn.et.food.entity.DeptExample.Criteria;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	DeptMapper deptMapper;

	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.DeptService#queryFoodByFoodName(java.lang.Integer)
	 */
	public List<TreeNode> queryDept(Integer id) {
		if(id==null){
			id=0;
		}
		
		DeptExample example=new DeptExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(id);
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<Dept> list = deptMapper.selectByExample(example);
		for(Dept dept:list){
			TreeNode treeNode=new TreeNode();
			treeNode.setId(dept.getDeptno());
			treeNode.setText(dept.getDname());
			//判断该节点是否还存在子节点
			if(queryDept(dept.getDeptno()).size()==0){
				treeNode.setState("open");
			}
			nodes.add(treeNode);
		}
		return nodes;
	}

}
