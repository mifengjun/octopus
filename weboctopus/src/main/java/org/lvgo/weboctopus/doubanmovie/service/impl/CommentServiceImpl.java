package org.lvgo.weboctopus.doubanmovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lvgo.weboctopus.doubanmovie.bean.Comment;
import org.lvgo.weboctopus.doubanmovie.mapper.CommentMapper;
import org.lvgo.weboctopus.doubanmovie.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
