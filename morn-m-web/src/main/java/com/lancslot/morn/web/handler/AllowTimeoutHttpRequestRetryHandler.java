package com.lancslot.morn.web.handler;

import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

import javax.net.ssl.SSLException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class AllowTimeoutHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {

	public AllowTimeoutHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled) {
		// Remove InterruptedIOException class from the list and allow timeout case to retry
		super(retryCount, requestSentRetryEnabled, Arrays.asList(UnknownHostException.class, ConnectException.class, SSLException.class));
	}

}
