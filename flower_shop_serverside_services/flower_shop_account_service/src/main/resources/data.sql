insert ignore into user_roles(id, display_name, is_displayed, name)
values (1, 'Клиент', true, 'ROLE_CLIENT'),
       (2, 'Флорист', false, 'ROLE_FLORIST'),
       (3, 'Админ', false, 'ROLE_ADMIN');
