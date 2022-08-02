# 11. Show the names of all the books *lastname6 firstname6* was responsible for.

SELECT db_book.Title
FROM db_book
WHERE db_book.BookID IN 
(
 SELECT db_order_detail.BookID
 FROM db_order_detail,db_employee,db_order
 WHERE db_employee.EmployeeID = db_order.EmployeeID 
 AND db_order.OrderID = db_order_detail.OrderID 
 AND (db_employee.FirstName = 'firstname6' AND db_employee.LastName = 'lastname6')
)