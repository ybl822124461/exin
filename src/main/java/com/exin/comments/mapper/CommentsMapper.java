package com.exin.comments.mapper;

import com.exin.comments.entity.CommentsReply;
import com.exin.comments.entity.CommentsRoot;
import com.exin.comments.entity.Liked;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public abstract class CommentsMapper {
    /**
     * 获取该文章或资源下的所有评论
     * @param ownerId 文章或资源id
     * @return
     */
//    @Select("select * from comments_info where owner_id = #{ownerId}")
    public abstract List<CommentsRoot> findByOwnerId(String ownerId);

    /**
     * 添加子评论或回复评论
     * @param commentsReply
     * @return
     */
    @Insert("insert into comments_reply (id,comment_id,from_id,from_name,from_avatar,to_id,to_name,to_avatar,like_num,content,create_time) " +
            "values(#{id},#{commentId},#{fromId},#{fromName},#{fromAvatar},#{toId},#{toName},#{toAvatar},#{likeNum},#{content},#{createTime})")
    public abstract boolean addSonComments(CommentsReply commentsReply);

    /**
     * 添加父评论
     * @param commentsRoot
     * @return
     */
//    @Insert("insert into comments_root (id,comment_id,owner_id,type,from_id,from_name,from_avatar,like_num,content,create_time) " +
//            "values(#{id},#{commentId},#{ownerId},#{type},#{fromId},#{fromName},#{fromAvatar},#{likeNum},#{content},#{createTime})")
    public abstract boolean addRootComments(CommentsRoot commentsRoot);

    /**
     * 点赞
     * @param liked
     * @return
     */
    @Insert("insert into liked (obj_id,user_id,like_status) values(#{objId},#{userId},#{likeStatus})")
    public abstract boolean addLiked(Liked liked);

    /**
     * 点赞或取消点赞
     * @param liked
     * @return
     */
    @Insert("update liked set like_status = #{likeStatus} where user_id = #{userId} and obj_id = #{objId}")
    public abstract boolean updateLiked(Liked liked);

    /**
     * 查询单个用户的所有点赞信息
     * @param userId
     * @return
     */
    @Select("select * from liked where user_id = #{userId}")
    public abstract List<Liked> getListLiked(String userId);

    /**
     * 检测用户是否点赞了
     * @param liked
     * @return
     */
    @Select("select * from liked where user_id = #{userId} and obj_id = #{objId} ")
    public abstract Liked checkedLike(Liked liked);

    /**
     * 更新父表的点赞数
     * @param liked
     * @return
     */
    @Update("update comments_root set like_num = like_num + #{likeStatus} where comment_id = #{objId} and like_num + #{likeStatus} >= 0")
    public abstract boolean updateRootLikeNum(Liked liked);

    /**
     * 更新子评论点赞数
     * @param liked
     * @return
     */
    @Update("update comments_reply set like_num = like_num + #{likeStatus} where comment_id = #{objId} and like_num + #{likeStatus} >= 0")
    public abstract boolean updateReplyLikeNum(Liked liked);



}
