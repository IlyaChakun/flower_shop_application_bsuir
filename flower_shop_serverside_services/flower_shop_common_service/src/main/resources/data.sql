-- /** countries **/
insert ignore into countries(id, country_name_en, country_name_ru)
VALUES (1, 'Belarus', 'Беларусь'),
       (2, 'Russia', 'Россия');

-- /** cities **/
insert ignore into cities(id, city_name)
VALUES (1, 'Минск'),
       (2, 'Брест');
