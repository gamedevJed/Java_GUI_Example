# 5. Show the total price *lastname1 firstname1* has paid for the books.

SELECT sum(db_order_detail.Quantity * db_book.UnitPrice) AS Total_Price
FROM db_order_detail
INNER JOIN db_book
ON db_order_detail.BookID = db_book.BookID
WHERE db_order_detail.OrderID IN 
  (
   SELECT db_order.OrderID
   FROM db_order
   WHERE db_order.CustomerID IN 
     (
      SELECT db_customer.CustomerID
      FROM db_customer
      WHERE db_customer.LastName = 'lastname1' AND db_customer.FirstName = 'firstname1'
     )
   )
;