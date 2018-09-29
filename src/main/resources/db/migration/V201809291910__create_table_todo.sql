CREATE TABLE IF NOT EXISTS todo(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar (40),
  status varchar (40),
  due_date date ,
  user_id bigint(20),
  PRIMARY KEY (id)
)