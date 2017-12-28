package cn.et.food.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.food.entity.Food;
import cn.et.food.entity.Result;
import cn.et.food.service.FoodService;
import cn.et.food.utils.PageTools;

@Controller
public class FoodController {

	@Autowired
	FoodService foodService;

	@ResponseBody
	@RequestMapping(value = "/queryAFoodList", method = RequestMethod.GET)
	public PageTools queryFoodList(String foodName, Integer page, Integer rows)
			throws Exception {
		PageTools queryFoodList = foodService.queryFoodByFoodName(foodName,
				page, rows);

		return queryFoodList;
	}

	@ResponseBody
	@RequestMapping(value = "/food", method = RequestMethod.POST)
	public Result saveFood(Food food, MultipartFile multifile) throws Exception {

		Result result = new Result();
		result.setCode(0);

		try {
			foodService.saveFood(food);

			// 获取上传文件的文件名
			String path = multifile.getOriginalFilename();

			String absPath = "D:/images";
			// 保存文件
			multifile.transferTo(new File(absPath + "/" + path));
		} catch (Exception e) {
			result.setCode(1);
			result.setMessage(e);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/food/{foodId}", method = RequestMethod.PUT)
	public Result updateFood(@PathVariable Integer foodId, Food food)
			throws Exception {

		food.setFoodid(foodId);

		Result result = new Result();
		result.setCode(0);

		try {
			foodService.updateFood(food);
		} catch (Exception e) {
			result.setCode(1);
			result.setMessage(e);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/food/{foodId}", method = RequestMethod.DELETE)
	public Result deleteFood(@PathVariable String foodId) throws Exception {
		Result result = new Result();
		result.setCode(0);

		try {
			foodService.deleteFood(foodId);
		} catch (Exception e) {
			result.setCode(1);
			result.setMessage(e);
		}
		return result;
	}
}
