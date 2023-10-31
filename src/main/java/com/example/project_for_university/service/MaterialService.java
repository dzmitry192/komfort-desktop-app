package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.MaterialFilterDto;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.FilterMaterialsModel;
import com.example.project_for_university.utils.AuthUtils;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MaterialService {
    private final FilterMaterialsModel filterMaterialsModel = new FilterMaterialsModel();

    @SneakyThrows
    public FilterMaterialsModel getFilterMaterialsThread(AllValues allValues) {
        CompletableFuture<FilterMaterialsModel> futureMaterials = new CompletableFuture<>();
        MaterialFilterDto filterDto = allValues.getMaterialFilterDto();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                List<NameValuePair> queryParams = new ArrayList<>();
                if (filterDto.getName() != null) {
                    queryParams.add(new BasicNameValuePair("name", filterDto.getName()));
                }
                if (filterDto.getUserId() != 0) {
                    queryParams.add(new BasicNameValuePair("userId", String.valueOf(filterDto.getUserId())));
                }
                if (filterDto.getLayersCnt() != 0) {
                    queryParams.add(new BasicNameValuePair("layersCnt", String.valueOf(filterDto.getLayersCnt())));
                }
                if (filterDto.getMembraneLayerPolymerType_id() != 0) {
                    queryParams.add(new BasicNameValuePair("membraneLayerPolymerType_id", String.valueOf(filterDto.getMembraneLayerPolymerType_id())));
                }
                if (filterDto.getProductionMethod_id() != 0) {
                    queryParams.add(new BasicNameValuePair("productionMethod_id", String.valueOf(filterDto.getProductionMethod_id())));
                }
                if (filterDto.getDepth_min() != 0) {
                    queryParams.add(new BasicNameValuePair("depth_min", String.valueOf(filterDto.getDepth_min())));
                }
                if (filterDto.getDepth_max() != 0) {
                    queryParams.add(new BasicNameValuePair("depth_max", String.valueOf(filterDto.getDepth_max())));
                }
                if (filterDto.getMaterialBlottingPressure_calculated_min() != 0) {
                    queryParams.add(new BasicNameValuePair("materialBlottingPressure_calculated_min", String.valueOf(filterDto.getMaterialBlottingPressure_calculated_min())));
                }
                if (filterDto.getMaterialBlottingPressure_calculated_max() != 0) {
                    queryParams.add(new BasicNameValuePair("materialBlottingPressure_calculated_max", String.valueOf(filterDto.getMaterialBlottingPressure_calculated_max())));
                }
                if (filterDto.getMaterialBlottingTime_calculated_min() != 0) {
                    queryParams.add(new BasicNameValuePair("materialBlottingTime_calculated_min", String.valueOf(filterDto.getMaterialBlottingTime_calculated_min())));
                }
                if (filterDto.getMaterialBlottingTime_calculated_max() != 0) {
                    queryParams.add(new BasicNameValuePair("materialBlottingTime_calculated_max", String.valueOf(filterDto.getMaterialBlottingTime_calculated_max())));
                }
                if (filterDto.getWaterPermeability_calculated_min() != 0) {
                    queryParams.add(new BasicNameValuePair("waterPermeability_calculated_min", String.valueOf(filterDto.getWaterPermeability_calculated_min())));
                }
                if (filterDto.getWaterPermeability_calculated_max() != 0) {
                    queryParams.add(new BasicNameValuePair("waterPermeability_calculated_max", String.valueOf(filterDto.getWaterPermeability_calculated_max())));
                }
                if (filterDto.getTotalThermalResistance_calculated_min() != 0) {
                    queryParams.add(new BasicNameValuePair("totalThermalResistance_calculated_min", String.valueOf(filterDto.getTotalThermalResistance_calculated_min())));
                }
                if (filterDto.getTotalThermalResistance_calculated_max() != 0) {
                    queryParams.add(new BasicNameValuePair("totalThermalResistance_calculated_max", String.valueOf(filterDto.getTotalThermalResistance_calculated_max())));
                }
                if (filterDto.getRelativeBlottingPressureAfterLoad_relativeValuation_min() != 0) {
                    queryParams.add(new BasicNameValuePair("relativeBlottingPressureAfterLoad_relativeValuation_min", String.valueOf(filterDto.getRelativeBlottingPressureAfterLoad_relativeValuation_min())));
                }
                if (filterDto.getRelativeBlottingPressureAfterLoad_relativeValuation_max() != 0) {
                    queryParams.add(new BasicNameValuePair("relativeBlottingPressureAfterLoad_relativeValuation_max", String.valueOf(filterDto.getRelativeBlottingPressureAfterLoad_relativeValuation_max())));
                }

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + "/material")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(allValues.getUser().getEmail(), allValues.getUser().getPassword()))
                        .setHeader("Content-Type", "application/json")
                        .addParameter("page", String.valueOf(allValues.getPaginationDto().getPage()))
                        .addParameter("perPage", String.valueOf(allValues.getPaginationDto().getPerPage()))
                        .addParameters(queryParams.toArray(NameValuePair[]::new))
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() != 200) {
                    filterMaterialsModel.setError(true);
                } else {
                    filterMaterialsModel.setPartialMaterials(JsonToClass.parseToListObject(PartialMaterialEntity.class, response).toArray(PartialMaterialEntity[]::new));
                    filterMaterialsModel.setError(false);
                    filterMaterialsModel.setTotalCount(Integer.parseInt(response.getFirstHeader("x-total-count").getValue()));
                }
                futureMaterials.complete(filterMaterialsModel);
            }
        };

        Thread getFilterMaterialsThread = new Thread(runnable);
        getFilterMaterialsThread.start();

        return futureMaterials.get();
    }
}
