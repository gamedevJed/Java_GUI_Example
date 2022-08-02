# 1. Show the subject names of books supplied by *supplier2*.
SELECT db_subject.CategoryName
FROM db_subject
WHERE db_subject.SubjectID in
     (
   SELECT db_book.SubjectID
      FROM db_book
      INNER JOIN db_subject
      ON db_book.SubjectID = db_subject.SubjectID
      WHERE db_book.SupplierID = 2
  ) ;

