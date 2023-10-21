package com.example.project_for_university.controllers.material;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.LoginController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.FConditionValues;
import com.example.project_for_university.dto.forBackend.entity.WashingEntity;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.utils.AuthUtils;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

@Data
public class FConditionController extends Node {

    private AllValues allValues;
    private FConditionValues FConditionValues = new FConditionValues();
    private int cnt_type_of_load = 0;
    private boolean isReadyToChange;

    private ObservableList<BendingTypeEntity> bendingTypeList = FXCollections.observableArrayList();
    private ObservableList<AbrasionTypeEntity> abrasionTypeList= FXCollections.observableArrayList();
    private ObservableList<PhysicalActivityTypeEntity> physicalActivityTypeList= FXCollections.observableArrayList();
    private ObservableList<WashingTypeEntity> washingTypeList= FXCollections.observableArrayList();
    private ObservableList<LayerTypeEntity> layerTypeList= FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> abrasion_type;

    @FXML
    private ComboBox<String> bend_type;

    @FXML
    private ComboBox<String> choose_layer_cb;

    @FXML
    private Button btn_add_layer;

    @FXML
    private Button btn_cond_back;

    @FXML
    private Button btn_cond_next;

    @FXML
    private CheckBox check_abrasion;

    @FXML
    private CheckBox check_bend;

    @FXML
    private CheckBox check_stretch_compress;

    @FXML
    private CheckBox check_torsion;

    @FXML
    private CheckBox check_wash;

    @FXML
    private TextField inp_av_speed;

    @FXML
    private TextField inp_max_air_one;

    @FXML
    private TextField inp_cycles_cnt;

    @FXML
    private TextField inp_max_air_sec;

    @FXML
    private TextField inp_min_temp_one;

    @FXML
    private TextField inp_min_temp_sec;

    @FXML
    private TextField inp_stretching;

    @FXML
    private TextField inp_temp_washing;

    @FXML
    private TextField inp_time_washing;

    @FXML
    private TextField inp_torsion_angle;

    @FXML
    private Label lab_spin;

    @FXML
    private Label lab_temp;

    @FXML
    private Label lab_wash_time;

    @FXML
    private ComboBox<String> lev_phys = new ComboBox<>();

    @FXML
    private RadioButton rad_btn_minus;

    @FXML
    private RadioButton rad_btn_no;

    @FXML
    private RadioButton rad_btn_plus;

    @FXML
    private RadioButton rad_btn_yes;

    @FXML
    private Slider scroll_av_speed;

    @FXML
    private Slider scroll_max_air_one;

    @FXML
    private Slider scroll_max_air_sec;

    @FXML
    private Slider scroll_min_temp_one;

    @FXML
    private Slider scroll_min_temp_sec;

    @FXML
    private Slider scroll_stretching;

    @FXML
    private Slider scroll_temp_washing;

    @FXML
    private Slider scroll_time_washing;

    @FXML
    private Slider scroll_torsion_angle;

    @FXML
    private ComboBox<Integer> time_cond = new ComboBox<>(FXCollections.observableArrayList(2, 4));

    @FXML
    private ComboBox<String> wash_type = new ComboBox<>();

    public void setData(AllValues allValues) {
        this.allValues = allValues;
        System.out.println(allValues.getMaterialInformationDto().toString());
    }

    private void setValuesToCB() {
        wash_type.setItems(FXCollections.observableArrayList(washingTypeList.stream().map(WashingTypeEntity::getName).toList()));
        choose_layer_cb.setItems(FXCollections.observableArrayList(layerTypeList.stream().map(LayerTypeEntity::getName).toList()));
        bend_type.setItems(FXCollections.observableArrayList(bendingTypeList.stream().map(BendingTypeEntity::getName).toList()));
        abrasion_type.setItems(FXCollections.observableArrayList(abrasionTypeList.stream().map(AbrasionTypeEntity::getName).toList()));
        lev_phys.setItems(FXCollections.observableArrayList(physicalActivityTypeList.stream().map(PhysicalActivityTypeEntity::getName).toList()));
    }

    public void getValuesForCB() throws IOException {
        bendingTypeList = getListOfNeedType(BendingTypeEntity.class, "/bending-type");
        layerTypeList = getListOfNeedType(LayerTypeEntity.class, "/layer-type");
        abrasionTypeList = getListOfNeedType(AbrasionTypeEntity.class, "/abrasion-type");
        physicalActivityTypeList = getListOfNeedType(PhysicalActivityTypeEntity.class, "/physical-activity-type");
        washingTypeList = getListOfNeedType(WashingTypeEntity.class, "/washing-type");
        setValuesToCB();
    }

    private <T> ObservableList<T> getListOfNeedType(Class<T> tClass, String route) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest httpGet = RequestBuilder.get()
                .setUri(Main.host.getValue() + route)
                .setHeader("Content-Type", "application/json")
                .setHeader(AuthUtils.header, AuthUtils.getAuth(allValues.getUser().getEmail(), allValues.getUser().getPassword()))
                .build();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.getStatusLine().getStatusCode());
        return FXCollections.observableArrayList(JsonToClass.parseToListObject(tClass, response));
    }

    @FXML
    void btn_add_layer_clicked(MouseEvent event) {

    }

    @FXML
    void check_abrasion_clicked(MouseEvent event) {
        if (!check_abrasion.isSelected()) {
            cnt_type_of_load--;
            abrasion_type.setVisible(false);
        } else {
            if (cnt_type_of_load == 2) {
                check_abrasion.setSelected(false);
            } else {
                cnt_type_of_load++;
                abrasion_type.setVisible(true);
            }
        }
    }

    @FXML
    void check_bend_clicked(MouseEvent event) {
        if (!check_bend.isSelected()) {
            cnt_type_of_load--;
            bend_type.setVisible(false);
        } else {
            if (cnt_type_of_load == 2) {
                check_bend.setSelected(false);
            } else {
                cnt_type_of_load++;
                bend_type.setVisible(true);
            }
        }
    }

    @FXML
    void check_stretch_compress_clicked(MouseEvent event) {
        if (!check_stretch_compress.isSelected()) {
            cnt_type_of_load--;
            scroll_stretching.setVisible(false);
            inp_stretching.setVisible(false);
        } else {
            if (cnt_type_of_load == 2) {
                check_stretch_compress.setSelected(false);
            } else {
                cnt_type_of_load++;
                scroll_stretching.setVisible(true);
                inp_stretching.setVisible(true);
            }
        }
    }

    @FXML
    void check_torsion_clicked(MouseEvent event) {
        if (!check_torsion.isSelected()) {
            cnt_type_of_load--;
            scroll_torsion_angle.setVisible(false);
            inp_torsion_angle.setVisible(false);
        } else {
            if (cnt_type_of_load == 2) {
                check_torsion.setSelected(false);
            } else {
                cnt_type_of_load++;
                scroll_torsion_angle.setVisible(true);
                inp_torsion_angle.setVisible(true);
            }
        }
    }

    @FXML
    void check_wash_clicked(MouseEvent event) {
        if (!check_wash.isSelected()) {
            cnt_type_of_load--;
            wash_type.setVisible(false);
            lab_temp.setVisible(false);
            lab_wash_time.setVisible(false);
            lab_spin.setVisible(false);
            scroll_temp_washing.setVisible(false);
            scroll_time_washing.setVisible(false);
            inp_temp_washing.setVisible(false);
            inp_time_washing.setVisible(false);
            rad_btn_yes.setVisible(false);
            rad_btn_no.setVisible(false);
        } else {
            if (cnt_type_of_load == 2) {
                check_wash.setSelected(false);
            } else {
                cnt_type_of_load++;
                wash_type.setVisible(true);
                lab_temp.setVisible(true);
                lab_wash_time.setVisible(true);
                lab_spin.setVisible(true);
                scroll_temp_washing.setVisible(true);
                scroll_time_washing.setVisible(true);
                inp_temp_washing.setVisible(true);
                inp_time_washing.setVisible(true);
                rad_btn_yes.setVisible(true);
                rad_btn_no.setVisible(true);
            }
        }
    }

    @FXML
    void rad_btn_minus_clicked(MouseEvent event) {
        rad_btn_plus.setSelected(false);
    }

    @FXML
    void rad_btn_no_clicked(MouseEvent event) {
        rad_btn_yes.setSelected(false);
    }

    @FXML
    void rad_btn_plus_clicked(MouseEvent event) {
        rad_btn_minus.setSelected(false);
    }

    @FXML
    void rad_btn_yes_clicked(MouseEvent event) {
        rad_btn_no.setSelected(false);
    }

    @FXML
    void btn_cond_back_clicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) btn_cond_back.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project_for_university/fxml/cond/material-info.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ((MaterialInfoController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void btn_cond_next_clicked(MouseEvent event) throws IOException {
        isReadyToChange = true;
        try {
            FConditionValues.setPositive(rad_btn_plus.isSelected());
            FConditionValues.setMinAirTemp((int) scroll_min_temp_one.getValue());
            FConditionValues.setMaxAirTemp((int) scroll_min_temp_sec.getValue());
            FConditionValues.setMinAirHumidity((int) scroll_max_air_one.getValue());
            FConditionValues.setMaxAirHumidity((int) scroll_max_air_sec.getValue());
            FConditionValues.setAvgAirSpeed((int) scroll_av_speed.getValue());
            FConditionValues.setResidenceTime(time_cond.getValue());
            if (check_bend.isSelected()) {
                FConditionValues.setBendingType(bendingTypeList.stream().filter(el -> el.getName().equals(bend_type.getValue())).findFirst().get());
            }
            if (check_abrasion.isSelected()) {
                FConditionValues.setAbrasionType(abrasionTypeList.stream().filter(el -> el.getName().equals(abrasion_type.getValue())).findFirst().get());
            }
            if (check_stretch_compress.isSelected()) {
                FConditionValues.setStretchingCompression((int) scroll_stretching.getValue());
            }
            if (check_torsion.isSelected()) {
                FConditionValues.setTorsionAngle((int) scroll_torsion_angle.getValue());
            }
            if (check_wash.isSelected()) {
                FConditionValues.setWashing(new WashingEntity(0,
                        (int) scroll_temp_washing.getValue(),
                        Integer.parseInt(inp_cycles_cnt.getText()),
                        (int) scroll_time_washing.getValue(),
                        rad_btn_yes.isPressed(),
                        washingTypeList.stream().filter(el -> el.getName().equals(wash_type.getValue())).findFirst().get()));
            }
            allValues.setFConditionValues(FConditionValues);
        } catch (NullPointerException e) {
            System.out.println("works");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Вы не заполнили все поля!");
            alert.setContentText("Закройте это окно и дозаполните всё необходимые поля!");
            Optional<ButtonType> option = alert.showAndWait();
            isReadyToChange = false;
        }
        if(isReadyToChange) {
            changeWindow(event);
        }
    }

    private void changeWindow(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/project_for_university/fxml/cond/condition-2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        ((SConditionController) fxmlLoader.getController()).setData(allValues);
    }

    @FXML
    void scroll_av_speed_dragged(MouseEvent event) {
        inp_av_speed.setText(String.valueOf((int) scroll_av_speed.getValue()));
    }

    @FXML
    void scroll_max_air_one_dragged(MouseEvent event) {
        inp_max_air_one.setText(String.valueOf((int) scroll_max_air_one.getValue()));
    }

    @FXML
    void scroll_max_air_sec_dragged(MouseEvent event) {
        inp_max_air_sec.setText(String.valueOf((int) scroll_max_air_sec.getValue()));
    }

    @FXML
    void scroll_min_temp_one_dragged(MouseEvent event) {
        inp_min_temp_one.setText(String.valueOf((int) scroll_min_temp_one.getValue()));
    }

    @FXML
    void scroll_min_temp_sec_dragged(MouseEvent event) {
        inp_min_temp_sec.setText(String.valueOf((int) scroll_min_temp_sec.getValue()));
    }

    @FXML
    void scroll_stretching_dragged(MouseEvent event) {
        inp_stretching.setText(String.valueOf((int) scroll_stretching.getValue()));
    }

    @FXML
    void scroll_torsion_angle_dragged(MouseEvent event) {
        inp_torsion_angle.setText(String.valueOf((int) scroll_torsion_angle.getValue()));
    }

    @FXML
    void scroll_temp_washing_dragged(MouseEvent event) {
        inp_temp_washing.setText(String.valueOf((int) scroll_temp_washing.getValue()));
    }

    @FXML
    void scroll_time_washing_dragged(MouseEvent event) {
        inp_time_washing.setText(String.valueOf((int) scroll_time_washing.getValue()));
    }
}