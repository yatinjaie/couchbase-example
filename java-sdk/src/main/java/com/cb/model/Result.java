package com.cb.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;

import lombok.ToString;

/**
 * A standardized result format for successful responses.
 *
 */
@ToString
public class Result<T> {

    private final T data;
    private final String[] context;

    private Result(T data, String... contexts) {
        this.data = data;
        this.context = contexts;
    }

    public static <T> Result<T> of(T data, String... contexts) {
        return new Result<T>(data, contexts);
    }

    public T getData() {
        return data;
    }

    public String[] getContext() {
        return context;
    }

    public static Result<List<Map<String, Object>>> get(QueryResult result, String query) {
        List<JsonObject> resultObjects = result.rowsAsObject();
        List<Map<String, Object>> data = new LinkedList<Map<String, Object>>();
        for (JsonObject obj : resultObjects) {
            data.add(obj.toMap());
        }
        return Result.of(data, query);
    }

}
