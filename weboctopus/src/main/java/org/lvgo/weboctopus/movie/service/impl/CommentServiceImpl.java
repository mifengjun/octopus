package org.lvgo.weboctopus.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lvgo.weboctopus.movie.entity.Comment;
import org.lvgo.weboctopus.movie.mapper.CommentMapper;
import org.lvgo.weboctopus.movie.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
