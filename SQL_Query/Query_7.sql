# 7. Show the name of books supplied by *supplier2*.
SELECT db_book.Title
FROM db_book
INNER JOIN db_supplier
ON db_supplier.SupplierID = db_book.SupplierID
WHERE db_supplier.CompanyName = 'supplier2'