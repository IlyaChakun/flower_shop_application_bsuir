Тема: цветочный магазин.

Роли: Клиент, Админ, Курьер, Аноним.
Для регистрации есть локальный провайдер (база) и внешний - google.
Компания - базовая компания , сам цветочный магазин, где хранится инфа об организации.
Магазин - может быть один и более в компании, по разным адресам и тд. Каждый магазин менеджиться отдельно, в каждый магазин можно добавить цветы\букеты.
Цветок - продукт, который можно добавить в какой либо магазин
Букет - продукт, который можно добавить в какой либо магазин
	У обоих подразумевается одна картинка, большиство полей получаются с сервера, устанавливаются только несколькою
Корзина
Заказ
История заказов
Личный кабинет
Страница компании
Страница магазина
Каталог продуктов
Форма смены пароля
Личные кабинет всех пользователей
Страница для курьера где есть текущие заказы на сегодня



	Use cases:
0. Написать страницу login (local  + Ouath2 , использовать только Google) (после логоина подразумевается редирект на страницу продуктов)
1. Написать страницу регистрации (только local)
2. Написать страницу Личный кабинет, где будет храниться только личная информация пользователя, которую можно менять.
3. Написать страницу Компании. На этой странице можно добавлять\изменять всю инфу о компании.
4. Написать компонент для добавления Магазина (отдельную страницу)
5. Написать компонент для просмотра Магазина. 
	На этой странице есть tab (либо другое испольнение) Где храняться все Магазины компании
	Каждый магазин можно детально открыть, просмотреть\изменить информацию о нем.
6. Написать страницу добавления Цветка в Магазин для админа.
7. Написать страницу добавления Букета в Магазин для админа.
8. Написать страницы просмотра товаров 
	Страница 1. просмотр цветов + сортировка\фильтрация по базовым полям + пагинация
	Страница 2. просмот буктов + сортировка\фильатрция по базовым полям + пагинация
9. Написать компонент для детального просмотра товара (вижу как всплывающее окно по клику на товар)
10. Написать страницу Корзины. В нее добавляется товар, внутри корзины можно изменить колво, удалить товар.
	Корзина хранится в draft в БД (!) Значит при обновлении страницы данные не пропадают.
	После выбора товаров происходит оформление заказа.
10.1	Вводится информация об варианте получения (самовывоз или доставка 
	(если доставка,
		 то адрес который указывается на карте: То есть мы вводим в input адрес и нам указывается метка на Google Map 
		 и потом это все красиво отправляется на сервер для дальнейшей постройки маршрута,
		 вводится удобный день + время доставки.)
	Данные об клиенте если зареган берутся из профиля + возможность поменять номер телефона
	Если аноним то обязательно к вводу(!)
	Показывается финальная сумма, разделенная на сумму за цветы + сумма за доставку + общая сумма)
10.2 После создания заказа редирект на список заказов
10.2.1 Список заказов разделен на 2: в первом активный заказ, во втором выполненные заказы
10.3 Страница списка заказов с пагинацией и в ней лежат все заказы клиента
10.4 При нажатии на заказ открывается новой страницей и там детальная информация + статус
10.5 Если заказ был выполнен, появляется форма для отправки отзыва + бальной оценки (0-10)
11. Страницы для просмотра заказов должны быть написаны не привязываясь к юзеру, тк они будут использываться и для Админа,
	и для клиента, и для курьера.(!)
12. На странице компании у Админа есть возможность скачать отчеты по последним нескольким месяцам (чисто запрос на сервер и получается файл)
13. Написать страницу для просмотра всех зареганых пользователей админом
14. На странице Компании добавить tab для просмотра всех курьеров компании
15. На странице Компании добавить в tab для просмотра всез курьеров компании,
	добавить вспылвающее окно для добавления нового курьера (идеально взять форму регистрации юзера + дописать новые поля)
15. Написать страницу личного кабинета курьера
16. Написать страницу просмотра истории заказов у курьера ПО ДНЯМ
17. Написать страницу просмотра заказов на сегодня (то есть на день) сделать ограничение не более чем N заказов для курьера в день (мб 8)
17.1 На этой странице есть:
	1. все заказы в виде списка по порядку (сегменты доставки)  + есть чек бокс для того чтобы маркнуть что заказ выполнен
	2. карта на которой отмечены все адреса получателей
	3. рядом есть форма где отмечено:  1. Время которое будет затрачено на доставку + расстояние
					   2. Сумма денег за все заказы
18. Написать форму для всех пользователей для смены пароля	
				





Стэк на фронт: 
React, antd, redux, oauth2 support, webpack, bootstrap (optional)

Стэк на бэк:
Spring boot, web, data, security, ouath2, mail, validation, modelMapper, lombok, openpdf.

DB: mysql


Info: 
Antd components: https://ant.design/components/overview/
Oauth2 intro:    https://www.digitalocean.com/community/tutorials/oauth-2-ru
Example: 	 https://github.com/YuraLu/SpringBoot-JPA-Data-OAuth2-Security-Sensor-App/tree/master/monintorsensor_clientside
Redux info:	 https://rajdee.gitbooks.io/redux-in-russian/content/