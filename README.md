# spring-boot-tomcat-dynamic-ports

Sample project demonstrating how to add Tomcat ports dynamically with a simple url filter.

Stackoverflow Reference: https://stackoverflow.com/questions/57491231/spring-boot-how-to-dynamically-add-new-tomcat-connector/57634179?noredirect=1#comment101723738_57634179



# How to test it?

In a browser:

1 - try:

http://localhost:8080/admin/hello

Expected Response: hello test

2 - try:

http://localhost:8080/admin/addNewPort?port=9090&context=alternative

Expected Response: Added port:9090

3 - try:

http://localhost:9090/alternative/hello

Expected Response: hello test 2

4 - try expected errors:

http://localhost:9090/alternative/addNewPort?port=8181&context=alternative

Expected Response (context [alternative] allowed but endpoint not registered in controller for this context): Whitelabel Error Page...

http://localhost:9090/any/hello

Expected Response (context [any] not allowed): Whitelabel Error Page...

http://localhost:8888/any/hello

Expected Response (invalid port number): ERR_CONNECTION_REFUSED

http://localhost:8080/hello

Expected Response (no context allowed [/hello]): Whitelabel Error Page...
