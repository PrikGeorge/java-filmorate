![Untitled (1)](https://user-images.githubusercontent.com/102664845/194965004-691c041f-8b13-43c3-9173-a2bf8421bc5a.png)

В рамках группового проекта были решены таски:

Функциональность «Лента событий» (ветка: add-feed)
Добавление режиссёров в фильмы (ветка: add-director)
Функциональность «Рекомендации» (ветка: add-recommendations)
Функциональность «Отзывы» (ветка: add-reviews)
Удаление фильмов и пользователей (ветка: add-remove-endpoint)
Функциональность «Общие фильмы» (ветка: add-common-films)
Вывод самых популярных фильмов по жанру и годам (ветка: add-most-populars)

Список всех фильмов:
  SELECT * FROM films;
  
Список общих друзей:
  SELECT user_id FROM user_friends
  WHERE friend_id = 1
  AND friend_id in (
    SELECT friend_id FROM friends
    WHERE user_id = 2
  )
