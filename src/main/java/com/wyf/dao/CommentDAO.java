package com.wyf.dao;


import com.wyf.model.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by w7397 on 2017/3/20.
 */
@Mapper
public interface CommentDAO {

	String TABLE_NAME = " comment ";
	String INSERT_FIELDS = " user_id, content, created_date, entity_id, entity_type, status ";
	String SELECT_FIELDS = " id, " + INSERT_FIELDS;

	@Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{userId},#{content},#{createDate},#{entityId},#{entityType},#{status})"})
	int addComment(Comment comment);

	@Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId} order by id desc "})
	List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

	@Select({"select count(id) from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId}"})
	int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}
