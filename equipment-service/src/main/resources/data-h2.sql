INSERT INTO TBL_PROVIDER (id, name, phone, email) values (1, 'Provider 1', '06890321', 'provider1@company.com');
INSERT INTO TBL_PROVIDER (id, name, phone, email) values (2, 'Provider 2', '05453381', 'provider2@company.com');
INSERT INTO TBL_PROVIDER (id, name, phone, email) values (3, 'Provider 3', '04947335', 'provider3@company.com');
INSERT INTO TBL_PROVIDER (id, name, phone, email) values (4, 'Provider 4', '01742586', 'provider4@company.com');
INSERT INTO TBL_PROVIDER (id, name, phone, email) values (5, 'Provider 5', '09964555', 'provider5@company.com');

INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (1, 'Medical masks', 'Medical masks');
INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (2, 'Gloves', 'Gloves');
INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (3, 'Sanitizer', 'Sanitizer');
INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (4, 'Surgical Masks', 'Surgical Masks');
INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (5, 'Medical Gowns', 'Medical Gowns');
INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (6, 'Respirator', 'Respirator');
INSERT INTO TBL_EQUIPMENT_TYPE (id, name, description) values (7, 'PCR kit', 'PCR kit');

INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (1, 1, 3, 51, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (2, 1, 1, 25, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (3, 2, 5, 65, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (4, 2, 1, 52, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (5, 3, 2, 24, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (6, 3, 2, 64, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (7, 3, 2, 53, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (8, 4, 1, 15, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (9, 4, 1, 54, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (10, 5, 5, 373, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (11, 5, 3, 378, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (12, 6, 1, 185, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (13, 6, 4, 35, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (14, 6, 5, 487, 0);
INSERT INTO TBL_EQUIPMENT(id, id_type, provider_id, quantity, disponibility) values (15, 7, 1, 347, 0);
