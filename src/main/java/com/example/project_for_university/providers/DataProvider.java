package com.example.project_for_university.providers;

import com.example.project_for_university.dto.AllValues;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface DataProvider {
    void setData(AllValues allValues) throws IOException, ExecutionException, InterruptedException;
}
