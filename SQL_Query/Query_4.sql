# 4. Show the title of books which have more than 10 units in stock.
SELECT db_book.Title
FROM db_book
WHERE db_book.Quantity >= 10