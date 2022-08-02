# 8. Show the total price each customer paid and their names.  List the result in descending price.

/*
SELECT * 
FROM 
( 
 	SELECT sum(db_order_detail.Quantity * db_book.UnitPrice) AS total_price, db_customer.FirstName,db_customer.LastName
	FROM db_order_detail
	INNER JOIN db_book
	ON db_book.BookID = db_order_detail.BookID
	INNER JOIN db_order
	ON db_order.OrderID = db_order_detail.OrderID
	INNER JOIN db_customer
	ON db_order_detail.CustomerID = db_customer.CustomerID
	GROUP BY db_order.CustomerID
	ORDER BY total_price desc
) AS TEMPTABLE1
;
*/

SELECT sum(db_order_detail.Quantity * db_book.UnitPrice) AS total_price, db_customer.FirstName,db_customer.LastName
FROM db_order_detail, db_order, db_book, db_customer
WHERE db_order.CustomerID = db_customer.CustomerID AND db_order.OrderID = db_order_detail.OrderID AND db_book.BookID = db_order_detail.BookID
GROUP BY db_customer.FirstName, db_customer.LastName
ORDER BY total_price DESC







