package com.student.controller;

import java.sql.SQLException;

import com.constants.ProjectConstants;
import com.entity.beans.RegistrationBean;
import com.student.database.impl.RegisterDBImpl;
import com.student.manager.implementation.RegistrationBeanCreator;

public class RegistrationActionController {
// This is to create the Student as well as Staff record creation
    public String actionController(String type, String requestBody) {
        try {
            if (type.equals(ProjectConstants.STUDENT_CREATE)) {
                RegistrationBeanCreator registrationBeanCreator = new RegistrationBeanCreator(
                        type, requestBody);
                RegistrationBean registerBean = registrationBeanCreator.createRegistrationBeanObject();
                RegisterDBImpl registerDBImpl = new RegisterDBImpl();
                registerDBImpl.createStaffOrStudent(registerBean);
                return "<student>" + registerBean.getFirstName() + " Registered successfully!!!</student>";
            }
        } catch (Exception ex) {
            System.out.println("ex : " + ex);
        }
        return "<student>Creation failed</student>";

    }

}
