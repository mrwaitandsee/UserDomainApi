SELECT "user".id, "user".name, "user".password FROM "user"
INNER JOIN "project_user" ON "user".id = "project_user".user_id
WHERE project_id = '905fe9d5-c2a0-49bb-8969-9fea110c9681';