package com.student.manager.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.constants.StudentConstants;
import com.student.beans.RegistrationBean;
import java.io.StringReader;
import org.xml.sax.InputSource;

public class RegistrationBeanCreator {

    RegistrationBean studentRegisterBean;
    public String type;
    public String body;

    public static void main(String[] args) {
        String type = "register";
        String inputXMLBody = "<?xml version=\"1.0\"?>\n"
                + "   <student>\n"
                + "      <firstname>Surendhar</firstname>\n"
                + "      <lastname>Muthumani</lastname>\n"
                + "      <middlename>jazz</middlename>\n"
                + "      <age>24</age>\n"
                + "      <dob>15/03/1994</dob>\n"
                + "      <father>Muthumani</father>\n"
                + "      <mother>Mani</mother>\n"
                + "      <mobilenumber>9025844134</mobilenumber>\n"
                + "      <address>Namakkal</address>\n"
                + "      <pincode>637402</pincode>\n"
                + "      <emailid>surendharmuthumani@gmail.com</emailid>\n"
                + "      <password>corent123$$</password>\n"
                + "   </student>";
        RegistrationBeanCreator ss = new RegistrationBeanCreator(type, inputXMLBody);
    }

    public RegistrationBeanCreator(String type, String inputXMLBody) {
        if (type.equals(StudentConstants.STUDENT_CREATE)) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                UserHandler userhandler = new UserHandler();
                StringReader sr = new StringReader(inputXMLBody);
                InputSource is = new InputSource(sr);
                studentRegisterBean = new RegistrationBean();
                saxParser.parse(is, userhandler);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }

    public RegistrationBean createRegistrationBeanObject() {
        return studentRegisterBean;
    }

    class UserHandler extends DefaultHandler {

        boolean bStaff = false;
        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bMiddleName = false;
        boolean bAge = false;
        boolean bDob = false;
        boolean bFather = false;
        boolean bMother = false;
        boolean bSpouse = false;
        boolean bMobileNumber = false;
        boolean bAddress = false;
        boolean bPincode = false;
        boolean bEmailid = false;
        boolean bPassword = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            if (qName.equalsIgnoreCase("student")) {
            } else if (qName.equalsIgnoreCase("staff")) {
                bStaff = true;
                studentRegisterBean.setIsStaff(true);
                studentRegisterBean.setIsTeachingStaff(Boolean.parseBoolean(attributes.getValue("isTeaching")));
            } else if (qName.equalsIgnoreCase("firstname")) {
                bFirstName = true;
            } else if (qName.equalsIgnoreCase("lastname")) {
                bLastName = true;
            } else if (qName.equalsIgnoreCase("middlename")) {
                bMiddleName = true;
            } else if (qName.equalsIgnoreCase("age")) {
                bAge = true;
            } else if (qName.equalsIgnoreCase("dob")) {
                bDob = true;
            } else if (qName.equalsIgnoreCase("father")) {
                bFather = true;
            } else if (qName.equalsIgnoreCase("mother")) {
                bMother = true;
            } else if (qName.equalsIgnoreCase("spouse")) {
                bSpouse = true;
            } else if (qName.equalsIgnoreCase("mobilenumber")) {
                bMobileNumber = true;
            } else if (qName.equalsIgnoreCase("address")) {
                bAddress = true;
            } else if (qName.equalsIgnoreCase("pincode")) {
                bPincode = true;
            } else if (qName.equalsIgnoreCase("emailid")) {
                bEmailid = true;
            } else if (qName.equalsIgnoreCase("password")) {
                bPassword = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("student") || (qName.equalsIgnoreCase("staff"))) {
                System.out.println("End Element :" + qName);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (bFirstName) {
                System.out.println("First Name: " + new String(ch, start, length));
                studentRegisterBean.setFirstName(new String(ch, start, length));
                bFirstName = false;
            } else if (bLastName) {
                System.out.println("Last Name: " + new String(ch, start, length));
                studentRegisterBean.setLastName(new String(ch, start, length));
                bLastName = false;
            } else if (bMiddleName) {
                System.out.println("Middle Name: " + new String(ch, start, length));
                studentRegisterBean.setMiddleName(new String(ch, start, length));
                bMiddleName = false;
            } else if (bAge) {
                System.out.println("Age: " + new String(ch, start, length));
                studentRegisterBean.setStudentAge(Integer.parseInt(new String(ch, start, length)));
                bAge = false;
            } else if (bDob) {
                System.out.println("DOB: " + new String(ch, start, length));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {

                    Date date = formatter.parse(new String(ch, start, length));
                    // System.out.println(date);
                    // System.out.println(formatter.format(date));
                    studentRegisterBean.setStudentDob(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                bDob = false;
            } else if (bFather) {
                System.out.println("Father: " + new String(ch, start, length));
                studentRegisterBean.setFatherName(new String(ch, start, length));
                bFather = false;
            } else if (bMother) {
                System.out.println("Mother: " + new String(ch, start, length));
                studentRegisterBean.setMotherName(new String(ch, start, length));
                bMother = false;
            } else if (bSpouse) {
                System.out.println("Mother: " + new String(ch, start, length));
                studentRegisterBean.setSpouseName(new String(ch, start, length));
                bSpouse = false;
            } else if (bMobileNumber) {
                System.out.println("Mobile Number: " + new String(ch, start, length));
                studentRegisterBean.setMobileNumber(new String(ch, start, length));
                bMobileNumber = false;
            } else if (bAddress) {
                System.out.println("Address: " + new String(ch, start, length));
                studentRegisterBean.setAddress(new String(ch, start, length));
                bAddress = false;
            } else if (bPincode) {
                System.out.println("Pincode: " + new String(ch, start, length));
                studentRegisterBean.setPincode(new String(ch, start, length));
                bPincode = false;
            } else if (bEmailid) {
                System.out.println("emailid: " + new String(ch, start, length));
                studentRegisterBean.setEmailiId(new String(ch, start, length));
                bEmailid = false;
            } else if (bPassword) {
                System.out.println("Password: " + new String(ch, start, length));
                studentRegisterBean.setPassword(new String(ch, start, length));
                bPassword = false;
            }
        }
    }

}
