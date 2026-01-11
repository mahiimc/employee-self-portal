-- ==============================================
-- Organization
-- ==============================================

CREATE TABLE organizations (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    type VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

INSERT INTO organizations(name, type, status, created_at, updated_at)
VALUES ('SYSTEM', 'SUPER', 'ACTIVE', now(), now());


-- ==============================================
-- USERS
-- ==============================================

CREATE TABLE users (

    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL,
    username VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user_org FOREIGN KEY (org_id) REFERENCES organizations(id)
);

CREATE UNIQUE INDEX uk_org_username ON users (org_id, username);










