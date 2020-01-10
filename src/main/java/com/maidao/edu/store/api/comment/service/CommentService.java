package com.maidao.edu.store.api.comment.service;

import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.qo.CommentQo;
import com.maidao.edu.store.api.comment.repository.CommentRepository;
import com.maidao.edu.store.api.orders.model.Orders;
import com.maidao.edu.store.api.orders.repository.OrdersRepository;
import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.repository.UserRepository;
import com.maidao.edu.store.common.context.Contexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void remove(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void save(Comment comment) {

        Integer userId = Contexts.requestUserId();
        comment.setUserId(userId);

        Orders order = ordersRepository.findById(comment.getOrdersId()).get();
        order.setStatus(4);

        comment.setCreateAt(System.currentTimeMillis());

        commentRepository.save(comment);
    }

    @Override
    public Comment findComment(Integer id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Page<Comment> comments(CommentQo qo) {
        Page<Comment> comments = commentRepository.findAll(qo);
        for (Comment comment : comments) {
            Orders order = ordersRepository.getOne(comment.getOrdersId());
            User user = userRepository.getOne(comment.getUserId());
            comment.setOrders(order);
            comment.setUser(user);
        }
        return comments;
    }
}
