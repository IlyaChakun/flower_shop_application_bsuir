/* roles */
insert ignore into roles(id, name, display_name, is_displayed)
values (1, 'ROLE_ADMIN', 'Администратор', 0);/*Не показывается в общей регистрации! */
insert ignore into roles(id, name, display_name, is_displayed)
values (2, 'ROLE_CLIENT', 'Клиент', 1);
insert ignore into roles(id, name, display_name, is_displayed)
values (3, 'ROLE_ANONYMOUS', 'Аноним', 1);
/************/


/************/
/* Типы цветов */
insert ignore into flower_types(id, flower_type)
VALUES (1, 'Розы');
insert ignore into flower_types(id, flower_type)
VALUES (2, 'Кустовые розы');
insert ignore into flower_types(id, flower_type)
VALUES (3, 'Хризантемы');
insert ignore into flower_types(id, flower_type)
VALUES (4, 'Гвоздики');
insert ignore into flower_types(id, flower_type)
VALUES (5, 'Тюльпаны');
insert ignore into flower_types(id, flower_type)
VALUES (6, 'Лилии');
insert ignore into flower_types(id, flower_type)
VALUES (7, 'Альстромерии');
/************/

# //TODO  в базу хардкодом пишем все цвета, все страны, сорта

insert ignore into colors(id, color_name)
VALUES (1, 'Красный');
insert ignore into colors(id, color_name)
VALUES (2, 'Кремовый');
insert ignore into colors(id, color_name)
VALUES (3, 'Желтый');
insert ignore into colors(id, color_name)
VALUES (4, 'Оранжевый');
insert ignore into colors(id, color_name)
VALUES (5, 'Белый');
insert ignore into colors(id, color_name)
VALUES (6, 'Розовый');
insert ignore into colors(id, color_name)
VALUES (7, 'Фиолетовый');


insert ignore into sorts(id, sort_name_en, sort_name_ru)
VALUES (1, 'Avalanche', 'Аваланш');
insert ignore into sorts(id, sort_name_en, sort_name_ru)
VALUES (2, 'Avalanche Peach', 'Аваланш Пич');
insert ignore into sorts(id, sort_name_en, sort_name_ru)
VALUES (3, 'Red Naomi', 'Красная Наоми');


/** countries **/
insert ignore into countries(id, country_name_en, country_name_ru)
VALUES (1, 'Belarus', 'Беларусь');
insert ignore into countries(id, country_name_en, country_name_ru)
VALUES (2, 'Russia', 'Россия');


/** типы букетов**/
insert ignore into bouquet_types(id, bouquet_type)
values (1, 'Букет из роз');