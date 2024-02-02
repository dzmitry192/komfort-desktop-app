package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.MaterialService;
import com.example.project_for_university.service.models.material.CreateMaterialRequestDto;
import com.example.project_for_university.service.models.material.CreateMaterialResponse;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MaterialInfoController implements DataProvider {
    private AllValues allValues;
    private static final List<File> images = new ArrayList<>();
    @FXML
    private TextArea description;

    @FXML
    private TextField name;

    @FXML
    private HBox upload_photo_btn;

    @FXML
    private HBox back_btn;

    @FXML
    private HBox next_btn;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;

        fillMaterialInfo();

        if (allValues.getCreateMaterialDto().getMaterial() == null) {
            allValues.getCreateMaterialDto().setMaterial(new MaterialInfoDto());
        }

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    validateAndSetData();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    @SneakyThrows
    @FXML
    void back_btn_clicked(MouseEvent event) throws IOException {
        validateAndSetData();
        allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
        ComponentUtil.mount(Component.ESTIMATION_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    private void fillMaterialInfo() {
        CreateMaterialDto materialDto = allValues.getCreateMaterialDto();

        if (materialDto.getMaterial().getDescription() != null) {
            description.setText(materialDto.getMaterial().getDescription());
        }
        if (materialDto.getMaterial().getName() != null) {
            name.setText(materialDto.getMaterial().getName());
        }
        if (materialDto.getImages() != null) {
            images.addAll(List.of(materialDto.getImages()));
        }
    }

    private boolean validateAndSetData() {
        try {
            if (name.getText().isEmpty()) {
                throw new IllegalArgumentException();
            } else {
                allValues.getCreateMaterialDto().getMaterial().setName(name.getText());
            }
            if (description.getText().isEmpty()) {
                throw new IllegalArgumentException();
            } else {
                allValues.getCreateMaterialDto().getMaterial().setDescription(description.getText());
            }
            if (!images.isEmpty()) {
                allValues.getCreateMaterialDto().setImages(images.toArray(File[]::new));
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private CreateMaterialRequestDto createMaterialDtoToCreateMaterialRequestDto(CreateMaterialDto materialDto) {
        CreateMaterialRequestDto requestDto = new CreateMaterialRequestDto();

        //set files
        if(materialDto.getImages().length > 0) {
            requestDto.setImages(materialDto.getImages());
        }

        //set material
        requestDto.setMaterial(materialDto.getMaterial());

        //set condition values
        requestDto.getCondition().setPositive(materialDto.getCondition().isPositive());
        requestDto.getCondition().setMinAirTemp(materialDto.getCondition().getMinAirTemp());
        requestDto.getCondition().setMaxAirTemp(materialDto.getCondition().getMaxAirTemp());
        requestDto.getCondition().setMinAirHumidity(materialDto.getCondition().getMinAirHumidity());
        requestDto.getCondition().setMaxAirHumidity(materialDto.getCondition().getMaxAirHumidity());
        requestDto.getCondition().setAvgAirSpeed(materialDto.getCondition().getAvgAirSpeed());
        requestDto.getCondition().setResidenceTime(materialDto.getCondition().getResidenceTime());
        requestDto.getCondition().setTorsionAngle(materialDto.getCondition().getTorsionAngle());
        requestDto.getCondition().setStretchingCompression(materialDto.getCondition().getStretchingCompression());
        requestDto.getCondition().getWashing().setTemperature(materialDto.getCondition().getWashing().getTemperature());
        requestDto.getCondition().getWashing().setDuration(materialDto.getCondition().getWashing().getDuration());
        requestDto.getCondition().getWashing().setPress(materialDto.getCondition().getWashing().isPress());
        requestDto.getCondition().getWashing().setCyclesCnt(materialDto.getCondition().getWashing().getCyclesCnt());
        if(materialDto.getCondition().getWashing().getWashingType_id() == 0) {
            requestDto.getCondition().getWashing().setWashingType_id(null);
        } else {
            requestDto.getCondition().getWashing().setWashingType_id(materialDto.getCondition().getWashing().getWashingType_id());
        }
        if(materialDto.getCondition().getAbrasionType_id() == 0) {
            requestDto.getCondition().setAbrasionType_id(null);
        } else {
            requestDto.getCondition().setAbrasionType_id(materialDto.getCondition().getAbrasionType_id());
        }
        if(materialDto.getCondition().getBendingType_id() == 0) {
            requestDto.getCondition().setBendingType_id(null);
        } else {
            requestDto.getCondition().setBendingType_id(materialDto.getCondition().getBendingType_id());
        }
        requestDto.getCondition().setPhysicalActivityType_id(materialDto.getCondition().getPhysicalActivityType_id());

        //waterproof table
        requestDto.setWaterproofFunction(negativeValueToZero(materialDto.getWaterproofFunction()));
        requestDto.setHomeostasisFunction(negativeValueToZero(materialDto.getHomeostasisFunction()));
        requestDto.setReliabilityFunction(negativeValueToZero(materialDto.getReliabilityFunction()));
        requestDto.setEstimation(materialDto.getEstimation());

        return requestDto;
    }

    private <T> T negativeValueToZero(T obj) {
        try {
            T updatedObj = (T) obj.getClass().newInstance();

            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                if (field.getType() == double.class) {
                    double value = field.getDouble(obj);

                    if (value == -1) {
                        field.setDouble(updatedObj, 0);
                    } else {
                        field.setDouble(updatedObj, value);
                    }
                }
            }

            return updatedObj;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return obj; // Возвращаем исходный объект в случае ошибки
        }
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if (validateAndSetData()) {
            System.out.println("------ДО--------\n" + allValues.getCreateMaterialDto());
            CreateMaterialRequestDto createMaterialRequestDto = createMaterialDtoToCreateMaterialRequestDto(allValues.getCreateMaterialDto());
            System.out.println("------ПОСЛЕ-----------\n" + createMaterialRequestDto.getCondition().isPositive());
            CreateMaterialResponse createMaterialResponse = MaterialService.materialService.create(createMaterialRequestDto, allValues.getUser().getEmail(), allValues.getUser().getPassword());
            if(createMaterialResponse.isError()) {
                AlertUtil.show("Ошибка при создании материала", createMaterialResponse.getErrorMessage(), allValues.getRootStage());
            } else {
                allValues.setCreateMaterialDto(new CreateMaterialDto());
                allValues.setLastCreateMaterialComponent(null);
                ComponentUtil.mountMaterialDetails(allValues.getContentPanes().getLoggedInStackPane(), allValues, createMaterialResponse.getMaterial());
            }
        } else {
            AlertUtil.show("Заполните все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        }
    }

    @FXML
    void upload_photo_btn_clicked(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файлы");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); // начальная директория
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.tif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage); // stage - текущее окно приложения
        if (selectedFiles.size() > 5) {
            AlertUtil.show("Превышен лимит", "Максимальное количетсов фотографий - 5", allValues.getRootStage());
        } else {
            images.addAll(selectedFiles);
            allValues.getCreateMaterialDto().setImages(images.toArray(File[]::new));
        }
    }

    @FXML
    void initialize() {
        name.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() >= 40) {
                AlertUtil.show("Превышен лимит", "Максимальная длинна названия - 40", allValues.getRootStage());
                return null;
            }
            return change;
        }));

        description.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() >= 800) {
                AlertUtil.show("Превышен лимит", "Максимальная примечания названия - 800", allValues.getRootStage());
                return null;
            }
            return change;
        }));
    }
}
