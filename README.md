# TSheets Proxy

Acts as a simple proxy to the [TSheets by QuickBooks](https://www.tsheets.com/) API. Useful for when you are using Java 7, and you need to use the TSheets API. TSheets has recently limited their TLS cipher suites to the
four GCM ciphers. Java 8 and higher have these ciphers, but not Java 7.

# Building and Running

1. ./gradlew build
2. ./gradlew run
3. Browse to or do an HTTP GET on localhost:8080/api/v1/hello

# Docker

Replace username with yours:

1. docker build -t username/tsheets-proxy:latest .
2. docker run username/tsheets-proxy
3. Browse to or do an HTTP GET on localhost:8080/api/v1/hello
