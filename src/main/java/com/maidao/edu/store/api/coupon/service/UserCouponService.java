package com.maidao.edu.store.api.coupon.service;

import com.maidao.edu.store.api.coupon.model.UserCoupon;
import com.maidao.edu.store.api.coupon.qo.UserCouponQo;
import com.maidao.edu.store.api.coupon.repository.CouponRepository;
import com.maidao.edu.store.api.coupon.repository.UserCouponRepository;
import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.service.UserService;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ArgumentServiceException;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.mail.MailHelper;
import com.maidao.edu.store.common.mail.MailService;
import com.maidao.edu.store.common.task.ApiTask;
import com.maidao.edu.store.common.task.TaskService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCouponService implements IUserCouponService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Override
    public void save(UserCoupon userCoupon) throws ServiceException {

        UserCoupon exist = userCouponRepository.findByCouponId(userCoupon.getCouponId());

        if (exist != null) {
            throw new ArgumentServiceException("已经领取过此优惠券");
        }

        userCoupon.setUserId(Contexts.requestUserId());

        userCoupon.setGetAt(System.currentTimeMillis());

        userCoupon.setExpirAt((couponRepository.getOne(userCoupon.getCouponId()).getDuration()) * 86400000 + System.currentTimeMillis());

        userCouponRepository.save(userCoupon);
    }

    @Override
    public void remove(Integer id) throws Exception {

        UserCoupon userCoupon = userCouponRepository.getOne(id);

        userCoupon.setStatus(2);

        userCouponRepository.save(userCoupon);
    }

    @Override
    public void used(Integer id) throws Exception {

        UserCoupon userCoupon = userCouponRepository.getOne(id);

        userCoupon.setStatus(3);

        userCouponRepository.save(userCoupon);
    }

    @Override
    public UserCoupon coupon(Integer id) {
        return userCouponRepository.getOne(id);
    }

    @Override
    public List<UserCoupon> userCoupons(UserCouponQo qo) {
        return userCouponRepository.findAll(qo);
    }

    @Override
    public void checkCoupon() throws Exception {

        long expiredAt = System.currentTimeMillis() + DateUtils.MILLIS_PER_DAY;

        List<UserCoupon> couponUsers = userCouponRepository.findByStatusAndExpirAtBefore(1, expiredAt);

        Map<Integer, List<UserCoupon>> map = new HashMap<>(couponUsers.size());

        for (UserCoupon couponUser : couponUsers) {
            System.out.println(couponUser.getId());

            Integer userId = couponUser.getUserId();

            if (map.containsKey(userId)) {
                List<UserCoupon> list = map.get(userId);
                list.add(couponUser);
                map.put(userId, list);
            } else {
                List<UserCoupon> list = new ArrayList<>(1);
                list.add(couponUser);
                map.put(userId, list);
            }

        }

        for (Integer userId : map.keySet()) {
            System.out.println(userId);
            List<UserCoupon> list = map.get(userId);
            for (UserCoupon cp : list) {
                System.out.println("--" + cp.getId());
            }
            User user = userService.findById(userId);
            taskService.addTask(new CouponEmail(user, list.size()));
        }
    }

    private class CouponEmail extends ApiTask {
        private User user;
        private int count;

        public CouponEmail(User user, int count) {
            super();
            this.user = user;
            this.count = count;
        }

        @Override
        protected void doApiWork() {
            MailHelper.MailInfo mail = new MailHelper.MailInfo();
            mail.setToAddress(user.getEmail());
            mail.setSubject("剑陈商城");
            mail.setContent("尊敬的用户：您有 " + count + " 张优惠券即将过期，请尽快使用!");
            mailService.send(mail);
        }
    }


}