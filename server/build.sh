#!/bin/bash

javac -cp webroot/WEB-INF/lib/sqlite-jdbc.jar:webroot/WEB-INF/lib/servlet-api.jar:webroot/WEB-INF/lib/org.json.jar:webroot/WEB-INF/classes/ webroot/WEB-INF/classes/servlets/SendPlayersServlet.java
