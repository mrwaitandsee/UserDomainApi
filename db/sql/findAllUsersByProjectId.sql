SELECT "user".id, "user".name, "user".password FROM "user"
INNER JOIN "project_user" ON "user".id = "project_user".user_id
WHERE project_id = '975eac3a-cba1-11ec-9d64-0242ac120000';