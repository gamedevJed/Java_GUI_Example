# 13. Show the names of the customers who ordered at least 2 books.\

SELECT db_customer.FirstName, db_customer.LastName
FROM db_customer
WHERE db_customer.CustomerID IN
(
 SELECT CustomerID
 FROM db_order_detail,db_order
 WHERE db_order.OrderID = db_order_detail.OrderID
 GROUP BY CustomerID
 HAVING SUM(Quantity) >= 2
)