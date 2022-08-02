# 15. Show the name of the customer who has ordered at least one book written by *author1*.
SELECT db_customer.FirstName, db_customer.LastName
FROM db_order_detail, db_order,db_customer,db_book
WHERE db_order_detail.BookID = db_book.BookID 
AND db_order_detail.OrderID = db_order.OrderID 
AND db_order.CustomerID = db_customer.CustomerID
AND Author = 'author1' AND db_order_detail.Quantity >= 1