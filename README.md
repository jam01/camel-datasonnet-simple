# camel-datasonnet-simple

test

curl --location --request GET 'localhost:8080/api/pets'

curl --location --request POST 'localhost:8080/api/pets' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "seal",
    "beep": "boop3"
}'

