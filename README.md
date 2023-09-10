# ToDoList
# In order to make authorized requests please use this curl to generate personal access token:
curl --request POST \
  --url https://dev-1i2dfkkyyyq015s0.us.auth0.com/oauth/token \
  --header 'content-type: application/json' \
  --data '{"client_id":"Zq0XvDLuE4OXMJLs3arqnrvdMequrTZv","client_secret":"UOuje-bQUxkrPBm0qc3PV4i09FQXjKaRawHe4ucFpLZGkCE_nWQlBOP1hTw33Euc","audience":"http://localhost:8080/api/task","grant_type":"client_credentials"}'
