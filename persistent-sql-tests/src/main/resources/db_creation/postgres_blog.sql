CREATE SCHEMA IF NOT EXISTS blog;

CREATE TABLE IF NOT EXISTS blog.posts (
  post_id SERIAL NOT NULL,
  PRIMARY KEY (post_id)
);

CREATE TABLE IF NOT EXISTS blog.users (
  user_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS blog.blogs (
  blog_name VARCHAR(256) NOT NULL,
  PRIMARY KEY (blog_name),
  title     TEXT
);
--
-- CREATE TABLE IF NOT EXISTS blog.tags (
--   tag_code        VARCHAR(50)  NOT NULL,
--   blog_name       VARCHAR(256) NOT NULL REFERENCES blog.blogs (blog_name) ON UPDATE CASCADE ON DELETE CASCADE,
--   PRIMARY KEY (tag_code, blog_name),
--   tag_description TEXT         NOT NULL
-- );
--
-- CREATE TABLE IF NOT EXISTS blog.post_tags (
--   post_id  INT         NOT NULL REFERENCES blog.posts (post_id) ON DELETE CASCADE ON UPDATE CASCADE,
--   tag_code VARCHAR(50) NOT NULL REFERENCES blog.tags (tag_code),
--   PRIMARY KEY (post_id, tag_code)
-- );