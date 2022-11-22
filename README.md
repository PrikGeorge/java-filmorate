![192549477-7d00fd78-97d4-43e2-b038-4b4f1bf104bd](https://user-images.githubusercontent.com/107940914/203314162-df796574-3fe1-4674-b62a-8f44a2dbe208.png)

Список всех фильмов:
  SELECT * FROM films;
  
Список общих друзей:
  SELECT from_id FROM friends
  WHERE to_id = 1
  AND from_id in (
    SELECT from_id FROM friends
    WHERE to_id = 2
  )
