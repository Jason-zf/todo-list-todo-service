CREATE TABLE IF NOT EXISTS tag(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar (40),
  user_id bigint(20),
  PRIMARY KEY (id)
)