![192549477-7d00fd78-97d4-43e2-b038-4b4f1bf104bd](https://user-images.githubusercontent.com/107940914/203314162-df796574-3fe1-4674-b62a-8f44a2dbe208.png)

Список всех фильмов:
  SELECT * FROM films;
  
Список общих друзей:
  SELECT user_id FROM user_friends
  WHERE friend_id = 1
  AND friend_id in (
    SELECT friend_id FROM friends
    WHERE user_id = 2
  )
