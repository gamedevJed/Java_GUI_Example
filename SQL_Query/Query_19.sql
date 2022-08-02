# 19. Show the names of customers who have ordered more than 3 books and their respective telephone numbers.

SELECT db_customer.Phone
FROM db_order_detail, db_order, db_customer
WHERE db_order.OrderID = db_order_detail.OrderID AND db_order.CustomerID = db_customer.CustomerID
GROUP BY db_customer.FirstName, db_customer.LastName
HAVING sum(db_order_detail.Quantity) >= 3
ORDER BY sum(db_order_detail.Quantity) DESC