#6. Show the names of the customers who have paid less than $80 in totals.
SELECT db_customer.FirstName,db_customer.LastName
FROM db_customer
WHERE db_customer.CustomerID IN
( 
	SELECT customerID
	FROM
	(
		SELECT db_order.CustomerID, sum(db_order_detail.Quantity * db_book.UnitPrice) AS total_price
		FROM db_order_detail
		INNER JOIN db_book
		ON db_book.BookID = db_order_detail.BookID
		INNER JOIN db_order
		ON db_order.OrderID = db_order_detail.OrderID
		GROUP BY db_order.CustomerID
	) AS TABLE1
	WHERE total_price < 80
)
;