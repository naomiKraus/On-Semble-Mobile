package com.onsemble.onsemble;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable{
    public int id;
    public boolean isActive;
    public int nodeEntityId;
    public int activityId;
    public String activityName;
    public int departmentId;
    public boolean disableActivities;
    public int companyId;
    public int nodeDepartmentId;
    public ArrayList<Action> actions;
    public ArrayList<Activity> activities;
    public boolean workingStatus;
    public String firstName;
    public String lastName;
    public String image;

}
