package cn.et.food.service;


import cn.et.food.entity.Food;
import cn.et.food.entity.FoodExample;
import cn.et.food.utils.PageTools;

public interface FoodService {
	
	public int getFoodCount(FoodExample example);

	public abstract PageTools queryFoodByFoodName(String foodName,Integer page,Integer rows);
	
	
	public abstract void saveFood(Food food);

	public abstract void updateFood(Food food);
	
	public abstract void deleteFood(String foodId);
}