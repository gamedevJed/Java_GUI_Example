# 16. Show the name and total sale (price of orders) of each employee.

SELECT db_employee.FirstName,db_employee.LastName,sum(db_order_detail.Quantity * db_book.UnitPrice) AS 'total_price'
FROM db_order_detail, db_order,db_book,db_employee
WHERE db_order.OrderID = db_order_detail.OrderID AND db_order_detail.BookID = db_book.BookID AND db_order.EmployeeID = db_employee.EmployeeID
GROUP BY db_employee.EmployeeID
