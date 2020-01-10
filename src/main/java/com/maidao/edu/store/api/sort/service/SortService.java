package com.maidao.edu.store.api.sort.service;

import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.sort.model.Sort;
import com.maidao.edu.store.api.sort.qo.SortQo;
import com.maidao.edu.store.api.sort.repository.ISortRepository;
import com.maidao.edu.store.common.cache.CacheOptions;
import com.maidao.edu.store.common.cache.KvCacheFactory;
import com.maidao.edu.store.common.cache.KvCacheWrap;
import com.maidao.edu.store.common.entity.Constants;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.ListModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

@Service
public class SortService implements ISortService {

    @Autowired
    private ISortRepository iSortRepository;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Byte, List<Sort>> sortCache;

    @Override
    public List<Sort> sorts(boolean adm) {
        return sortCache.findByKey(adm ? 0 : Constants.BSTATUS_OK);
    }

    @Override
    public void save(Sort sort) throws ServiceException {

        validateProductCategory(sort);

        Integer id = sort.getId();
        if (id == null || id == 0) {
            iSortRepository.save(sort);
            clearExamQuestionTypes();
        } else {
            Sort of = sort(id);
            of.setSequence(sort.getSequence());
            of.setName(sort.getName());
            of.setParentId(sort.getParentId());
            of.setPriority(sort.getPriority());
            iSortRepository.save(of);
            clearExamQuestionTypes();
        }
    }

    @Override
    public void status(int id, byte status) throws ServiceException {
        if (!(status == Constants.STATUS_OK || status == Constants.STATUS_HALT)) {
            throw new ServiceException(ERR_PERMISSION_DENIED);
        }
        Sort sort = sort(id);
        if (sort != null) {
            String sequence = sort.getSequence();
            if (sequence.endsWith("0000")) {
                sequence = sequence.substring(0, 2);
            } else if (sequence.endsWith("00")) {
                sequence = sequence.substring(0, 4);
            }
            iSortRepository.status(status, sequence);
            clearExamQuestionTypes();
        }
    }

    @Override
    @Transactional
    public void remove(int id) throws ServiceException {
        iSortRepository.deleteById(id);
        clearExamQuestionTypes();
    }

    @Override
    public Sort sort(int id) throws ServiceException {
        Sort type = iSortRepository.findById(id).get();
        if (type == null || type.getId() == null) {
            throw new ServiceException(ERR_PARENTID_VALID_DENIED);
        }
        return type;
    }

    private void clearExamQuestionTypes() {
        sortCache.remove((byte) 0);
        sortCache.remove(Constants.BSTATUS_OK);
    }

    private List<Sort> sortExamQuestionTypes(List<Sort> list) {

        List<Sort> ret = list.stream().filter((Sort t) -> t.getParentId() == 0).collect(Collectors.toList());
        for (Sort t1 : ret) {
            List<Sort> list2 = list.stream().filter((Sort t) -> t.getParentId().intValue() == t1.getId().intValue()).collect(Collectors.toList());
            for (Sort t2 : list2) {
                List<Sort> list3 = list.stream().filter((Sort t) -> t.getParentId().intValue() == t2.getId().intValue()).collect(Collectors.toList());
                t2.setChildren(list3);
            }
            t1.setChildren(list2);
        }
        return ret;
    }

    private void validateProductCategory(Sort sort) throws ServiceException {

        int parentId = sort.getParentId();
        if (StringUtils.isEmpty(sort.getName())) {
            throw new ServiceException(ERR_NAME_VALID_DENIED);
        }
        String sequence = sort.getSequence();
        if (parentId > 0) {
            Sort parent = sort(parentId);
            int _parentId = parent.getParentId();
            if (_parentId > 0) {
                Sort grandPa = sort(_parentId);
                System.out.println(parent.getSequence());
                System.out.println(grandPa.getSequence());
                if (!(sequence.substring(0, 2).equals(grandPa.getSequence().substring(0, 2))) && (sequence.substring(0, 4).equals(parent.getSequence().substring(0, 4)))) {
                    throw new ServiceException(ERR_PERMISSION_DENIED);
                }
            } else {
                if (!sequence.substring(0, 2).equals(parent.getSequence().substring(0, 2))) {
                    throw new ServiceException(ERR_PERMISSION_DENIED);
                }
            }
        }
        if (sort.getStatus() == 0) {
            sort.setStatus(Constants.BSTATUS_HALT);
        }
        if (sort.getPriority() == 0) {
            sort.setPriority((byte) 1);
        }
    }

    @Override
    public void codes2Ids(ProductQo qo) {

        List<String> codes = qo.getCodes();

        if (codes == null) {
            return;
        }

        if (codes.size() == 0) {
            return;
        }

        List<Sort> productCategories = sortCache.findByKey((byte) 0);

        List<Integer> ids = new ArrayList<>();

        for (Sort t1 : productCategories) {
            boolean with1 = codes.contains(t1.getSequence());
            if (with1 && !ids.contains(t1.getId())) {
                ids.add(t1.getId());
            }
            for (Sort t2 : t1.getChildren()) {
                boolean with2 = codes.contains(t2.getSequence());
                if ((with1 || with2) && !ids.contains(t2.getId())) {
                    ids.add(t2.getId());
                }
                for (Sort t3 : t2.getChildren()) {
                    boolean with3 = codes.contains(t3.getSequence());
                    if ((with1 || with2 || with3) && !ids.contains(t3.getId())) {
                        ids.add(t3.getId());
                    }
                }
            }
        }
        qo.setSortIds(ids);
    }

    @PostConstruct
    public void init() {

        sortCache = kvCacheFactory.create(new CacheOptions("product_category", 1, Constants.CACHE_REDIS_EXPIRE), new RepositoryProvider<Byte, List<Sort>>() {

            @Override
            public List<Sort> findByKey(Byte key) throws ServiceException {
                if (key == Constants.STATUS_OK) {
                    return sortExamQuestionTypes(iSortRepository.findAll(new SortQo()));
                } else {
                    return sortExamQuestionTypes(iSortRepository.findAll(new SortQo((byte) 0)));
                }
            }

            @Override
            public Map<Byte, List<Sort>> findByKeys(Collection<Byte> keys) throws ServiceException {
                throw new UnsupportedOperationException("keys");
            }

        }, new ListModelConverter<>(Sort.class));
    }

    @Override
    public void firstSortId2Ids(ProductQo qo) {

        List<Sort> productCategories = sortCache.findByKey((byte) 1);

        List<Integer> ids = new ArrayList<>();

        for (Sort t1 : productCategories) {
            if (t1.getId().intValue() == qo.getFirstSortId()) {
                for (Sort t2 : t1.getChildren()) {
                    for (Sort t3 : t2.getChildren()) {
                        ids.add(t3.getId());
                    }
                }
            }
        }
        qo.setSortIds(ids);
    }
}