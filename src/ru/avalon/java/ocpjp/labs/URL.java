package ru.avalon.java.ocpjp.labs;

public class URL {
    private String driver;
    private String host;
    private String port;
    private String database;

    public String getURL() {
        return driver + "://" + host + ":" + port + "/" + database;
    }

    public static URLBuilder builder() {
        return new URLBuilder();
    }

    static class URLBuilder {
        private URL urlInstance = new URL();

        public URLBuilder() {
        }

        public URLBuilder driver(String driver) {
            urlInstance.driver = driver;
            return this;
        }

        public URLBuilder host(String host) {
            urlInstance.host = host;
            return this;
        }

        public URLBuilder port(String port) {
            urlInstance.port = port;
            return this;
        }

        public URLBuilder database(String database) {
            urlInstance.database = database;
            return this;
        }

        public URL build() {
            URL result = urlInstance;
            urlInstance = new URL();
            return result;
        }
    }
}
