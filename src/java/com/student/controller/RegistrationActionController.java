package com.student.controller;

import java.sql.SQLException;

import com.constants.StudentConstants;
import com.student.beans.RegistrationBean;
import com.student.database.impl.RegisterDBImpl;
import com.student.manager.implementation.RegistrationBeanCreator;

public class RegistrationActionController {

    public String actionController(String type, String requestBody) {
        try {
            if (type.equals(StudentConstants.STUDENT_CREATE)) {
                RegistrationBeanCreator registrationBeanCreator = new RegistrationBeanCreator(
                        type, requestBody);
                RegistrationBean studentRegisterBean = registrationBeanCreator.createRegistrationBeanObject();
                RegisterDBImpl registerDBImpl = new RegisterDBImpl();
                registerDBImpl.createStudent(studentRegisterBean);
                return "<student>" + studentRegisterBean.getFirstName() + " Registered successfully!!!</student>";
            }
        } catch (Exception ex) {
            System.out.println("ex : " + ex);
        }
        return "<student>Creation failed</student>";

    }

}
