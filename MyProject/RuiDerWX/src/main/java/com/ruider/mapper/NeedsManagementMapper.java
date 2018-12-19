package com.ruider.mapper;

import com.ruider.model.NeedsManagement;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by mahede on 2018/12/2.
 */
@Mapper
public interface NeedsManagementMapper {

    @Select("select * from `needsManage` where `userId` = #{userId}")
    List<NeedsManagement> getNeedsByUserId(int userId);

    @Select("select * from `needsManage` where `userId` = #{userId} AND `categoryId` = #{categoryId}")
    List<NeedsManagement> getNeedsByUserIdAndCategoryId(@Param("userId") int userId,@Param("categoryId") int categoryId);

    @Insert("Insert into `needsManage`(`categoryId`, `userId`, `title`, `content`, `startTime`, `deadline`,`qq`, `weChat`, `phoneNo`, `limitNo`, `createTime`, `updateTime`) values(#{categoryId}, #{userId}, #{title}, #{content}, #{startTime}, #{deadline}, #{qq}, #{weChat}, #{phoneNo}, #{limitNo}, #{createTime}, #{updateTime})")
    int addNeeds(NeedsManagement needsManagement);

    @Delete("delete from `needsManage` where `id` = #{id}")
    int deleteNeeds(@Param("id") int id);

    @Select("select * from `needsManage` where `id` = #{id}")
    NeedsManagement getNeedsDetailsById(int id);

    @Update("update `needsManage` set `categoryId` = #{categoryId}, `userId`= #{userId}, `title`= #{title}, `content`= #{content}, `startTime`= #{startTime}, `deadline`= #{deadline},`qq`= #{qq}, `weChat`= #{weChat}, `phoneNo`= #{phoneNo}, `limitNo`= #{limitNo}, `updateTime`= #{updateTime}  where `id` = #{id}")
    int editNeeds(NeedsManagement needsManagement);
}
