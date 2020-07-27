package com.cb.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cb.model.Result;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;

public class Common {

    public static Result<List<Map<String, Object>>> getResult(QueryResult result, String query) {

        List<JsonObject> resultObjects = result.rowsAsObject();
        List<Map<String, Object>> data = new LinkedList<Map<String, Object>>();
        for (JsonObject obj : resultObjects) {
            data.add(obj.toMap());
        }
        return Result.of(data, query);
    }
}
