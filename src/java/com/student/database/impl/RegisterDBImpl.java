package com.student.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.constants.SQLQueryConstants;

import com.entity.beans.RegistrationBean;
import com.student.db.connection.manager.MySQLConnectionCreator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDBImpl {

    Connection connection;

    public void createStaffOrStudent(RegistrationBean registrationBean) throws SQLException {
        connection = MySQLConnectionCreator.getDBConnection();
        PreparedStatement preparedStmt = null;
        preparedStmt = createPreparedStatement(preparedStmt, registrationBean);

        // create the mysql insert preparedstatement
        try {
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                try {
                    if (preparedStmt != null) {
                        preparedStmt.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private PreparedStatement createPreparedStatement(PreparedStatement preparedStmt, RegistrationBean registrationBean) {
        try {
            preparedStmt = connection.prepareStatement(registrationBean.isIsStaff() ? SQLQueryConstants.STAFF_REGISTRATION_CREATE : SQLQueryConstants.STUDENT_REGISTRATION_CREATE);
            preparedStmt.setString(1, registrationBean.getFirstName());
            preparedStmt.setString(2, registrationBean.getLastName());
            preparedStmt.setString(3, registrationBean.getMiddleName());
            preparedStmt.setInt(4, registrationBean.getAge());
            preparedStmt.setDate(5, new java.sql.Date(registrationBean.getDob().getTime()));
            preparedStmt.setString(6, registrationBean.getFatherName());
            preparedStmt.setString(7, registrationBean.getMotherName());
            preparedStmt.setString(8, registrationBean.getMobileNumber());
            preparedStmt.setString(9, registrationBean.getAddress());
            preparedStmt.setString(10, registrationBean.getPincode());
            preparedStmt.setString(11, registrationBean.getEmailiId());
            preparedStmt.setString(12, registrationBean.getPassword());
            if (registrationBean.isIsStaff()) {
                preparedStmt.setString(13, registrationBean.getSpouseName());
                preparedStmt.setBoolean(14, registrationBean.isIsTeachingStaff());
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preparedStmt;
    }

}
