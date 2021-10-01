package ru.lanit;

import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

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

		CharResponseWrapper charResponseWrapper = new CharResponseWrapper((HttpServletResponse) response);
		response.setCharacterEncoding("UTF-8");

		chain.doFilter(request, charResponseWrapper);
		String htmlCode = charResponseWrapper.toString();
		charResponseWrapper.setHeader("Content-Length", String.valueOf(htmlCode.length()));

		Writer writer = new OutputStreamWriter(charResponseWrapper.getOutputStream(), StandardCharsets.UTF_8);

		System.out.println(htmlCode);
		writer.write(htmlCode);
		writer.close();
		charResponseWrapper.setContentLength(htmlCode.length());
		String contentLength = charResponseWrapper.getHeader("Content-Length");
		System.out.println("Content-Length: " + contentLength);

	}

	@Override
	public void destroy() {
		System.out.println("Filter is destroy");
	}
}

class CharResponseWrapper extends HttpServletResponseWrapper {
	private CharArrayWriter output;
	public String toString() {
		return output.toString();
	}
	public CharResponseWrapper(HttpServletResponse response){
		super(response);
		output = new CharArrayWriter();
	}
	public PrintWriter getWriter(){
		return new PrintWriter(output);
	}

}