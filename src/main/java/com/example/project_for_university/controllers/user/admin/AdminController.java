package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AuthUtils;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdminController implements DataProvider {
    private AllValues allValues;
    private AbrasionTypeEntity[] abrasionTypes;
    private BendingTypeEntity[] bendingTypes;
    private GlueTypeEntity[] glueTypes;
    private LayerTypeEntity[] layerTypes;
    private MembraneLayerPolymerTypeEntity[] membraneLayerPolymerTypes;
    private PhysicalActivityTypeEntity[] physicalActivityTypes;
    private ProductionMethodEntity[] productionMethods;
    private WashingTypeEntity[] washingTypes;

    @FXML
    private HBox btn_abrasion_type;

    @FXML
    private HBox btn_production_type;

    @FXML
    private HBox btn_bend_type;

    @FXML
    private HBox btn_glue_type;

    @FXML
    private HBox btn_lay_type;

    @FXML
    private HBox btn_lev_activ_type;

    @FXML
    private HBox btn_membr_lay_type;

    @FXML
    private HBox btn_washing_type;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void btn_abrasion_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_bend_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_production_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_glue_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_lay_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_lev_activ_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_membr_lay_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_washing_type_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    //ДИМА ЭТО КАЛ ПОЛНЫЙ, СДЕЛАЙ ОТДЕЛЬНЫЕ СЕРВИСЫ, ТЕБЕ ЖЕ НЕ ТОЛЬКО ГЕТ ЗАПРОСЫ ИДАТЬ ДЛЯ КАЖДОГО ТИПА

//    private <T> void changeWindow(int btnTypeNum, List<String> names) throws IOException {
//        Scene scene;
//        if(btnTypeNum == 1) {
//            FXMLLoader fxmlLoader = new FXMLLoader(AdminController.class.getResource(Component.TYPE.getPath()));
//            scene = new Scene(fxmlLoader.load());
//
//            ((TypeController) fxmlLoader.getController()).setData(allValues, names);
//        } else {
//            FXMLLoader fxmlLoader = new FXMLLoader(AdminController.class.getResource(Component.TYPE.getPath()));
//            scene = new Scene(fxmlLoader.load());
//
//            ((PhTypeController) fxmlLoader.getController()).setData(allValues);
//        }
//
//        Stage window = (Stage) btn_abrasion_type.getScene().getWindow();
//        window.setTitle("Расчет материалов");
//        window.setScene(scene);
//        window.show();
//    }

//    private String getUrl(int num) {
//        switch (num) {
//            case 1: return Main.host.getValue() + "/abrasion-type";
//            case 2: return Main.host.getValue() + "/bending-type";
//            case 3: return Main.host.getValue() + "/glue-type";
//            case 4: return Main.host.getValue() + "/layer-type";
//            case 5: return Main.host.getValue() + "/membrane-layer-polymer-type";
//            case 6: return Main.host.getValue() + "/physical-activity-type";
//            case 7: return Main.host.getValue() + "/production-method";
//            case 8: return Main.host.getValue() + "/washing-type";
//        }
//
//        return null;
//    }
//
//    public void setDataFromBackend() throws IOException {
//        abrasionTypes = getListOfNeedType(AbrasionTypeEntity.class, 1).toArray(AbrasionTypeEntity[]::new);
//        bendingTypes = getListOfNeedType(BendingTypeEntity.class, 2).toArray(BendingTypeEntity[]::new);
//        glueTypes = getListOfNeedType(GlueTypeEntity.class, 3).toArray(GlueTypeEntity[]::new);
//        layerTypes = getListOfNeedType(LayerTypeEntity.class, 4).toArray(LayerTypeEntity[]::new);
//        membraneLayerPolymerTypes = getListOfNeedType(MembraneLayerPolymerTypeEntity.class, 5).toArray(MembraneLayerPolymerTypeEntity[]::new);
//        physicalActivityTypes = getListOfNeedType(PhysicalActivityTypeEntity.class, 6).toArray(PhysicalActivityTypeEntity[]::new);
//        productionMethods = getListOfNeedType(ProductionMethodEntity.class, 7).toArray(ProductionMethodEntity[]::new);
//        washingTypes = getListOfNeedType(WashingTypeEntity.class, 8).toArray(WashingTypeEntity[]::new);
//        allValues.setReturnAllTypesDto(new ReturnAllTypesDto(
//                abrasionTypes,
//                bendingTypes,
//                glueTypes,
//                layerTypes,
//                membraneLayerPolymerTypes,
//                physicalActivityTypes,
//                productionMethods,
//                washingTypes
//        ));
//    }
//
//    private <T> ObservableList<T> getListOfNeedType(Class<T> tClass, int numRoute) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpUriRequest httpGet = RequestBuilder.get()
//                .setUri(getUrl(numRoute))
//                .setHeader("Content-Type", "application/json")
//                .setHeader(AuthUtils.header, AuthUtils.getAuth(allValues.getUser().getEmail(), allValues.getUser().getPassword()))
//                .build();
//        CloseableHttpResponse response = httpClient.execute(httpGet);
//        return FXCollections.observableArrayList(JsonToClass.parseToListObject(tClass, response));
//    }
}
