package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Student;
import util.crudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManageFormController {
    public JFXTextField studentId;
    public JFXTextField studentName;
    public JFXTextField studentMail;
    public JFXTextField studentContact;
    public JFXTextField studentAddress;
    public JFXTextField studentNIC;
    public JFXTextField searchStudentId;
    public JFXTextField studentId1;
    public JFXTextField studentName1;
    public JFXTextField studentMail1;
    public JFXTextField studentContact1;
    public JFXTextField studentAddress1;
    public JFXTextField studentNIC1;

    public void addBtn(ActionEvent actionEvent) {
        String id = studentId.getText();
        String name = studentName.getText();
        String mail = studentMail.getText();
        String contact = studentContact.getText();
        String address = studentAddress.getText();
        String nic = studentNIC.getText();

        Student student = new Student(id,name,mail,contact,address,nic);

        try{
            if(crudUtil.execute("INSERT INTO student VALUES (?,?,?,?,?,?)",student.getStudentId(),student.getStudentName(),student.getStudentEmail(),student.getStudentContact(),student.getStudentAddress(),student.getStudentNic())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Student!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something went Wrong!..").show();
        }
    }

    public void updateBtn(ActionEvent actionEvent) {
        String newId = studentId.getText();
        String newName = studentName.getText();
        String newMail = studentMail.getText();
        String newContact = studentContact.getText();
        String newAddress = studentAddress.getText();
        String newNic = studentNIC.getText();

        Student student = new Student(newId,newName,newMail,newContact,newAddress,newNic);

        try{
            if(crudUtil.execute("UPDATE student SET studentName=?,email=?,contact=?,address=?,nic=? WHERE studentId=?",student.getStudentName(),student.getStudentEmail(),student.getStudentContact(),student.getStudentAddress(),student.getStudentNic(),student.getStudentId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Student Details!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something went Wrong!..").show();
        }

    }

    public void deleteBtn(ActionEvent actionEvent) {
        String Id = studentId.getText();

        try{
            if (crudUtil.execute("DELETE FROM student WHERE studentId=? ",Id)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted Student!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something went Wrong!..").show();
            }
        }catch (  ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void searchBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ResultSet result = crudUtil.execute("SELECT * FROM student WHERE studentId=?",searchStudentId.getText());
        if (result.next()) {
            studentId.setText(result.getString(1));
            studentName.setText(result.getString(2));
            studentMail.setText(result.getString(3));
            studentContact.setText(result.getString(4));
            studentAddress.setText(result.getString(5));
            studentNIC.setText(result.getString(6));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }
}
