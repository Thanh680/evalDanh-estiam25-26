INSERT INTO projet (id, nom) VALUES
                                 ('1', 'Project 1'),
                                 ('2', 'Project 2');

INSERT INTO intervention (projet_id, date, duree) VALUES
                                 ('1','2025-10-01 23:50:26+00', '17'),
                                 ('1','2025-10-05 12:55:54+00', '30'),
                                 ('2','2025-10-08 12:55:54+00', '1520');

INSERT INTO salarie (nom, prenom) VALUES
                                 ('John','Doe'),
                                 ('Jane','Smith'),
                                 ('Alice','Johnson');

INSERT INTO client (id, adresse, projet_id) VALUES
                                    ('1','123 Main St','1'),
                                    ('2','456 Oak Ave','2'),
                                    ('3','789 Pine Rd',NULL);

INSERT INTO app_user (id, email, password, admin, client_id) VALUES
                                                      ('1','a@a.com', '$2a$10$KBPFXGAAQIdGOkzg8S4c7OHnyB78w.BKjqAYvxP2yK5vGMTKNXBiK',true,1),
                                                      ('2','b@b.com', '$2a$10$KBPFXGAAQIdGOkzg8S4c7OHnyB78w.BKjqAYvxP2yK5vGMTKNXBiK',false,2),
                                                      ('3','c@c.com', '$2a$10$KBPFXGAAQIdGOkzg8S4c7OHnyB78w.BKjqAYvxP2yK5vGMTKNXBiK',false,3);


INSERT INTO materiel (designation) VALUES
                                    ('Laptop'),
                                    ('Projector'),
                                    ('Tablet');

