/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.constants;

/**
 *
 * @author Surendhar
 */
public class SQLQueryConstants {

    public static final String STUDENT_REGISTRATION_CREATE = " insert into student_registration_data (FirstName, LastName, MiddleName,"
            + " Age, DOB, FatherName, MotherName, MobileNumber, ResidentialAddress, pincode, "
            + "emailid, Userpassword)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String STAFF_REGISTRATION_CREATE = " insert into staff_registration_data (FirstName, LastName, MiddleName,"
            + " Age, DOB, FatherName, MotherName, MobileNumber, ResidentialAddress, pincode, "
            + "emailid, Userpassword, SpouseName, isTeaching)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

}
