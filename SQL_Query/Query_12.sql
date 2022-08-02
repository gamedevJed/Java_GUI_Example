#12. Show the names of all the ordered books and their total quantities.  List the result in ascending quantity.
SELECT db_book.Title, sum(db_book.UnitPrice * db_order_detail.Quantity) AS 'total_price of ordered book'
FROM db_book,db_order_detail
WHERE db_book.BookID = db_order_detail.BookID
GROUP BY db_book.Title
ORDER BY sum(db_book.UnitPrice * db_order_detail.Quantity)