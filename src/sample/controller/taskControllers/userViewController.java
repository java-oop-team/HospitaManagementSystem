package sample.controller.taskControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import sample.controller.actionTask.UserAction;
import sample.model.BloodGroup;
import sample.model.Patient;
import sample.model.UserRoll;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class userViewController {
    ObservableList<String> userType;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> userView_userTable;

    @FXML
    private JFXButton userView_addUser;

    @FXML
    private JFXButton userView_updateUser;

    @FXML
    private JFXButton userView_deleteUser;

    @FXML
    private JFXButton userView_viewAll;

    @FXML
    private JFXButton userView_reset;

    @FXML
    private JFXTextField userView_name;

    @FXML
    private JFXTextField userView_phoneNum;

    @FXML
    private JFXTextField userView_NIC;

    @FXML
    private DatePicker userView_dob;

    @FXML
    private JFXTextField userView_addressLine01;

    @FXML
    private JFXTextField userView_addressLine02;

    @FXML
    private JFXComboBox<String> userView_marital;

    @FXML
    private JFXTextField userView_userName;

    @FXML
    private JFXTextField userView_allergies;

    @FXML
    private JFXComboBox<BloodGroup> userView_bloodGroup;

    @FXML
    private JFXComboBox<String> userView_gender;

    @FXML
    private JFXTextField userView_staffID;

    @FXML
    private JFXTextField userView_staffEmail;

    @FXML
    private DatePicker userView_staffdoj;

    @FXML
    private JFXTextField userView_nicSearch;

    @FXML
    private JFXButton userView_searchButton;

    @FXML
    private JFXComboBox<String> userView_speciality;

    @FXML
    private JFXComboBox<UserRoll> userView_userTypeDrop;

    @FXML
    private JFXPasswordField userView_userPassword;

    @FXML
    void initialize() {
        userView_userTypeDrop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (userView_userTypeDrop.getValue()==UserRoll.PATIENT){
                    userView_staffID.setDisable(true);
                    userView_staffdoj.setDisable(true);
                    userView_staffEmail.setDisable(true);
                    userView_speciality.setDisable(true);


                }else if (userView_userTypeDrop.getValue()==UserRoll.ADMIN){
                    userView_bloodGroup.setDisable(true);

                }
                else if (userView_userTypeDrop.getValue()==UserRoll.RECEPTIONIST){
                    userView_staffID.setDisable(false);
                    userView_staffdoj.setDisable(false);
                    userView_staffEmail.setDisable(false);
                    userView_speciality.setDisable(true);
                }else {
                    userView_speciality.setDisable(false);
                }
            }
        });


        //set the derop down wit the data taken by the reference module
        userView_userTypeDrop.getItems().addAll(referenceViewController.getUserRolls());
        userView_speciality.getItems().addAll(referenceViewController.getDoctorSpeciality());
        userView_gender.getItems().addAll(referenceViewController.getGender());
        userView_bloodGroup.getItems().addAll(referenceViewController.getBloogGroup());
        userView_marital.getItems().addAll(referenceViewController.getMaritalStatus());

        userView_addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate localDate = userView_dob.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));



                switch (userView_userTypeDrop.getValue()){
                    case PATIENT:
                        Patient patient = new Patient();
                        patient.setUserRoll(userView_userTypeDrop.getValue());
                        patient.setName(userView_name.getText());
                        patient.setGender(userView_gender.getValue());
                        patient.setMaritalStatus(userView_marital.getValue());
                        patient.setDob(Date.from(instant));
                        patient.setPhoneNumber(userView_phoneNum.getText());
                        patient.setIdCardNumber(userView_NIC.getText());
                        patient.setUserName(userView_NIC.getText());
                        patient.setUserPassword(userView_NIC.getText());
                        patient.setBloodGroup(userView_bloodGroup.getValue());
                        patient.setAllergies(userView_allergies.getText());

                        UserAction.addPatient(patient,UserRoll.ADMIN);
                }
            }
        });
    }
}
