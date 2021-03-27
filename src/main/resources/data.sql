insert into address (`id`, `city`, `country`)
values (1, 'beijing', 'china'),
       (2, 'shenzhen', 'china');

insert into user (`id`, `name`, `age`)
values (1, 'haha', 18);

insert into user_address (`user_id`, `address_id`)
values (1, 1);
