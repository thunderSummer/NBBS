alter table post add column brief varchar(100) not null default'这是一个简介';
alter table comment modify id int auto_increment;
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第一个用户','test1@qq.com','test1',1,now(),1,'照片的链接');
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第二个用户','test2@qq.com','test1',1,now(),1,'照片的链接');
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第三个用户','test3@qq.com','test1',2,now(),1,'照片的链接');
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第四个用户','test4@qq.com','test1',1,now(),1,'照片的链接');
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第五个用户','test5@qq.com','test1',2,now(),1,'照片的链接');
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第六个用户','test6@qq.com','test1',1,now(),1,'照片的链接');
insert into user(nickname, email, password, sex, create_time, status, avatar) VALUES ('我是第七个用户','test7@qq.com','test1',2,now(),1,'照片的链接');
insert into user_fans(fllow_id, fllowed_id) VALUES (1,2);
insert into user_fans(fllow_id, fllowed_id) VALUES (2,1);
insert into user_fans(fllow_id, fllowed_id) VALUES (1,3);
insert into user_fans(fllow_id, fllowed_id) VALUES (1,5);
insert into user_fans(fllow_id, fllowed_id) VALUES (2,7);
insert into user_fans(fllow_id, fllowed_id) VALUES (4,3);
insert into user_black (black_id, blacked_id) values (1,4);
insert into user_black (black_id, blacked_id) values (2,5);
insert into user_black (black_id, blacked_id) values (3,4);
insert into user_black (black_id, blacked_id) values (7,2);
insert into user_black (black_id, blacked_id) values (3,6);
insert into user_black (black_id, blacked_id) values (1,7);
insert into user_black (black_id, blacked_id) values (7,1);
insert into user_black (black_id, blacked_id) values (2,4);


insert into user_like(user_id, post_id) VALUES (1,1);
insert into user_like(user_id, post_id) VALUES (1,2);
insert into user_like(user_id, post_id) VALUES (1,3);
insert into user_like(user_id, post_id) VALUES (1,4);
insert into user_like(user_id, post_id) VALUES (1,5);
insert into user_like(user_id, post_id) VALUES (1,6);
insert into user_like(user_id, post_id) VALUES (1,20);
insert into user_like(user_id, post_id) VALUES (1,8);
insert into user_like(user_id, post_id) VALUES (1,9);
insert into user_like(user_id, post_id) VALUES (1,10);

insert into user_collection(user_id, post_id) VALUES (1,5);
insert into user_collection(user_id, post_id) VALUES (1,10);
insert into user_collection(user_id, post_id) VALUES (1,8);
insert into user_collection(user_id, post_id) VALUES (1,13);
insert into user_collection(user_id, post_id) VALUES (1,9);
insert into user_collection(user_id, post_id) VALUES (1,7);
insert into user_collection(user_id, post_id) VALUES (1,12);
insert into user_collection(user_id, post_id) VALUES (1,22);
insert into user_collection(user_id, post_id) VALUES (1,21);
insert into user_collection(user_id, post_id) VALUES (1,2);
insert into user_collection(user_id, post_id) VALUES (1,17);
insert into user_collection(user_id, post_id) VALUES (1,4);
insert into user_collection(user_id, post_id) VALUES (1,3);

insert into bbs_partition(title) VALUES ('本部风采');
insert into bbs_partition(title) VALUES ('软院风采');

insert into section (title, announcement, partition_id) values ('本部食堂','大家可以在这里自由讨论食堂哦',1);
insert into section (title, announcement, partition_id) values ('本部图书馆','大家可以在这里自由讨论图书馆哦',1);

insert into section (title, announcement, partition_id) values ('软院食堂','大家可以在这里自由讨论食堂哦',2);
insert into section (title, announcement, partition_id) values ('软院图书馆','大家可以在这里自由讨论图书馆哦',2);
insert into section (title, announcement, partition_id) values ('软院XXX','大家可以在这里自由讨论XXX哦',2);




insert into post(title, content, user_id, type, topping, view, create_time, section_id, last_modify, brief) VALUES ('这是第1个帖子题目','这是第1个帖子的内容',1,1,0,1,now(),1,now(),'这是第1个简介');
insert into post(title, content, user_id, type, topping, view, create_time, section_id, last_modify, brief) VALUES ('这是第2个帖子题目','这是第2个帖子的内容',2,1,0,2,now(),2,now(),'这是第2个简介');
insert into post(title, content, user_id, type, topping, view, create_time, section_id, last_modify, brief) VALUES ('这是第3个帖子题目','这是第3个帖子的内容',3,1,0,3,now(),1,now(),'这是第3个简介');
insert into post(title, content, user_id, type, topping, view, create_time, section_id, last_modify, brief) VALUES ('这是第4个帖子题目','这是第4个帖子的内容',4,1,0,4,now(),3,now(),'这是第4个简介');
insert into post(title, content, user_id, type, topping, view, create_time, section_id, last_modify, brief) VALUES ('这是第5个帖子题目','这是第5个帖子的内容',5,1,0,5,now(),1,now(),'这是第5个简介');
insert into post(title, content, user_id, type, topping, view, create_time, section_id, last_modify, brief) VALUES ('这是第6个帖子题目','这是第6个帖子的内容',6,1,1,1,now(),5,now(),'这是第6个简介');
insert into post(title, content, user_id, type, topping, view, create_time,  section_id, last_modify, brief) VALUES ('这是第7个帖子题目','这是第7个帖子的内容',7,0,0,2,now(),1,now(),'这是第7个简介');
insert into post(title, content, user_id, type, topping, view, create_time,  section_id, last_modify, brief) VALUES ('这是第8个帖子题目','这是第8个帖子的内容',7,2,0,4,now(),3,now(),'这是第8个简介');
insert into post(title, content, user_id, type, topping, view, create_time,  section_id, last_modify, brief) VALUES ('这是第9个帖子题目','这是第9个帖子的内容',5,0,0,2,now(),5,now(),'这是第9个简介');
insert into post(title, content, user_id, type, topping, view, create_time,  section_id, last_modify, brief) VALUES ('这是第10个帖子题目','这是第10个帖子的内容',6,1,0,1,now(),1,now(),'这是第10个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第11个帖子题目','这是第11个帖子的内容',3,1,0,2,now(),1,now(),'这是第11个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第12个帖子题目','这是第12个帖子的内容',1,1,0,3,now(),3,now(),'这是第12个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第13个帖子题目','这是第14个帖子的内容',2,1,0,1,now(),1,now(),'这是第13个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第14个帖子题目','这是第14个帖子的内容',6,1,0,1,now(),1,now(),'这是第14个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第15个帖子题目','这是第15个帖子的内容',3,1,0,4,now(),4,now(),'这是第15个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第16个帖子题目','这是第16个帖子的内容',1,1,1,3,now(),3,now(),'这是第16个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第17个帖子题目','这是第17个帖子的内容',5,1,1,1,now(),1,now(),'这是第17个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第18个帖子题目','这是第18个帖子的内容',6,1,0,1,now(),5,now(),'这是第18个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第19个帖子题目','这是第19个帖子的内容',7,1,0,3,now(),1,now(),'这是第19个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第20个帖子题目','这是第20个帖子的内容',3,1,2,1,now(),5,now(),'这是第20个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第21个帖子题目','这是第21个帖子的内容',2,1,0,3,now(),1,now(),'这是第21个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第22个帖子题目','这是第22个帖子的内容',1,1,0,1,now(),1,now(),'这是第22个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第23个帖子题目','这是第23个帖子的内容',0,1,0,4,now(),1,now(),'这是第23个简介');
insert into post(title, content, user_id, type, topping, view,create_time,  section_id, last_modify, brief) VALUES ('这是第24个帖子题目','这是第24个帖子的内容',2,1,2,1,now(),5,now(),'这是第24个简介');

insert into comment (content, post_id, comment_id, user_id) values ('我是第一个评论来第一篇文章',1,null,1);
insert into comment (content, post_id, comment_id, user_id) values ('我是第二个评论来第一篇文章',1,null,2);
insert into comment (content, post_id, comment_id, user_id) values ('我是第三个评论来第一篇文章',1,null,3);
insert into comment (content, post_id, comment_id, user_id) values ('我是第四个评论来第一个评论',1,1,1);
insert into comment (content, post_id, comment_id, user_id) values ('我是第五个评论来回复上一个回复我的',1,4,1);

insert into mask_word (word) values ('握草');
insert into mask_word (word) values ('去你妈的');

insert into user_word (user_id, word) values (1,'卧槽');