<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-controller="MainController" ng-app="RCApp">
<head>
    <link href="<c:url value="/resources/img/favicon.ico"/>" type="image/vnd.microsoft.icon" rel="shortcut icon">
    <title ng-bind-html="pageHeaderTitle"></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/refresh.css"/>"/>

    <script src="<c:url value="/resources/js/libx/angular/angular.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/angular/angular-route.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/angular/angular-sanitize.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/angular/angular-cookies.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/jquery/jquery-2.0.3.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/jquery/jquery.cookie.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/jquery/jquery-ui.custom.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/RCLibXCore.js"/>"></script>
    <script src="<c:url value="/system/configJs"/>"></script>
    <script src="<c:url value="/resources/js/libx/RCLibX.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/utils/Date.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/utils/Number.js"/>"></script>
    <script src="<c:url value="/resources/js/common/lang.js"/>"></script>
    <script src="<c:url value="/resources/js/libx/angular.extend/angular.extend.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</head>
<body>
<div id="app-main"/>
</body>
</html>
