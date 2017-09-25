resources/mapper/BookDao.xml相当于对BookDao(interface)的实现

分页查询语句:mysql + mybatis

```sql
SELECT 
	book_id,
	name,
	number
FROM
	book_id
LIMIT
	#{offset}, #{limit}
```

INSERT ignore INTO是如果数据库中已有此数据，则忽略；没有，则插入此条数据，避免重复插入。
```sql
INSERT ignore INTO appointment (book_id, student_id) 
VALUES (#{bookId}, #{studentId})
```
内联结，即将符合条件`a.book_id = b.book_id`的元组选择出来。
```sql
SELECT
	a.book_id,
	a.student_id,
	a.appoint_time,
	b.book_id "book.book_id",
	b.name "book.name",
	b.number "book.number"
FROM
	appointment a
INNER JOIN book b ON a.book_id = b.book_id
WHERE
	a.book_id = 1000
AND a.student_id = 12345678910;
```

mapper总结：`namespace`是该xml对应的接口全名，`select`和`update`中的`id`对应方法名，`resultType`是返回值类型，`parameterType`是参数类型（这个其实可选），最后`#{...}`中填写的是方法的参数。