# Getting Started
Чтобы начать игру, следует обратиться к методу /demo/v1/config
и создать игру, задать имя игры и количество игроков помимо программы. 
Т.е. если мы зададим 2, предполагается что играют 2 человека и программа.
Так же важно указать статус NEW_GAME. Если задать статус STOP_GAME, 
игра будет остановлена и играть в нее не получится. Следует создать новую игру.

Игра проверяет ответы по кругу и если игрок совершил ошибку, 
игра исключает игрока и делает отметку в комментарии, что исключен один игрок 
и ожидает следующий номер от следующего или называет следующую цифру в качестве ответа. 
Если последний игрок сделал ошибку, игра прекращается и в комментарий пишется 
что игра окончена и программа победила. Ошибок делать программа не умеет сама.

При разработке создан каркас будущего приложения корпоративного уровня, 
но я не писал функционал и фичи которые не требуются от этого демо-MVP.

Например, можно было бы создать иерархию исключений в соответствии с контрактом 
обработки фронтом ошибок сервера.
Так же вопрос может вызвать сортировка объектов по пакетам, но обычно структура 
расположения зависит от соглашения и может меняться по мере роста проекта.

По адресу http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ 
можно проверить функционал игры.

Многие классы покрыты комментариями там где это необходимо.

Надеюсь, такая реализация достаточна для зачета как тестовое задание.

Игра разрабатывалась и тестировалась на Liberica 11-full
