package com.management.forutona.AuthDao;

import com.management.forutona.AuthDto.Userinfo;
import java.util.List;

public interface UserinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated
     */
    int insert(Userinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated
     */
    Userinfo selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated
     */
    List<Userinfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Userinfo record);
}