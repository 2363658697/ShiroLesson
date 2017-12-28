package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.FoodMapper;
import cn.et.food.entity.Food;
import cn.et.food.entity.FoodExample;
import cn.et.food.entity.FoodExample.Criteria;
import cn.et.food.service.FoodService;
import cn.et.food.utils.PageTools;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	FoodMapper foodMapper;

	
	public int getFoodCount(FoodExample example) {
		int total = (int) foodMapper.countByExample(example);
		return total;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.FoodService#queryFoodByFoodName(java.lang.String)
	 */
	public PageTools queryFoodByFoodName(String foodName,Integer page,Integer rows) {
		//PageTools pageTools=new PageTools(curPage, pageCount, totalCount);
		if(foodName==null){
			foodName="";
		}
		
		FoodExample example=new FoodExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andFoodnameLike("%"+foodName+"%");
				
		
		int total=getFoodCount(example);
		
		
		PageTools pageTools=new PageTools(page, rows, total);
		 
		RowBounds rowBounds=new RowBounds(pageTools.getStartIndex()-1,rows);
		
		
		List<Food> foods = foodMapper.selectByExampleWithRowbounds(example, rowBounds);
		
		pageTools.setRows(foods);
		
		//自己写条件  FoodExample中方法addCriterion的权限改为public
		
		return pageTools;
	}



	public void saveFood(Food food) {

		
		//foodMapper.insert(food); 该方法是插入表中所有的列
		
		foodMapper.insertSelective(food); //插入有数据的列
	}


	public void updateFood(Food food) {
		
		//foodMapper.updateByExample(food, foodExample);  sql语句：修改所有的列
		
		foodMapper.updateByPrimaryKeySelective(food); //sql语句：修改插入数据的列	
	}


	public void deleteFood(String foodId) {
		String[] strings=foodId.split(",");
		for(String id:strings){
			foodMapper.deleteByPrimaryKey(Integer.valueOf(id));	
		}

	}

}
