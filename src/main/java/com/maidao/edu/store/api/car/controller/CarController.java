package com.maidao.edu.store.api.car.controller;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.api.car.service.CarService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/car")
public class CarController extends BaseController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String car) throws Exception {
        carService.save(parseModel(car, new Car()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        carService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/car")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView car(Integer id) throws Exception {
        return feedback(carService.car(id));
    }

    @RequestMapping(value = "/cars")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView cars() throws Exception {
        return feedback(carService.cars());
    }

    @RequestMapping(value = "/findbyids")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findByIds(String ids) throws Exception {
        return feedback(carService.findByIds(JSON.parseArray(ids, Integer.class)));
    }
}