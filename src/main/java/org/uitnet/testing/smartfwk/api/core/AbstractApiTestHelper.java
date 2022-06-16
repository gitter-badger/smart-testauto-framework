/*
 * SmartTestAutoFramework
 * Copyright 2021 and beyond
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.uitnet.testing.smartfwk.api.core;

import java.io.File;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.uitnet.testing.smartfwk.api.core.defaults.ApiTestManager;
import org.uitnet.testing.smartfwk.api.core.support.HttpMultipartRequest;
import org.uitnet.testing.smartfwk.api.core.support.HttpRequest;
import org.uitnet.testing.smartfwk.api.core.support.HttpResponse;
import org.uitnet.testing.smartfwk.api.core.support.HttpSession;
import org.uitnet.testing.smartfwk.api.core.support.MultipartData;
import org.uitnet.testing.smartfwk.ui.core.config.TestConfigManager;
import org.uitnet.testing.smartfwk.ui.core.config.UserProfile;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class AbstractApiTestHelper implements ApiAuthenticationProvider {
	protected String appName;
	protected String baseURL;
	protected HttpSession session;
	protected TestConfigManager testConfigManager;
	protected String activeProfileName;
	protected UserProfile activeUserProfile;
	protected int sessionExpiryDurationInSeconds;
	protected long lastRequestAccessTimeInMs;
	protected boolean logoutRequest = false;
	protected ApiTestManager apiTestManager;
	protected String targetServerName;

	public AbstractApiTestHelper(String appName, int sessionExpiryDurationInSeconds) {
		this.appName = appName;
		this.sessionExpiryDurationInSeconds = sessionExpiryDurationInSeconds;
		testConfigManager = TestConfigManager.getInstance();

	}
	
	public void setApiTestManager(ApiTestManager apiTestManager) {
		this.apiTestManager = apiTestManager;
	}
	
	public void setTargetServerName(String targetServerName) {
		this.targetServerName = targetServerName;
	}

	protected void setBaseURL(String baseUrlKey) {
		baseURL = testConfigManager.getAppConfig(appName).getApiConfig().getAdditionalPropertyValue(baseUrlKey, String.class);
	}

	public HttpSession setActiveProfileName(String profileName) {
		if (activeProfileName == null || "".equals(activeProfileName)) {
			authenticate(profileName);
			activeProfileName = profileName;
			activeUserProfile = testConfigManager.getUserProfile(appName, profileName);
			lastRequestAccessTimeInMs = Calendar.getInstance().getTimeInMillis();

		} else if (!activeProfileName.equals(profileName)) {
			if(apiTestManager == null) {
				logout();
			}
			authenticate(profileName);
			activeProfileName = profileName;
			activeUserProfile = testConfigManager.getUserProfile(appName, profileName);
			lastRequestAccessTimeInMs = Calendar.getInstance().getTimeInMillis();
		}
		
		return session;
	}
	
	protected void authenticate(String profileName) {
		if(apiTestManager != null) {
			ApiAuthenticationProvider authProvider = apiTestManager.getAuthenticationProvider(appName, targetServerName, profileName);
			session = authProvider.login(testConfigManager.getAppConfig(appName).getApiConfig(),
					testConfigManager.getUserProfile(appName, profileName));
		} else {
			session = login(testConfigManager.getAppConfig(appName).getApiConfig(),
					testConfigManager.getUserProfile(appName, profileName));
		}
	}
	
	public String getActiveProfileName() {
		return activeProfileName;
	}

	public HttpResponse httpGet(String relativeUrl, String responseContentType, Integer connectTimeoutInSeconds,
			Integer readTimeoutInSeconds) {
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(readTimeoutInSeconds == null ? 60 : readTimeoutInSeconds, TimeUnit.SECONDS)
				.connectTimeout(connectTimeoutInSeconds == null ? 30 : connectTimeoutInSeconds, TimeUnit.SECONDS)
				.build();

		String targetURL = baseURL + "/" + relativeUrl;
		okhttp3.Request.Builder requestBuilder = new Request.Builder().get().url(targetURL);

		// Add headers
		if (session != null && session.getParams() != null && session.getParams().size() > 0) {
			for (Map.Entry<String, String> kv : session.getParams().entrySet()) {
				requestBuilder.addHeader(kv.getKey(), kv.getValue());
			}
		}

		// Add cookies
		if (session != null && session.getCookies() != null && session.getCookies().size() > 0) {
			String cookie = null;
			for (Map.Entry<String, String> kv : session.getCookies().entrySet()) {
				if (cookie == null) {
					cookie = kv.getKey() + "=" + kv.getValue();
				} else {
					cookie = kv + ";" + kv.getKey() + "=" + kv.getValue();
				}
			}

			if (cookie != null) {
				requestBuilder.addHeader("Cookie", cookie);
			}
		}

		requestBuilder.removeHeader("Content-Type");
		if (responseContentType == null || "".equals(responseContentType.trim())) {
			requestBuilder.removeHeader("Accept");
		} else {
			requestBuilder.addHeader("Accept", responseContentType);
		}

		return prepareResponse(client, requestBuilder, responseContentType != null, targetURL);
	}

	public HttpResponse httpDelete(String relativeUrl, String responseContentType, Integer connectTimeoutInSeconds,
			Integer readTimeoutInSeconds) {
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(readTimeoutInSeconds == null ? 60 : readTimeoutInSeconds, TimeUnit.SECONDS)
				.connectTimeout(connectTimeoutInSeconds == null ? 30 : connectTimeoutInSeconds, TimeUnit.SECONDS)
				.build();

		String targetURL = baseURL + "/" + relativeUrl;
		okhttp3.Request.Builder requestBuilder = new Request.Builder().delete().url(targetURL);

		// Add headers
		if (session != null && session.getParams() != null && session.getParams().size() > 0) {
			for (Map.Entry<String, String> kv : session.getParams().entrySet()) {
				requestBuilder.addHeader(kv.getKey(), kv.getValue());
			}
		}

		// Add cookies
		if (session != null && session.getCookies() != null && session.getCookies().size() > 0) {
			String cookie = null;
			for (Map.Entry<String, String> kv : session.getCookies().entrySet()) {
				if (cookie == null) {
					cookie = kv.getKey() + "=" + kv.getValue();
				} else {
					cookie = kv + ";" + kv.getKey() + "=" + kv.getValue();
				}
			}

			if (cookie != null) {
				requestBuilder.addHeader("Cookie", cookie);
			}
		}

		requestBuilder.removeHeader("Content-Type");
		if (responseContentType == null || "".equals(responseContentType.trim())) {
			requestBuilder.removeHeader("Accept");
		} else {
			requestBuilder.addHeader("Accept", responseContentType);
		}

		return prepareResponse(client, requestBuilder, responseContentType != null, targetURL);
	}

	public HttpResponse httpPost(String relativeUrl, HttpRequest request, Integer connectTimeoutInSeconds,
			Integer readTimeoutInSeconds) {
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(readTimeoutInSeconds == null ? 60 : readTimeoutInSeconds, TimeUnit.SECONDS)
				.connectTimeout(connectTimeoutInSeconds == null ? 30 : connectTimeoutInSeconds, TimeUnit.SECONDS)
				.build();

		String targetURL = baseURL + "/" + relativeUrl;
		okhttp3.Request.Builder requestBuilder = new Request.Builder()
				.post(RequestBody.create(request.getPayload(), MediaType.parse(request.getPayloadType())))
				.url(targetURL);

		// Add headers
		if (session != null && session.getParams() != null && session.getParams().size() > 0) {
			for (Map.Entry<String, String> kv : session.getParams().entrySet()) {
				requestBuilder.addHeader(kv.getKey(), kv.getValue());
			}
		}

		// Add cookies
		if (session != null && session.getCookies() != null && session.getCookies().size() > 0) {
			String cookie = null;
			for (Map.Entry<String, String> kv : session.getCookies().entrySet()) {
				if (cookie == null) {
					cookie = kv.getKey() + "=" + kv.getValue();
				} else {
					cookie = kv + ";" + kv.getKey() + "=" + kv.getValue();
				}
			}

			if (cookie != null) {
				requestBuilder.addHeader("Cookie", cookie);
			}
		}

		if (request.getResponseContentType() == null || "".equals(request.getResponseContentType().trim())) {
			requestBuilder.removeHeader("Accept");
		} else {
			requestBuilder.addHeader("Accept", request.getResponseContentType());
		}

		return prepareResponse(client, requestBuilder, request.getResponseContentType() != null, targetURL);
	}

	public HttpResponse httpPut(String relativeUrl, HttpRequest request, Integer connectTimeoutInSeconds,
			Integer readTimeoutInSeconds) {
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(readTimeoutInSeconds == null ? 60 : readTimeoutInSeconds, TimeUnit.SECONDS)
				.connectTimeout(connectTimeoutInSeconds == null ? 30 : connectTimeoutInSeconds, TimeUnit.SECONDS)
				.build();

		String targetURL = baseURL + "/" + relativeUrl;
		okhttp3.Request.Builder requestBuilder = new Request.Builder()
				.put(RequestBody.create(request.getPayload(), MediaType.parse(request.getPayloadType())))
				.url(targetURL);

		// Add headers
		if (session != null && session.getParams() != null && session.getParams().size() > 0) {
			for (Map.Entry<String, String> kv : session.getParams().entrySet()) {
				requestBuilder.addHeader(kv.getKey(), kv.getValue());
			}
		}

		// Add cookies
		if (session != null && session.getCookies() != null && session.getCookies().size() > 0) {
			String cookie = null;
			for (Map.Entry<String, String> kv : session.getCookies().entrySet()) {
				if (cookie == null) {
					cookie = kv.getKey() + "=" + kv.getValue();
				} else {
					cookie = kv + ";" + kv.getKey() + "=" + kv.getValue();
				}
			}

			if (cookie != null) {
				requestBuilder.addHeader("Cookie", cookie);
			}
		}

		if (request.getResponseContentType() == null || "".equals(request.getResponseContentType().trim())) {
			requestBuilder.removeHeader("Accept");
		} else {
			requestBuilder.addHeader("Accept", request.getResponseContentType());
		}

		return prepareResponse(client, requestBuilder, request.getResponseContentType() != null, targetURL);
	}

	public HttpResponse httpUploadFormFiles(String relativeUrl, HttpMultipartRequest request,
			Integer connectTimeoutInSeconds, Integer readTimeoutInSeconds) {
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(readTimeoutInSeconds == null ? 60 : readTimeoutInSeconds, TimeUnit.SECONDS)
				.connectTimeout(connectTimeoutInSeconds == null ? 30 : connectTimeoutInSeconds, TimeUnit.SECONDS)
				.build();

		okhttp3.MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

		for (MultipartData part : request.getParts()) {
			if (part.getContentType() == null) {
				multipartBodyBuilder.addFormDataPart(part.getName(), part.getFileName());
			} else {
				multipartBodyBuilder.addFormDataPart(part.getName(), part.getFileName(),
						RequestBody.create(new File(part.getFilePath()), MediaType.parse(part.getContentType())));
			}
		}

		String targetURL = baseURL + "/" + relativeUrl;
		okhttp3.Request.Builder requestBuilder = new Request.Builder().post(multipartBodyBuilder.build())
				.url(targetURL);

		// Add headers
		if (session != null && session.getParams() != null && session.getParams().size() > 0) {
			for (Map.Entry<String, String> kv : session.getParams().entrySet()) {
				requestBuilder.addHeader(kv.getKey(), kv.getValue());
			}
		}

		// Add cookies
		if (session != null && session.getCookies() != null && session.getCookies().size() > 0) {
			String cookie = null;
			for (Map.Entry<String, String> kv : session.getCookies().entrySet()) {
				if (cookie == null) {
					cookie = kv.getKey() + "=" + kv.getValue();
				} else {
					cookie = kv + ";" + kv.getKey() + "=" + kv.getValue();
				}
			}

			if (cookie != null) {
				requestBuilder.addHeader("Cookie", cookie);
			}
		}

		if (request.getResponseContentType() == null || "".equals(request.getResponseContentType().trim())) {
			requestBuilder.removeHeader("Accept");
		} else {
			requestBuilder.addHeader("Accept", request.getResponseContentType());
		}

		requestBuilder.addHeader("Content-Type", request.getContentType());

		return prepareResponse(client, requestBuilder, request.getResponseContentType() != null, targetURL);
	}

	protected HttpResponse prepareResponse(OkHttpClient client, okhttp3.Request.Builder requestBuilder,
			boolean expectResponseBody, String targetURL) {
		if(session != null && !logoutRequest) {
			if(isSessionExpired()) {
				logout();
				setActiveProfileName(activeProfileName);
			} else {
				lastRequestAccessTimeInMs = Calendar.getInstance().getTimeInMillis();
			}
		}
		
		HttpResponse httpResponse = new HttpResponse();
		try (Response response = client.newCall(requestBuilder.build()).execute()) {
			httpResponse.setCode(response.code());
			httpResponse.setMessage(response.message());
			if (expectResponseBody) {
				ResponseBody body = response.body();
				httpResponse.setPayload(body.string());
			}

		} catch (Exception ex) {
			httpResponse.setCode(400);
			httpResponse.setMessage("Bad Request");
			Assert.fail("Failed to make API call on target URL: " + targetURL, ex);
		} finally {
			if(logoutRequest) {
				logoutRequest = false;
				session = null;
			}
		}

		return httpResponse;
	}
	
	protected boolean isSessionExpired() {
		long currTimeInMs = Calendar.getInstance().getTimeInMillis();
		long durationInSeconds = (currTimeInMs - lastRequestAccessTimeInMs) / 1000;
		if(durationInSeconds >=  sessionExpiryDurationInSeconds) {
			return true;
		}
		return false;
	}

	public UserProfile getActiveUserProfile() {
		return activeUserProfile;
	}

	public int getSessionExpiryDurationInSeconds() {
		return sessionExpiryDurationInSeconds;
	}

	public long getLastRequestAccessTimeInMs() {
		return lastRequestAccessTimeInMs;
	}
	
	public AbstractApiTestHelper clone() {
		try {
			return (AbstractApiTestHelper) this.getClass().getDeclaredConstructors()[0].newInstance();
		} catch(Exception ex) {
			Assert.fail("Failed to clone '" + this.getClass().getName()+ "' class object.", ex);
		}
		return null;
	}
}
