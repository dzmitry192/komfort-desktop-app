package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.LoginDto;
import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.example.project_for_university.dto.forBackend.create.CreateConditionDto;
import com.example.project_for_university.dto.forBackend.create.CreateLayerDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.create.CreateWashingDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AuthService;
import com.example.project_for_university.service.MaterialService;
import com.example.project_for_university.service.ReturnAllTypesService;
import com.example.project_for_university.service.models.UserModel;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Data
public class LoginController implements DataProvider {
    private AllValues allValues = new AllValues();
    private AuthService authService = new AuthService();
    private ReturnAllTypesService returnAllTypesService = new ReturnAllTypesService();

    @FXML
    private TextField email_tf;

    @FXML
    private VBox main_box;

    @FXML
    private VBox sec_main_box;

    @FXML
    private Button login_btn;

    @FXML
    private TextField password_tf;

    @FXML
    private Button signup_btn;

    @FXML
    private Label status_lbl;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void login_btn_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        String email = email_tf.getText();
        String password = password_tf.getText();

//        allValues = LoaderUtil.showModal(allValues.getRootStage(), allValues);

        if (email.isEmpty() || password.isEmpty()) {
            status_lbl.setText("Все поля должны быть заполнены");
        } else {
            UserModel userModel = authService.loginThread(new LoginDto(email, password));
            if (userModel.isError()) {
                status_lbl.setText(userModel.getErrorMessage());
            } else {
                allValues.setUser(userModel.getUser());
                allValues.getAdminPanelInfo().setReturnAllTypesDto(returnAllTypesService.getAllTypesThread(allValues.getUser().getEmail(), allValues.getUser().getPassword()).getReturnAllTypesDto());
                ComponentUtil.mount(Component.LOGGED_IN, allValues.getContentPanes().getMainContentPane(), allValues);
            }
//            LoaderUtil.closeModal(allValues.getLoaderStage());
        }
    }

    @SneakyThrows
    @FXML
    void signup_btn_clicked(MouseEvent event) throws IOException {
        new MaterialService().create(new CreateMaterialDto(
                new File[]{new File("/Users/daniil/Desktop/project_for_university/src/main/resources/com/example/project_for_university/img/no-image.png")},
                new MaterialInfoDto("GAVNO_IS_ZHOPbI", "manufacturer", "description", 0, 1, 1, 1, new ArrayList<>(List.of(new CreateLayerDto(1, 1)))),
                new CreateConditionDto(
                        true,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        new CreateWashingDto(0, 0, 0, true, 1),
                        1,
                        1,
                        1
                ),
                new CalculateWaterproofFunctionDto(
                        0,
                        0,
                        0,
                        0,

                        0,
                        0,  // waterproof_calculated
                        0,  // waterproof_base
                        0,   // waterproof_weight

                        0,  // materialBlottingTime_experimental_1
                        0,  // materialBlottingTime_calculated
                        0,  // materialBlottingTime_base
                        0,   // materialBlottingTime_weight

                        0,  // waterproofRealizationCriteria_experimental_1
                        0,  // waterproofRealizationCriteria_experimental_2
                        0,  // waterproofRealizationCriteria_base
                        0,   // waterproofRealizationCriteria_weight

                        0,  // dynamicWaterproofCriteria_experimental_1
                        0,  // dynamicWaterproofCriteria_experimental_2
                        0,  // dynamicWaterproofCriteria_experimental_3
                        0,  // dynamicWaterproofCriteria_experimental_4
                        0,  // dynamicWaterproofCriteria_base
                        0,   // dynamicWaterproofCriteria_weight

                        0,  // hydrostaticPressureIncreaseSpeed
                        0,  // hydrostaticPressure
                        0,  // waterproofTime
                        "testEquipment"
                ),
                new CalculateHomeostasisFunctionDto(
                        0,  // sampleSurfaceArea
                        0,  // m1s
                        0,  // m2s
                        0,  // s0_1
                        0,  // t_1
                        0,  // waterPermeability_base
                        0,   // waterPermeability_weight
                        0,  // m1min
                        0,  // m2min
                        0,  // m1max
                        0,  // m2max
                        0,  // s0_2
                        0,  // t_2
                        0,  // waterPermeabilityDynamicCriteria_base
                        0,   // waterPermeabilityDynamicCriteria_weight
                        0,  // tos
                        0,  // s
                        0,  // m
                        0,  // totalThermalResistance_base
                        0,   // totalThermalResistance_weight
                        0,  // comfTempInsideClothes
                        0,  // comfHumidityInsideClothes
                        0,  // minOutdoorTemp
                        0,  // maxOutdoorTemp
                        0,  // minOutdoorHumidity
                        0,  // maxOutdoorHumidity
                        0,  // avgOutdoorAirSpeed
                        "testEquipment"
                ),
                new CalculateReliabilityFunctionDto(
                        0,  // maxWaterResistanceLvl
                        0,  // relativeBlottingPressureAfterLoad_experimental_1
                        0,  // relativeBlottingPressureAfterLoad_calculated
                        0,  // relativeBlottingPressureAfterLoad_base
                        0,   // relativeBlottingPressureAfterLoad_weight
                        0,  // relativeWaterResistanceAfterLoad_experimental_1
                        0,  // relativeWaterResistanceAfterLoad_calculated
                        0,  // relativeWaterResistanceAfterLoad_base
                        0,   // relativeWaterResistanceAfterLoad_weight
                        0,  // relativeBlottingTimeAfterLoad_experimental_1
                        0,  // relativeBlottingTimeAfterLoad_calculated
                        0,  // relativeBlottingTimeAfterLoad_base
                        0,   // relativeBlottingTimeAfterLoad_weight
                        0,  // waterproofRealizationCriteriaAfterLoad_experimental_1
                        0,  // waterproofRealizationCriteriaAfterLoad_experimental_2
                        0,   // waterproofRealizationCriteriaAfterLoad_weight
                        0,  // waterproofFunctionResource_base
                        0,   // waterproofFunctionResource_weight
                        0,  // impactCyclesCnt
                        "testEquipment"

                ),
                new CalculateEstimationDto(0, 0, 0)), "admin@yandex.by", "adminDlyaDimki9182_gay"
        );
        ComponentUtil.mount(Component.SIGNUP, allValues.getContentPanes().getMainContentPane(), allValues);
    }
}