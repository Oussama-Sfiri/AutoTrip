-- Sample data for conducteur table
INSERT INTO conducteur (matricule, cin, date_naissance, nom, prenom) VALUES
                                                                         ('C001', 'AB123456', '1990-05-15', 'Smith', 'John'),
                                                                         ('C002', 'CD987654', '1985-08-20', 'Doe', 'Jane'),
                                                                         ('C003', 'EF345678', '1992-12-10', 'Johnson', 'Michael');

-- Sample data for vehicule table
INSERT INTO vehicule (immatriculation, disponible, equipements_speciaux, kilometrage, marque, modele, type, type_permis_requis) VALUES
                                                                                                                                    ('V001', 1, 'GPS,Bluetooth', 50000, 'Toyota', 'Corolla', 'Sedan', 'B'),
                                                                                                                                    ('V002', 1, 'None', 80000, 'Honda', 'Civic', 'Sedan', 'B'),
                                                                                                                                    ('V003', 1, 'GPS', 30000, 'Ford', 'Focus', 'Hatchback', 'B');

-- Sample data for carte_grise table
INSERT INTO carte_grise (adresse, nobmre_de_place, type, premiere_mise_circulation, vehicule_immatriculation) VALUES
                                                                                                                  ('123 Main St, Anytown', 5, 'Sedan', '2019-06-15', 'V001'),
                                                                                                                  ('456 Elm St, Othertown', 4, 'Sedan', '2018-10-20', 'V002'),
                                                                                                                  ('789 Oak St, Anycity', 4, 'Hatchback', '2020-03-25', 'V003');

-- Sample data for permis table
INSERT INTO permis (numero_permis, date_remise_permis, type_permis, conducteur_matricule) VALUES
                                                                                              ('P001', '2010-01-01', 'B', 'C001'),
                                                                                              ('P002', '2015-05-20', 'B', 'C002'),
                                                                                              ('P003', '2012-08-10', 'B', 'C003');

-- Sample data for voyage table
INSERT INTO voyage (autres_details, date_arrivee_prev, date_depart, depart, destination, heure_arrivee_prev, heure_depart, nombre_passagers, status, type_vehicule, conducteur_id, vehicule_id) VALUES
                                                                                                                                                                                                    ('Business trip', '2024-03-01', '2024-02-28', 'City A', 'City B', '18:00:00', '08:00:00', 3, 0, 'Sedan', 'C001', 'V001'),
                                                                                                                                                                                                    ('Family vacation', '2024-03-10', '2024-03-05', 'City X', 'City Y', '14:00:00', '09:00:00', 4, 0, 'Sedan', 'C002', 'V002'),
                                                                                                                                                                                                    ('Weekend getaway', '2024-03-03', '2024-03-01', 'City M', 'City N', '21:00:00', '17:00:00', 2, 0, 'Hatchback', 'C003', 'V003');
