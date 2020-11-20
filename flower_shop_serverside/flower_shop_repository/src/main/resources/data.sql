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
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (1, now(), now(), 'Розы');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (2, now(), now(), 'Кустовые розы');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (3, now(), now(), 'Хризантемы');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (4, now(), now(), 'Гвоздики');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (5, now(), now(), 'Тюльпаны');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (6, now(), now(), 'Лилии');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (7, now(), now(), 'Альстромерии');
/************/

# //TODO  в базу хардкодом пишем все цвета, все страны, сорта

insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (1, now(), now(), 'Красный');
insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (2, now(), now(), 'Кремовый');
insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (3, now(), now(), 'Желтый');
insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (4, now(), now(), 'Оранжевый');
insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (5, now(), now(), 'Белый');
insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (6, now(), now(), 'Розовый');
insert ignore into colors(id, date_of_creation, date_of_last_update, color_name)
VALUES (7, now(), now(), 'Фиолетовый');


insert ignore into sorts(id, date_of_creation, date_of_last_update, sort_name_en, sort_name_ru)
VALUES (1, now(), now(), 'Avalanche', 'Аваланш');
insert ignore into sorts(id, date_of_creation, date_of_last_update, sort_name_en, sort_name_ru)
VALUES (2, now(), now(), 'Avalanche Peach', 'Аваланш Пич');
insert ignore into sorts(id, date_of_creation, date_of_last_update, sort_name_en, sort_name_ru)
VALUES (3, now(), now(), 'Red Naomi', 'Красная Наоми');


/** countries **/
insert ignore into countries(id, date_of_creation, date_of_last_update, country_name_en, country_name_ru)
VALUES (1, now(), now(), 'Belarus', 'Беларусь');
insert ignore into countries(id, date_of_creation, date_of_last_update, country_name_en, country_name_ru)
VALUES (2, now(), now(), 'Russia', 'Россия');

