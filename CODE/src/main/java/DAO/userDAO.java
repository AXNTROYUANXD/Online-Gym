package DAO;
/**
 *
 * Title: userDAO.java
 * Description: This interface is for user data access objects.
 * Copyright: Copyright (c) 2021
 * @since April 7 2021
 * @author Wangbo Li
 *
 */
public interface userDAO {
    public String[] getInfo(String userId);
    public String[] getMembershipPrivileges(String MembershipLevel);
    public void Save(String[] info);

}
