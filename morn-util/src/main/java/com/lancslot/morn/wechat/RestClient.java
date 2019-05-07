package com.lancslot.morn.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 *
 * 如果需要使用请在spring配置文件中显示声明
 */
public class RestClient {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private RestTemplate restTemplate;

	public JSONObject postForObject(String url, Object request) {
		logger.info("postForObject, url: {}, request: {}", url, JSONObject.toJSONString(request));
		String result = restTemplate.postForObject(url, request, String.class);
		logger.info("postForObject, result: {}", result);
		return JSONObject.parseObject(result);
	}

	public JSONObject postForObject(String url, Object request, Object data) {
		logger.info("postForObject, url: {}, request: {}, data: {}", url, JSONObject.toJSONString(request), JSONObject.toJSONString(data));
		String result = restTemplate.postForObject(url, request, String.class, data);
		logger.info("postForObject, result: {}", result);
		return JSONObject.parseObject(result);
	}

	public <T> T postForObject(String url, Object request, Class<T> clazz) {
		logger.info("postForObject, url: {}, request: {}, class: {}", url, JSONObject.toJSONString(request), clazz.getName());
		T result = restTemplate.postForObject(url, request, clazz);
		logger.info("postForObject, result: {}", JSONObject.toJSONString(result));
		return result;
	}

	public JSONObject getForObject(String url, Map<String, ?> uriVariables) {
		logger.info("getForObject, url: {}, urlVariables: {}", url, JSONObject.toJSONString(uriVariables));
		String result = restTemplate.getForObject(url, String.class, uriVariables);
		logger.info("getForObject, result: {}", result);
		return JSONObject.parseObject(result);
	}

	public JSONObject getForObject(String url, Object... urlVariables) {
		logger.info("getForObject, url: {}, urlVariables: {}", url, JSONObject.toJSONString(urlVariables));
		String result = restTemplate.getForObject(url, String.class, urlVariables);
		logger.info("getForObject, result: {}", result);
		return JSONObject.parseObject(result);
	}

	public JSONObject jscode2session(String code , String appId , String secret) {
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/sns/jscode2session?appid=")
				.append(appId)
				.append("&secret=")
				.append(secret)
				.append("&js_code=")
				.append(code)
				.append("&grant_type=authorization_code");

		String result = restTemplate.getForObject(url.toString() , String.class);
		logger.debug("{}\n\t{}" , url.toString() , result);
		return JSONObject.parseObject(result);
	}

	public JSONArray getForArray(String url, Object... urlVariables) {
		logger.info("getForArray, url: {}, urlVariables: {}", url, JSONObject.toJSONString(urlVariables));
		String result = restTemplate.getForObject(url, String.class, urlVariables);
		logger.info("getForArray, result: {}", result);
		return JSONObject.parseArray(result);
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
