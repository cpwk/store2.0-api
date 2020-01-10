package com.maidao.edu.store.api.comment.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.qo.CommentQo;
import com.maidao.edu.store.api.comment.service.CommentService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "adm/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    @RequestMapping(value = "/save")
    public ModelAndView save(String comment) throws Exception {
        commentService.save(parseModel(comment, new Comment()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.NONE, adminPermission = AdminPermission.COMMENT_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        commentService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/comment")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    public ModelAndView comment(Integer id) throws Exception {
        return feedback(commentService.findComment(id));
    }

    @RequestMapping(value = "/comments")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    public ModelAndView comments(String commentQo) throws Exception {
        return feedback(commentService.comments(parseModel(commentQo, new CommentQo())));
    }
}
