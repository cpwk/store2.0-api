package com.maidao.edu.store.api.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivitySchedulerTask {
    @Autowired
    private IUserCouponService userCouponService;

    //@Scheduled(cron = "0 */1 * * * ?")
    //@Scheduled(cron = "0 0 7 ? * *")
    @Scheduled(cron = "0 0 10 * * ?")
    //@Scheduled(cron = "  0 */1 * * * ?")
    public void doTrack() throws Exception {
        //TODO 隔天执行
//        Calendar c = Calendar.getInstance();//获得当天0点时间
//        int week = c.get(Calendar.DAY_OF_WEEK) - 1;
        //Calendar中提供的DAY_OF_WEEK获取的一周也是以星期日作为一周的开始

        //if (c.get(Calendar.DAY_OF_MONTH) % 2 == 0) {
//        if (week == 1 || week == 3 || week == 5) {
        userCouponService.checkCoupon();
        // }
    }
}
