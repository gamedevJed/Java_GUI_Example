# 17. Show the book names and their respective quantities for open orders (the orders which have not been shipped) at midnight 08/04/2016.
SELECT db_book.Title,db_order_detail.Quantity
FROM db_book, db_order,db_order_detail
WHERE db_order_detail.BookID = db_book.BookID 
AND db_order_detail.OrderID = db_order.OrderID 
AND db_order.OrderDate = '8/4/2016' 
AND (db_order.ShippedDate IS NULL OR db_order.ShippedDate > '8/4/2016')
