package com.example.project_for_university.factory;

import com.example.project_for_university.enums.AdminPanelType;
import com.example.project_for_university.interfaces.CrudService;
import com.example.project_for_university.service.admin.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceFactory {
    public <T extends CrudService> T  createService(AdminPanelType panelType) {
        switch (panelType.name()) {
            case "PHYSICAL_ACTIVITY":
                return (T) new PhysicalActivityTypeService();
            case "MEMBRANE_LAYER_POLYMER":
                return (T) new MembraneLayerPolymerTypeService();
            case "PRODUCTION_METHOD":
                return (T) new ProductionMethodService();
            case "ABRASION":
                return (T) new AbrasionTypeService();
            case "BENDING":
                return (T) new BendingTypeService();
            case "WASHING":
                return (T) new WashingTypeService();
            case "LAYER":
                return (T) new LayerTypeService();
            default:
                return (T) new GlueTypeService();
        }
    }
}
