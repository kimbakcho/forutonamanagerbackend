package com.management.forutona.AuthDto;

public class Userinfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column UserInfo.uid
     *
     * @mbg.generated
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column UserInfo.NickName
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column UserInfo.Password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column UserInfo.Role
     *
     * @mbg.generated
     */
    private String role;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column UserInfo.uid
     *
     * @return the value of UserInfo.uid
     *
     * @mbg.generated
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column UserInfo.uid
     *
     * @param uid the value for UserInfo.uid
     *
     * @mbg.generated
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column UserInfo.NickName
     *
     * @return the value of UserInfo.NickName
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column UserInfo.NickName
     *
     * @param nickname the value for UserInfo.NickName
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column UserInfo.Password
     *
     * @return the value of UserInfo.Password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column UserInfo.Password
     *
     * @param password the value for UserInfo.Password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column UserInfo.Role
     *
     * @return the value of UserInfo.Role
     *
     * @mbg.generated
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column UserInfo.Role
     *
     * @param role the value for UserInfo.Role
     *
     * @mbg.generated
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }


    String accesstoken;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}