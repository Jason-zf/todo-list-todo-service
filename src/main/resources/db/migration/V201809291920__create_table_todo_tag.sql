CREATE TABLE IF NOT EXISTS todo_tag(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  todo_id bigint(20),
  tag_id bigint(20),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8