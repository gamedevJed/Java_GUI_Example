# 2. Show the name and price of the most expensive book supplied by *supplier3*.
SELECT db_book.UnitPrice, db_book.Title 
FROM db_book 
WHERE db_book.UnitPrice  in 
 (
  SELECT MAX(db_book.UnitPrice)
  FROM db_book
  WHERE db_book.SupplierID = 3
 )
;