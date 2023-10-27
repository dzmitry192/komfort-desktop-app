package com.example.project_for_university.enums;

public enum AdminPanelType {
    PHYSICAL_ACTIVITY ("Уровень физической активности"),
    MEMBRANE_LAYER_POLYMER ("Тип полимера мембранного слоя"),
    PRODUCTION_METHOD ("Способ производства"),
    ABRASION ("Тип истирания"),
    BENDING ("Тип изгиба"),
    WASHING ("Тип стирки"),
    LAYER ("Тип слоя"),
    GLUE ("Тип слоя");

    private String name;

    AdminPanelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
