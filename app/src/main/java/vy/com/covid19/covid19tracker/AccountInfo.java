/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The AccountInfo file where it handle AccountInfo class
 */
package vy.com.covid19.covid19tracker;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountInfo implements Parcelable {
    private int accountID;
    private String username;
    private String password;
    private String gender;
    private String DOB;
    private String State;
    private String FamilyMember;

    public AccountInfo(int tmpID, String tmpUser, String tmpPass, String tmpGender, String tmpDOB, String tmpState, String tmpFM )
    {
        setAccountID(tmpID);
        setState(tmpState);
        setUsername(tmpUser);
        setPassword(tmpPass);
        setGender(tmpGender);
        setDOB(tmpDOB);
        setFamilyMember(tmpFM);
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFamilyMember() {
        return FamilyMember;
    }

    public void setFamilyMember(String familyMember) {
        FamilyMember = familyMember;
    }

    protected AccountInfo(Parcel in) {
        accountID = in.readInt();
        username = in.readString();
        password = in.readString();
        gender = in.readString();
        DOB = in.readString();
        State = in.readString();
        FamilyMember = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(accountID);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(gender);
        dest.writeString(DOB);
        dest.writeString(State);
        dest.writeString(FamilyMember);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AccountInfo> CREATOR = new Parcelable.Creator<AccountInfo>() {
        @Override
        public AccountInfo createFromParcel(Parcel in) {
            return new AccountInfo(in);
        }

        @Override
        public AccountInfo[] newArray(int size) {
            return new AccountInfo[size];
        }
    };
}