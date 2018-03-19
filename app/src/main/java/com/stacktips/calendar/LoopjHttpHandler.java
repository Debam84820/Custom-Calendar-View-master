package com.stacktips.calendar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by DELL on 09-03-2018.
 */

public class LoopjHttpHandler extends JsonHttpResponseHandler {

    private String TAG = LoopjHttpHandler.class.getSimpleName();

    private AsyncHttpClient client;
    private RequestParams params;
    private String method, url;
    int url_no;
    private HashMap<String, String> strHashMap;
    private LinkedHashMap<String, File> fileHashMap;

    public OnJsonResponseListener listener;

    public interface OnJsonResponseListener {
        void getJsonResponseCallBack(String result, int urlNo, int status_code);
    }

    public LoopjHttpHandler(OnJsonResponseListener listener){
        this.listener = listener;
    }

    private void setUpClientParams(){
        System.out.println("Inside setUpClientParams method");
        client = new AsyncHttpClient();
        params = new RequestParams();
    }

    private void setUpSerVerParams(){
        System.out.println("Inside setUpSerVerParams method");
        /*For only string pair
        * */
        if (strHashMap!= null){
            for (Map.Entry<String, String>entry: strHashMap.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                params.put(key, value);
            }
        }

        /*For only file pair
        * */
        if (fileHashMap!= null){
            for (Map.Entry<String, File>entry: fileHashMap.entrySet()){
                String key = entry.getKey();
                File value = entry.getValue();
                try {
                    params.put(key, value);
                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFound exception thrown from setupParams method");
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void makeRequest(int url_no){
        System.out.println("Inside makeRequest method");
        this.url_no = url_no;
        if (method.equalsIgnoreCase("GET")){
            client.get(url, params, this);
        }
        if (method.equalsIgnoreCase("POST")){
            client.post(url, params, this);
        }
    }

    public void callRequest(int url_no){
        System.out.println("Inside callRequest method`");
        setUpClientParams();
        if (strHashMap!= null || fileHashMap!= null){
            setUpSerVerParams();
        }
        makeRequest(url_no);
    }

    public void makeHttpRequest(String url, String method, int url_no){
        this.url = url;
        this.method = method;
        callRequest(url_no);
    }

    public void makeHttpRequest(String url, String method, HashMap<String, String> hashMap, int url_no){
        this.url = url;
        this.method = method;
        this.strHashMap = hashMap;
        callRequest(url_no);
    }

    public void makeHttpRequest(String url, String method, HashMap<String, String> hashMap, LinkedHashMap<String, File> fileHashMap, int url_no){
        this.url = url;
        this.method = method;
        this.strHashMap = hashMap;
        this.fileHashMap = fileHashMap;
        callRequest(url_no);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        super.onSuccess(statusCode, headers, response);
        System.out.println("Success Status Code from JSONArray: "+statusCode);
        listener.getJsonResponseCallBack(response.toString(), url_no, statusCode);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        System.out.println("Success Status Code from JSONObject: "+statusCode);
        listener.getJsonResponseCallBack(response.toString(), url_no, statusCode);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        super.onSuccess(statusCode, headers, responseString);
        System.out.println("Success Status Code from responseString: "+statusCode);
        listener.getJsonResponseCallBack(responseString, url_no, statusCode);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        System.out.println("Failure Status Code from responseString: "+statusCode);
        listener.getJsonResponseCallBack(responseString, url_no, statusCode);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        System.out.println("Failure Status Code from JSONArray: "+statusCode);
        listener.getJsonResponseCallBack(errorResponse.toString(), url_no, statusCode);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        System.out.println("Failure Status Code from JSONObject: "+statusCode);
        listener.getJsonResponseCallBack(errorResponse.toString(), url_no, statusCode);
    }

    @Override
    public void onRetry(int retryNo) {
        super.onRetry(retryNo);
    }
}
