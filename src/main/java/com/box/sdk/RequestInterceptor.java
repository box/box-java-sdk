package com.box.sdk;

/**
 * The interface for intercepting requests to the Box API.
 *
 * <p>An interceptor may handle a request in any way it sees fit. It may update a request before it's sent, or it may
 * choose to return a custom response. If an interceptor returns a null response, then the request will continue to be
 * sent to the API along with any changes that the interceptor may have made to it.</p>
 *
 * <pre>public BoxAPIResponse onRequest(BoxAPIRequest request) {
 *    request.addHeader("My-Header", "My-Value");
 *
 *    // Returning null means the request will be sent along with our new header.
 *    return null;
 * }</pre>
 *
 * <p>However, if a response is returned, then the request won't be sent and the interceptor's response will take the
 * place of the normal response.</p>
 *
 * <pre>public BoxAPIResponse onRequest(BoxAPIRequest request) {
 *    // Returning our own response means the request won't be sent at all.
 *    return new BoxAPIResponse();
 * }</pre>
 *
 * <p>A RequestInterceptor can be very useful for testing purposes. Requests to the Box API can be intercepted and fake
 * responses can be returned, allowing you to effectively test your code without needing to actually communicate with
 * the Box API.</p>
 */
public interface RequestInterceptor {
    /**
     * Invoked when a request is about to be sent to the API.
     *
     * @param request the request that is about to be sent.
     * @return an optional response to the request. If the response is null, then the request will continue to
     * be sent to the Box API.
     */
    BoxAPIResponse onRequest(BoxAPIRequest request);
}
