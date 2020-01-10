package com.maidao.edu.store.api.banner.service;

import com.maidao.edu.store.api.banner.model.Banner;
import com.maidao.edu.store.api.banner.qo.BannerQo;
import com.maidao.edu.store.api.banner.repository.IBannerRepository;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.maidao.edu.store.common.exception.ErrorCode.ERR_BANNER_IMG_NULL;

@Service
public class BannerService implements IBannerService {

    @Autowired
    private IBannerRepository bannerRepository;

    @Override
    public List<Banner> banners(BannerQo qo, boolean admin) {
        if (!admin) {
            qo.setStatus(1);
        }
        return bannerRepository.findAll(qo);
    }

    @Override
    public Banner banner(int id) {
        return bannerRepository.getOne(id);
    }

    @Override
    public void save(Banner banner) throws ServiceException {
        if (StringUtils.isEmpty(banner.getImg())) {
            throw new ServiceException(ERR_BANNER_IMG_NULL);
        }
        bannerRepository.save(banner);
    }

    @Override
    public void remove(int id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public void outSome(List<Integer> ids) {
        for (Integer id : ids) {
            Banner exist = bannerRepository.getOne(id);
            exist.setStatus(2);
            bannerRepository.save(exist);
        }
    }

    @Override
    public void putSome(List<Integer> ids) {
        for (Integer id : ids) {
            Banner exist = bannerRepository.getOne(id);
            exist.setStatus(1);
            bannerRepository.save(exist);
        }
    }
}
