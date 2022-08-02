# 3. Show the unique names of all books ordered by *lastname1 firstname1*.
SELECT db_book.Title
FROM db_book
WHERE db_book.BookID IN
   (
    SELECT db_order_detail.BookID
    FROM db_order_detail
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
   )   
;