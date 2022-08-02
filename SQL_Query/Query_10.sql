#10. Show the unique names of all the books *lastname1 firstname1* and *lastname4 firstname4* *both* ordered.

SELECT db_book.Title
FROM db_book
WHERE db_book.BookID IN
(
 SELECT distinct db_order_detail.BookID
 FROM db_order_detail,db_order,db_customer
 WHERE db_customer.CustomerID = db_order.CustomerID AND db_order.OrderID = db_order_detail.OrderID
 AND (db_customer.LastName = 'lastname1' AND db_customer.FirstName = 'firstname1') AND db_order_detail.BookID IN  
	(
	 SELECT distinct db_order_detail.BookID
	 FROM db_order_detail,db_order,db_customer
	 WHERE db_customer.CustomerID = db_order.CustomerID AND db_order.OrderID = db_order_detail.OrderID
	 AND (db_customer.LastName = 'lastname4' AND db_customer.FirstName = 'firstname4')
	)
)