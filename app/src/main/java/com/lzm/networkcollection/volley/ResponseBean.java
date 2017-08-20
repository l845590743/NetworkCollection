package com.lzm.networkcollection.volley;

/**
 * Created by lzm on 2016/11/21.
 */
public class ResponseBean {

    private Object page;
    private ResponseHeader header;
    private Responsebody body;

    public class ResponseHeader{
        private String retMessage;
        private int retStatus;

        public String getRetMessage() {
            return retMessage;
        }

        public void setRetMessage(String retMessage) {
            this.retMessage = retMessage;
        }

        public int getRetStatus() {
            return retStatus;
        }

        public void setRetStatus(int retStatus) {
            this.retStatus = retStatus;
        }
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public Responsebody getBody() {
        return body;
    }

    public void setBody(Responsebody body) {
        this.body = body;
    }
}
