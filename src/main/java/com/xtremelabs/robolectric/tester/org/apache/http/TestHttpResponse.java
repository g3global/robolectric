package com.xtremelabs.robolectric.tester.org.apache.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHeader;

import com.xtremelabs.robolectric.shadows.StatusLineStub;

public class TestHttpResponse extends HttpResponseStub {

    private int statusCode;
    private String responseBody;
    private TestStatusLine statusLine = new TestStatusLine();
    private TestHttpEntity httpEntity = new TestHttpEntity();
    
    private List<Header> headers = new ArrayList<Header>();  
    
    @Override
    public void setHeader(Header header) {
    	headers.add(header);
    }
    
    @Override
    public void setHeader(String s, String s1) {
    	Header h = new BasicHeader(s, s1);
    	setHeader(h);
    }
    
    @Override
    public void setHeaders(Header[] headers) {
    	for(Header h: headers){
    		setHeader(h);
    	}
    }
    
    @Override
    public Header[] getAllHeaders() {
    	Header[] headersArray = new Header[this.headers.size()];
    	for(int counter = 0; counter < this.headers.size(); counter++){
    		headersArray[counter] = this.headers.get(counter);
    	}
    	return headersArray;
    }

    public TestHttpResponse(int statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public TestHttpResponse(int statusCode, String responseBody, Header contentType) {
        this(statusCode, responseBody);
        setHeader(contentType);
    }

    @Override public StatusLine getStatusLine() {
        return statusLine;
    }

    @Override public HttpEntity getEntity() {
        return httpEntity;
    }

    public class TestHttpEntity extends HttpEntityStub {
        @Override public long getContentLength() {
            return responseBody.length();
        }
        
        @Override public Header getContentType() {
            return getFirstHeader("Content-Type");
        }
        
        @Override public boolean isStreaming() {
            return true;
        }
        
        @Override public boolean isRepeatable() {
            return true;
        }

        @Override public InputStream getContent() throws IOException, IllegalStateException {
            return new ByteArrayInputStream(responseBody.getBytes());
        }

        @Override public void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(responseBody.getBytes());
        }

        @Override public void consumeContent() throws IOException {
        }
    }

    public class TestStatusLine extends StatusLineStub {
        @Override public ProtocolVersion getProtocolVersion() {
            return new HttpVersion(1, 0);
        }

        @Override public int getStatusCode() {
            return statusCode;
        }

        @Override public String getReasonPhrase() {
            return "HTTP status " + statusCode;
        }
    }
}
