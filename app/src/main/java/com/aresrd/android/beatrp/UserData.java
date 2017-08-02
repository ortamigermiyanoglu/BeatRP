package com.aresrd.android.beatrp;

import java.util.List;

/**
 * Created by Ramadan on 26.12.2016.
 */

public class UserData {
    public String Token;
    public Boolean IsSuccess;
    public String ErrorMessage;
    public long CompanyId;
    public String DefaultLanguageCode;
    public String EMail;
    public long Id;
    public String LastLoginDateDay;
    public String LastLoginDateMonth;
    public String LastLoginDateYear;
    public String Name;
    public String NameSurname;
    public String Password;
    public String ProfileImageFileName;
    public int RecordStatus;
    public String Surname;
    public String UserName;
    public int UserType;
    public List<Branch> Branches;
    public List<Role> Roles;
    public List<Authority> Authorities;
    public List<Setting> UserSettings;
    public List<Setting> BranchSettings;
    public List<Setting> CompanySettings;
    public long CurrentBranchId;
}
