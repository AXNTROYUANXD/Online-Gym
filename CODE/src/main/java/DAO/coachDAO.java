package DAO;
/**
 *
 * Title: coachDAO.java
 * Description: This interface is for coach data access objects.
 * Copyright: Copyright (c) 2021
 * @since May 1 2021
 * @author Wangbo Li
 *
 */

public interface coachDAO {
    public String[] getInfo(String coachId);
    public void editCoachInfo(String coachName, String address,String gender,String moblienum,String age,String mark,String email,String category,String description);
}
