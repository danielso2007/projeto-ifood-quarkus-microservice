INSERT INTO public.localizacao(id, data_atualizacao, data_criacao, latitude, longitude)	VALUES (NEXTVAL('localizacao_id_seq'), NOW(), NOW(), -22.9014599,-43.1068623);
INSERT INTO public.localizacao(id, data_atualizacao, data_criacao, latitude, longitude)	VALUES (NEXTVAL('localizacao_id_seq'), NOW(), NOW(), -22.9014599,-43.1068623);
INSERT INTO public.localizacao(id, data_atualizacao, data_criacao, latitude, longitude)	VALUES (NEXTVAL('localizacao_id_seq'), NOW(), NOW(), -22.9032784,-43.1033432);

INSERT INTO public.restaurante(id, data_atualizacao, data_criacao, cnpj, nome, proprietario, localizacao_id) VALUES (NEXTVAL('restaurante_id_seq'), NOW(), NOW(), '96765226000152', 'Lorem ipsum dolor sit amet', 'Sophia Vera Lima', 1);
INSERT INTO public.restaurante(id, data_atualizacao, data_criacao, cnpj, nome, proprietario, localizacao_id) VALUES (NEXTVAL('restaurante_id_seq'), NOW(), NOW(), '58394438000181', 'Ut ac lacus suscipit nisl eleifend', 'Emily Clarice Ferreira', 2);
INSERT INTO public.restaurante(id, data_atualizacao, data_criacao, cnpj, nome, proprietario, localizacao_id) VALUES (NEXTVAL('restaurante_id_seq'), NOW(), NOW(), '02379021000195', 'Nulla imperdiet neque', 'Luan Guilherme da Costa', 3);

INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (NEXTVAL('prato_id_seq'), NOW(), NOW(), 'Ut ac lacus suscipit nisl eleifend varius nec vel dolor. Morbi non laoreet metus.', 'Phasellus laoreet', 87.54, 1);
INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (NEXTVAL('prato_id_seq'), NOW(), NOW(), 'Nulla imperdiet neque id gravida suscipit. Sed dignissim massa nec arcu pharetra, sit amet tristique nunc maximus.', 'Nulla imperdiet', 65.00, 1);
INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (NEXTVAL('prato_id_seq'), NOW(), NOW(), 'Sed eget neque ornare, bibendum quam non, vestibulum turpis. Nulla facilisi. Praesent venenatis pulvinar consectetur.', 'Sed eget neque', 35.90, 2);
INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (NEXTVAL('prato_id_seq'), NOW(), NOW(), 'Pellentesque quis sagittis odio, fermentum aliquam diam. Mauris vel posuere urna. Nam sed sollicitudin erat.', 'Morbi non laoreet', 15.10, 3);
