package com.example.project_for_university.enums;

import lombok.Getter;

@Getter
public enum UrlRoutes {
    AUTH_SIGNUP("/auth/signup"),
    AUTH_LOGIN("/auth/login"),
    GET_MATERIALS("/material"),
    GET_MATERIAL_BY_ID("/material/"),
    DELETE_MATERIAL_BY_ID("/material/"),
    POST_MATERIAL("/material"),
    GET_GLUE_TYPES("/glue-type"),
    GET_GLUE_TYPE_BY_ID("/glue-type/"),
    POST_GLUE_TYPE("/glue-type"),
    PATCH_GLUE_TYPE_BY_ID("/glue-type/"),
    DELETE_GLUE_TYPE_BY_ID("/glue-type/"),
    GET_LAYER_TYPES("/layer-type"),
    GET_LAYER_TYPE_BY_ID("/layer-type/"),
    POST_LAYER_TYPE("/layer-type"),
    PATCH_LAYER_TYPE_BY_ID("/layer-type/"),
    DELETE_LAYER_TYPE_BY_ID("/layer-type/"),
    GET_MEMBRANE_LAYER_POLYMER_TYPES("/membrane-layer-polymer-type"),
    GET_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID("/membrane-layer-polymer-type/"),
    POST_MEMBRANE_LAYER_POLYMER_TYPE("/membrane-layer-polymer-type"),
    PATCH_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID("/membrane-layer-polymer-type/"),
    DELETE_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID("/membrane-layer-polymer-type/"),
    GET_PRODUCTION_METHODS("/production-method"),
    GET_PRODUCTION_METHOD_BY_ID("/production-method/"),
    POST_PRODUCTION_METHOD("/production-method"),
    PATCH_PRODUCTION_METHOD_BY_ID("/production-method/"),
    DELETE_PRODUCTION_METHOD_BY_ID("/production-method/"),
    GET_ABRASION_TYPES("/abrasion-type"),
    GET_ABRASION_TYPE_BY_ID("/abrasion-type/"),
    POST_ABRASION_TYPE("/abrasion-type"),
    PATCH_ABRASION_TYPE_BY_ID("/abrasion-type/"),
    DELETE_ABRASION_TYPE_BY_ID("/abrasion-type/"),
    GET_BENDING_TYPES("/bending-type"),
    GET_BENDING_TYPE_BY_ID("/bending-type/"),
    POST_BENDING_TYPE("/bending-type"),
    PATCH_BENDING_TYPE_BY_ID("/bending-type/"),
    DELETE_BENDING_TYPE_BY_ID("/bending-type/"),
    GET_PHYSICAL_ACTIVITY_TYPES("/physical-activity-type"),
    GET_PHYSICAL_ACTIVITY_TYPE_BY_ID("/physical-activity-type/"),
    POST_PHYSICAL_ACTIVITY_TYPE("/physical-activity-type"),
    PATCH_PHYSICAL_ACTIVITY_TYPE_BY_ID("/physical-activity-type/"),
    DELETE_PHYSICAL_ACTIVITY_TYPE_BY_ID("/physical-activity-type/"),
    GET_WASHING_TYPES("/washing-type"),
    GET_WASHING_TYPE_BY_ID("/washing-type/"),
    POST_WASHING_TYPE("/washing-type"),
    PATCH_WASHING_TYPE_BY_ID("/washing-type/"),
    DELETE_WASHING_TYPE_BY_ID("/washing-type/"),
    GET_ALL_TYPES("/all-types");

    private final String name;

    UrlRoutes(String name) {
        this.name = name;
    }

}
