INSERT INTO roles(name, created_at, updated_at) VALUES('ROLE_FREE_USER', now(), now()) ON CONFLICT DO NOTHING;
INSERT INTO roles(name, created_at, updated_at) VALUES('ROLE_SUBSCRIBED_USER', now(), now()) ON CONFLICT DO NOTHING;
INSERT INTO roles(name, created_at, updated_at) VALUES('ROLE_ADMIN', now(), now()) ON CONFLICT DO NOTHING;