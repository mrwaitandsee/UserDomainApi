CREATE TABLE "user" (
	id UUID NOT NULL UNIQUE,
	name TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE "project" (
	id UUID NOT NULL UNIQUE,
	name TEXT NOT NULL UNIQUE,
	description TEXT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE "project_user" (
	id UUID NOT NULL UNIQUE,
	project_id UUID NOT NULL,
	user_id UUID NOT NULL,
	FOREIGN KEY (project_id) REFERENCES "project"(id),
	FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE "role" (
  id UUID NOT NULL UNIQUE,
  name TEXT NOT NULL UNIQUE
);

CREATE TABLE "permission" (
  id UUID NOT NULL UNIQUE,
  name TEXT NOT NULL UNIQUE
);

CREATE TABLE "role_permission" (
	id UUID NOT NULL UNIQUE,
	role_id UUID NOT NULL,
	permission_id UUID NOT NULL,
	FOREIGN KEY (role_id) REFERENCES "role"(id),
	FOREIGN KEY (permission_id) REFERENCES "permission"(id)
);

CREATE TABLE "user_role" (
	id UUID NOT NULL UNIQUE,
	user_id UUID NOT NULL,
	role_id UUID NOT NULL,
	FOREIGN KEY (user_id) REFERENCES "user"(id),
	FOREIGN KEY (role_id) REFERENCES "role"(id)
);