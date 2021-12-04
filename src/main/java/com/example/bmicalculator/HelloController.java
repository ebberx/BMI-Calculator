package com.example.bmicalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField ageTextField;
    @FXML
    private CheckBox maleCheckBox;
    @FXML
    private CheckBox femaleCheckBox;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private Button calculateButton;
    @FXML
    private Button resetButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Label bmiOutputLabel;
    @FXML
    private ProgressBar bmiProcent;

    @FXML
    private ImageView bmiImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load bmi scale image
        File file = new File("bmi-adult.png");
        Image image = new Image(file.toURI().toString());
        bmiImg.setImage(image);
    }

    @FXML
    public void onCalculateClick() {
        double age;
        int height;
        double weight;

        // Get values from the textfields of the application
        try {
            age = Double.parseDouble(ageTextField.getText());
            height = Integer.parseInt(heightTextField.getText());
            weight = Double.parseDouble(weightTextField.getText());
        }
        catch (Exception e) {
            errorLabel.setText("Error!");
            return;
        }
        errorLabel.setText("");

        // Calculate and set bmi
        double bmi = weight/Math.pow((double)(height)/100, 2);
        setBMILevel(bmi);
        String bmiText = String.format("%.2f", bmi);
        bmiOutputLabel.setText(bmiText+"kg/m2");
    }

    @FXML
    public void onResetClick() {
        // Reset everything
        errorLabel.setText("");
        ageTextField.setText("");
        maleCheckBox.setSelected(false);
        femaleCheckBox.setSelected(false);
        heightTextField.setText("");
        weightTextField.setText("");
        bmiOutputLabel.setText("0");
        setBMILevel(12);
    }

    @FXML
    public void onMaleCheckClick() {
        // Turn off other checkbox, only one at a time
        femaleCheckBox.setSelected(false);
    }
    @FXML
    public void onFemaleCheckClick() {
        // Turn off other checkbox, only one at a time
        maleCheckBox.setSelected(false);
    }

    /**
     * Sets the progress bar to the level corresponding to bmi value
     * @param bmi the bmi value
     */
    public void setBMILevel(double bmi) {
        double barLevel = (bmi - 12) / (44 - 12);
        bmiProcent.setProgress(barLevel);
    }
}