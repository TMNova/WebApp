package ru.lanit;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class FirstFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("Filter is init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURL().toString();

		String sessionID = ((HttpServletRequest) request).getRequestedSessionId();

		System.out.println("URL is: " + url);
		System.out.println("JSESSIONID: " + sessionID);

		ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

		chain.doFilter(request, responseWrapper);

		String servletResponse = responseWrapper.toString();

		System.out.println(servletResponse);

		class ContentLengthWrapper extends HttpServletResponseWrapper{
			public ContentLengthWrapper(HttpServletResponse response) {
				super(response);
			}
		};

		ContentLengthWrapper contextLengthWrapper = new ContentLengthWrapper((HttpServletResponse)response);

		chain.doFilter(request, contextLengthWrapper);

		System.out.println("Content-Length: " + contextLengthWrapper.getHeader("Content-Length"));

	}

	@Override
	public void destroy() {
		System.out.println("Filter is destroy");
	}
}

class ResponseWrapper extends HttpServletResponseWrapper {
	final int BUFFER_SIZE = getBufferSize();
	private StringWriter sw = new StringWriter(BUFFER_SIZE);

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(sw);
	}


	public String toString() {
		return sw.toString();
	}


}