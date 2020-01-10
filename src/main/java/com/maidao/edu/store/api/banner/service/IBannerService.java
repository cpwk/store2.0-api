package com.maidao.edu.store.api.banner.service;


import com.maidao.edu.store.api.banner.model.Banner;
import com.maidao.edu.store.api.banner.qo.BannerQo;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

public interface IBannerService {

    List<Banner> banners(BannerQo qo, boolean admin);

    Banner banner(int id);

    void save(Banner banner) throws ServiceException;

    void remove(int id);

    void outSome(List<Integer> ids);

    void putSome(List<Integer> ids);
}
