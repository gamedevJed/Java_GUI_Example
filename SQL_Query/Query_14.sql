# 14. Show the name of the customers who have ordered at least a book in *category3* or *category4* and the book names.
(
	SELECT db_customer.FirstName, db_customer.LastName,db_book.Title
	FROM db_order_detail,db_order,db_customer,db_subject,db_book
	WHERE db_order_detail.BookID = db_book.BookID          
	AND db_order_detail.OrderID = db_order.OrderID 
	AND db_order.CustomerID = db_customer.CustomerID 
	AND db_book.SubjectID = db_subject.SubjectID
	AND CategoryName = 'category3'
)
UNION
(
	SELECT db_customer.FirstName, db_customer.LastName,db_book.Title
	FROM db_order_detail,db_order,db_customer,db_subject,db_book
	WHERE db_order_detail.BookID = db_book.BookID          
	AND db_order_detail.OrderID = db_order.OrderID 
	AND db_order.CustomerID = db_customer.CustomerID 
	AND db_book.SubjectID = db_subject.SubjectID
	AND CategoryName = 'category4'
)