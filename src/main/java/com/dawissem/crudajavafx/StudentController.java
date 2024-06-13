package com.dawissem.crudajavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    //Config conexion base de donne
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tBirth;
    @FXML
    private TextField tCourse;
    @FXML
    private TextField tFName;
    @FXML
    private TextField tLastName;
    @FXML
    private TableColumn<Student, String> colBirthDate;
    @FXML
    private TableColumn<Student, String> colCourse;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colfName;
    @FXML
    private TableColumn<Student, String> collName;
    @FXML
    private TableView<Student> table;
    int id = 0;  // oon las incremente l'id icic pourqon fasse la mise ajour du contenu de table



    public void initialize(URL url, ResourceBundle resourceBundle) {
    showStudents();

    }



    public ObservableList<Student> getStudents()
    {
      ObservableList<Student> students = FXCollections.observableArrayList();

        String query = "select * from students";
        //afficahge de tout les etudaitns
        con = DBConnexion.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {

                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setFirstName(rs.getString("FirstName"));
                st.setLastName(rs.getString("LastName"));
                st.setBirthDate(rs.getString("BirthDate"));
                st.setCourse(rs.getString("Course"));
                students.add(st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return students;
    }






    public void showStudents() {
        ObservableList<Student> list = getStudents();
        table.setItems(list);
       colId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colfName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
       collName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
    }



    @FXML
    void creatStudent(ActionEvent event) {


        String insert = "insert into students(firstName,lastName,birthDate,course) values(?,?,?,?)";
        con = DBConnexion.getCon();


            try {
                st = con.prepareStatement(insert);
                st = con.prepareStatement(insert);
                st.setString(1, tFName.getText());
                st.setString(2, tLastName.getText());
                st.setString(3, tBirth.getText());
                st.setString(4, tCourse.getText());
                st.executeUpdate();
                clear();
                showStudents();
            } catch
            (SQLException e)
            {
                throw new RuntimeException(e);
            }
    }
    @FXML
    void getData(MouseEvent event) {
        Student student = (Student)this.table.getSelectionModel().getSelectedItem();
        id = student.getId();
        tFName.setText(student.getFirstName());
        tLastName.setText(student.getLastName());
        tBirth.setText(student.getBirthDate());
        tCourse.setText(student.getCourse());
        btnSave.setDisable(true);
    }

    void clearField(ActionEvent event) {
        tFName.setText(null);
        tLastName.setText(null);
        tBirth.setText(null);
        tCourse.setText(null);
        btnSave.setDisable(true);

        clear();
            showStudents();
    }
    @FXML
    void clear() {
        tFName.setText(null);
        tLastName.setText(null);
        tBirth.setText(null);
        tCourse.setText(null);

        showStudents();
    }

    @FXML
    void clearStudent(ActionEvent event) {



    }


    @FXML
    void deleteStudent(ActionEvent event) {
     String delete = "delete from students where id = ?";
        con = DBConnexion.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, id);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void updateStudent(ActionEvent event) {
 String update ="update students set firstName=?, lastName=?, birthDate=?, course=?  where id=?";
    con = DBConnexion.getCon();
        try {
            st=con.prepareStatement(update);
            st.setInt(5, id);
            st.setString(1, tFName.getText());
            st.setString(2, tLastName.getText());
            st.setString(3, tBirth.getText());
            st.setString(4, tCourse.getText());
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

