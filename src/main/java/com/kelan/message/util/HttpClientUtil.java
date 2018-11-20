package com.kelan.message.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/20 20:06
 * @see
 */
public class HttpClientUtil {
  public static String get(String url, List<NameValuePair> params) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response  = null;
    String body = null;
    try {
      // 转换为键值对
      String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
      // 创建Get请求
      HttpGet httpGet = new HttpGet(url + "?" + str);
      response = httpClient.execute(httpGet);
      // 得到响应体
      HttpEntity entity = response.getEntity();
      if(entity != null){
        body = EntityUtils.toString(entity, "UTF-8");
      }
      EntityUtils.consume(entity);
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      //消耗实体内容
      if(response != null){
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      //关闭相应 丢弃http连接
      if(httpClient != null){
        try {
          httpClient.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return body;
  }

  public static String post(String url, List<NameValuePair> params) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    CloseableHttpResponse response  = null;
    // 设置参数
    String body = null;
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
      response = httpClient.execute(httpPost);
      HttpEntity entity = response.getEntity();
      if(entity != null){
        body = EntityUtils.toString(entity, "UTF-8");
      }
      EntityUtils.consume(entity);
    } catch (Exception  e) {
      e.printStackTrace();
    }finally {
      if(response != null){
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(httpClient != null){
        try {
          httpClient.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return body;
  }
}
