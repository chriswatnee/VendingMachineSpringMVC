<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container text-center">
            <h1>Vending Machine</h1>
            <hr>
            <div class="row">
                <div class="col-md-8">
                    <div class="row">
                        <c:forEach var="item" items="${itemList}">
                            <div class="col-md-4">
                                <a href="selectItem?id=${item.id}">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <p class="text-left"><c:out value="${item.id}"/></p>
                                            <p><c:out value="${item.name}"/></p>
                                            <p>$<c:out value="${item.price}"/></p>
                                            <br>
                                            <br>
                                            <p>Quantity Left: <c:out value="${item.quantity}"/></p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-4">
                    <form class="form-horizontal" role="form" action="insertMoney" method="POST">
                        <div class="form-group form-group-lg">
                            <label class="h3" for="money">Total $ In</label>
                            <input type="number" class="form-control" readonly value="<c:if test="${balance.unscaledValue() > 0}">${balance}</c:if>">
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 text-right">
                                <button class="btn btn-primary" type="submit" name="money" value="1">Add Dollar</button>
                            </div>
                            <div class="col-md-6 text-left">
                                <button class="btn btn-primary" type="submit" name="money" value=".25">Add Quarter</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 text-right">
                                <button class="btn btn-primary" type="submit" name="money" value=".1">Add Dime</button>
                            </div>
                            <div class="col-md-6 text-left">
                                <button class="btn btn-primary" type="submit" name="money" value=".05">Add Nickel</button>
                            </div>
                        </div>
                    </form>
                    <hr>
                    <form class="form-horizontal" role="form" action="purchaseItem" method="POST">
                        <div class="form-group form-group-lg">
                            <label class="h3" for="message">Messages</label>
                            <input type="text" class="form-control" readonly value="${message}">
                        </div>
                        <div class="form-group form-group-lg">
                            <label class="col-md-2 control-label" for="item">Item:</label>
                            <div class="col-md-10">
                                <input type="number" class="form-control" name="itemIdSelected" readonly value="<c:if test="${itemIdSelected != 0}">${itemIdSelected}</c:if>">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" <c:if test="${itemIdSelected == 0}">disabled</c:if>>Make Purchase</button>
                    </form>
                    <hr>
                    <form class="form-horizontal" role="form" action="returnChange" method="POST">
                        <div class="form-group form-group-lg">
                            <label class="h3" for="change">Change</label>
                            <input type="text" class="form-control" readonly value="${change}">
                        </div>
                        <button type="submit" class="btn btn-primary">Change Return</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
