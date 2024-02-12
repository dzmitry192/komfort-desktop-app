package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.MaterialFilterDto;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.dto.forBackend.update.UpdateMaterialDto;
import com.example.project_for_university.enums.ErrorMessage;
import com.example.project_for_university.enums.ServiceEnum;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.AbstractResponse;
import com.example.project_for_university.service.models.GetMaterialReportResponse;
import com.example.project_for_university.service.models.UpdateMaterialResponse;
import com.example.project_for_university.service.models.material.CreateMaterialRequestDto;
import com.example.project_for_university.service.models.CreateMaterialResponse;
import com.example.project_for_university.service.models.get.GetMaterialsResponse;
import com.example.project_for_university.utils.AuthUtils;
import com.example.project_for_university.utils.ExceptionMessageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.stage.DirectoryChooser;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MaterialService {

    public static final MaterialService INSTANCE = new MaterialService();

    private final UpdateMaterialResponse updateMaterialResponse = new UpdateMaterialResponse();
    private final AbstractResponse deleteResponse = new AbstractResponse();

    private final GetMaterialsResponse getMaterialsResponse = new GetMaterialsResponse();
    private final GetMaterialReportResponse getMaterialReportResponse = new GetMaterialReportResponse();
    private final CreateMaterialResponse createMaterialResponse = new CreateMaterialResponse();

    @SneakyThrows
    public GetMaterialsResponse getFilterMaterialsThread(AllValues allValues) {
        CompletableFuture<GetMaterialsResponse> futureMaterials = new CompletableFuture<>();
        MaterialFilterDto filterDto = allValues.getMaterialFilterDto();

        Runnable runnable = () -> {
            CloseableHttpResponse response;
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

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
                        .setUri(Main.host.getValue() + UrlRoutes.GET_MATERIALS.getName())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(allValues.getUser().getEmail(), allValues.getUser().getPassword()))
                        .setHeader("Content-Type", "application/json")
                        .addParameter("page", String.valueOf(allValues.getPaginationDto().getPage()))
                        .addParameter("perPage", String.valueOf(allValues.getPaginationDto().getPerPage()))
                        .addParameters(queryParams.toArray(NameValuePair[]::new))
                        .build();

                response = httpClient.execute(httpGet);

                if (response.getStatusLine().getStatusCode() != 200) {
                    getMaterialsResponse.setError(true);
                    getMaterialsResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    getMaterialsResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.MATERIAL, response.getStatusLine().getStatusCode(), null));
                } else {
                    try {
                        getMaterialsResponse.setPartialMaterials(JsonToClass.parseToListObject(PartialMaterialEntity.class, response).toArray(PartialMaterialEntity[]::new));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getMaterialsResponse.setError(false);
                    getMaterialsResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    getMaterialsResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.MATERIAL, response.getStatusLine().getStatusCode(), null));
                    getMaterialsResponse.setTotalCount(Integer.parseInt(response.getFirstHeader("x-total-count").getValue()));
                }

                futureMaterials.complete(getMaterialsResponse);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        };

        Thread getFilterMaterialsThread = new Thread(runnable);
        getFilterMaterialsThread.start();

        return futureMaterials.get();
    }

    @SneakyThrows
    public AbstractResponse getMaterialReport(int materialId, String filePath, String email, String password) {
        CompletableFuture<AbstractResponse> completableFuture = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                String fileURL =  Main.host.getValue() + "/material/" + materialId + "/report"; // Замените на URL вашего файла

                try {
                    URL url = new URL(fileURL);
                    URLConnection connection = url.openConnection();

                    connection.setRequestProperty(AuthUtils.header, AuthUtils.getAuth(email, password));
                    try (InputStream in = new BufferedInputStream(connection.getInputStream());
                         FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }

                        AbstractResponse response = new AbstractResponse();
                        if (connection instanceof HttpURLConnection) {
                            int statusCode = ((HttpURLConnection) connection).getResponseCode();
                            if (statusCode >= 400) {
                                response.setError(true);
                                response.setMessage(ErrorMessage.MATERIAL_NOT_FOUND.getMessage());
                            } else {
                                response.setError(false);
                            }
                        }
                        completableFuture.complete(response);
                    }catch (Exception e) {
                        AbstractResponse response = new AbstractResponse();
                        response.setError(true);
                        response.setMessage(ErrorMessage.MATERIAL_NOT_FOUND.getMessage());
                        completableFuture.complete(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread createMaterialThread = new Thread(runnable);
        createMaterialThread.start();

        return completableFuture.get();
    }

    @SneakyThrows
    public CreateMaterialResponse create(CreateMaterialRequestDto createMaterialRequestDto, String email, String password) {
        CompletableFuture<CreateMaterialResponse> completableFuture = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();

                    for (File file : createMaterialRequestDto.getImages()) {
                        builder.addPart("images", new FileBody(file, ContentType.IMAGE_PNG, file.getName()));
                    }

                    builder.addTextBody("material", objectMapper.writeValueAsString(createMaterialRequestDto.getMaterial()), ContentType.APPLICATION_JSON);
                    builder.addTextBody("condition", objectMapper.writeValueAsString(createMaterialRequestDto.getCondition()), ContentType.APPLICATION_JSON);
                    builder.addTextBody("waterproofFunction", objectMapper.writeValueAsString(createMaterialRequestDto.getWaterproofFunction()), ContentType.APPLICATION_JSON);
                    builder.addTextBody("homeostasisFunction", objectMapper.writeValueAsString(createMaterialRequestDto.getHomeostasisFunction()), ContentType.APPLICATION_JSON);
                    builder.addTextBody("reliabilityFunction", objectMapper.writeValueAsString(createMaterialRequestDto.getReliabilityFunction()), ContentType.APPLICATION_JSON);
                    builder.addTextBody("estimation", objectMapper.writeValueAsString(createMaterialRequestDto.getEstimation()), ContentType.APPLICATION_JSON);

                    HttpEntity multipart = builder.build();

                    HttpUriRequest httpPost = RequestBuilder.post()
                            .setUri(Main.host.getValue() + UrlRoutes.POST_MATERIAL.getName())
                            .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                            .setEntity(multipart)
                            .build();

                    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                        if(response.getStatusLine().getStatusCode() == 201) {
                            createMaterialResponse.setMaterial(JsonToClass.parseToObject(PartialMaterialEntity.class, response));
                            createMaterialResponse.setError(false);
                            createMaterialResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        } else {
                            createMaterialResponse.setError(true);
                            createMaterialResponse.setStatusCode(response.getStatusLine().getStatusCode());
                            createMaterialResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.MATERIAL, response.getStatusLine().getStatusCode(), ExceptionMessageUtil.getMessageFromResponse(response)));
                        }
                        completableFuture.complete(createMaterialResponse);
                    } catch (IOException e) {
                        throw new IOException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread createMaterialThread = new Thread(runnable);
        createMaterialThread.start();

        return completableFuture.get();
    }

    @SneakyThrows
    public UpdateMaterialResponse update(UpdateMaterialDto updateMaterialDto, int materialId, String email, String password) {
        CompletableFuture<UpdateMaterialResponse> completableFuture = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name", updateMaterialDto.getName());
                    jsonObject.addProperty("manufacturer", updateMaterialDto.getManufacturer());
                    jsonObject.addProperty("description", updateMaterialDto.getDescription());

                    HttpUriRequest httpPatch = RequestBuilder.patch()
                            .setUri(Main.host.getValue() + UrlRoutes.PATCH_MATERIAL.getName() + materialId)
                            .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                            .setHeader("Content-Type", "application/json")
                            .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                            .build();

                    try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                        if(response.getStatusLine().getStatusCode() == 200) {
                            updateMaterialResponse.setError(false);
                            updateMaterialResponse.setStatusCode(response.getStatusLine().getStatusCode());
                            updateMaterialResponse.setMaterial(JsonToClass.parseToObject(PartialMaterialEntity.class, response));
                        } else {
                            updateMaterialResponse.setError(true);
                            updateMaterialResponse.setStatusCode(response.getStatusLine().getStatusCode());
                            updateMaterialResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.MATERIAL, response.getStatusLine().getStatusCode(), ExceptionMessageUtil.getMessageFromResponse(response)));
                        }
                        completableFuture.complete(updateMaterialResponse);
                    } catch (IOException e) {
                        throw new IOException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread createMaterialThread = new Thread(runnable);
        createMaterialThread.start();

        return completableFuture.get();
    }

    @SneakyThrows
    public AbstractResponse delete(int materialId, String email, String password) {
        CompletableFuture<AbstractResponse> completableFuture = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    HttpUriRequest httpDelete = RequestBuilder.delete()
                            .setUri(Main.host.getValue() + UrlRoutes.DELETE_MATERIAL_BY_ID.getName() + materialId)
                            .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                            .build();

                    try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                        if(response.getStatusLine().getStatusCode() == 200) {
                            deleteResponse.setError(false);
                            deleteResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        } else {
                            deleteResponse.setError(true);
                            deleteResponse.setStatusCode(response.getStatusLine().getStatusCode());
                            deleteResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.MATERIAL, response.getStatusLine().getStatusCode(), ExceptionMessageUtil.getMessageFromResponse(response)));
                        }
                        completableFuture.complete(createMaterialResponse);
                    } catch (IOException e) {
                        throw new IOException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread createMaterialThread = new Thread(runnable);
        createMaterialThread.start();

        return completableFuture.get();
    }
}