# TSheets Proxy

Acts as a simple proxy to the [TSheets by QuickBooks](https://www.tsheets.com/) API. Useful for when you are using Java 7, and you need to use the TSheets API. TSheets has recently limited their TLS cipher suites to the
four GCM ciphers. Java 8 and higher have these ciphers, but not Java 7.