# 18. Show the names of customers who have ordered more than 1 book and the corresponding quantities.  List the result in the descending quantity.
SELECT db_customer.FirstName, db_customer.LastName, sum(db_order_detail.Quantity) AS total_ordered
FROM db_order_detail, db_order, db_customer
WHERE db_order.OrderID = db_order_detail.OrderID AND db_order.CustomerID = db_customer.CustomerID
GROUP BY db_customer.FirstName, db_customer.LastName
HAVING sum(db_order_detail.Quantity) >= 1
ORDER BY sum(db_order_detail.Quantity) DESC