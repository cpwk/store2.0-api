package com.maidao.edu.store.common.context;


public class Context {

    private SessionWrap session;
    private Long customerId;
    private String requestIp;

    public SessionWrap getSession() {
        return session;
    }

    public void setSession(SessionWrap session) {
        this.session = session;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
}
