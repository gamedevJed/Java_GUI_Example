# 9. Show the names of all the books shipped on 08/04/2016 and their shippers' names.

SELECT db_book.Title, db_shipper.ShpperName
FROM db_book,db_shipper
WHERE (db_book.BookID,db_shipper.ShipperID) IN 
(
 SELECT db_book.BookID,db_shipper.ShipperID
 FROM db_order_detail,db_order
 WHERE db_shipper.ShipperID = db_order.ShipperID AND db_order_detail.OrderID = db_order.OrderID AND db_order_detail.BookID = db_book.BookID AND db_order.ShippedDate = '8/4/2016'
)