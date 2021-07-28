INSERT INTO public.prato_cliente(prato, cliente) VALUES (1, 'Daniel Oliveira');
INSERT INTO public.prato_cliente(prato, cliente) VALUES (2, 'Edson Breno da Costa');
INSERT INTO public.prato_cliente(prato, cliente) VALUES (3, 'Priscila Elza Moraes');
INSERT INTO public.prato_cliente(prato, cliente) VALUES (1, 'Hadassa Luzia Viana');

INSERT INTO public.localizacao(id, data_atualizacao, data_criacao, latitude, longitude)	VALUES (1, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', -22.9014599,-43.1068623);
INSERT INTO public.localizacao(id, data_atualizacao, data_criacao, latitude, longitude)	VALUES (2, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', -22.9014599,-43.1068623);
INSERT INTO public.localizacao(id, data_atualizacao, data_criacao, latitude, longitude)	VALUES (3, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', -22.9032784,-43.1033432);

INSERT INTO public.restaurante(id, data_atualizacao, data_criacao, cnpj, nome, proprietario, localizacao_id) VALUES (1, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', '96765226000152', 'Lorem ipsum dolor sit amet', 'Sophia Vera Lima', 1);
INSERT INTO public.restaurante(id, data_atualizacao, data_criacao, cnpj, nome, proprietario, localizacao_id) VALUES (2, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', '58394438000181', 'Ut ac lacus suscipit nisl eleifend', 'Emily Clarice Ferreira', 2);
INSERT INTO public.restaurante(id, data_atualizacao, data_criacao, cnpj, nome, proprietario, localizacao_id) VALUES (3, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', '02379021000195', 'Nulla imperdiet neque', 'Luan Guilherme da Costa', 3);

INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (1, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', 'Ut ac lacus suscipit nisl eleifend varius nec vel dolor. Morbi non laoreet metus.', 'Phasellus laoreet', 87.54, (SELECT r.id FROM public.restaurante r WHERE r.nome = 'Lorem ipsum dolor sit amet'));
INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (2, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', 'Nulla imperdiet neque id gravida suscipit. Sed dignissim massa nec arcu pharetra, sit amet tristique nunc maximus.', 'Nulla imperdiet', 65.00, (SELECT r.id FROM public.restaurante r WHERE r.nome = 'Lorem ipsum dolor sit amet'));
INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (3, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', 'Sed eget neque ornare, bibendum quam non, vestibulum turpis. Nulla facilisi. Praesent venenatis pulvinar consectetur.', 'Sed eget neque', 35.90, (SELECT r.id FROM public.restaurante r WHERE r.nome = 'Ut ac lacus suscipit nisl eleifend'));
INSERT INTO public.prato(id, data_atualizacao, data_criacao, descricao, nome, preco, restaurante_id) VALUES (4, '2021-01-01 00:00:00.00000', '2021-01-01 00:00:00.00000', 'Pellentesque quis sagittis odio, fermentum aliquam diam. Mauris vel posuere urna. Nam sed sollicitudin erat.', 'Morbi non laoreet', 15.10, (SELECT r.id FROM public.restaurante r WHERE r.nome = 'Nulla imperdiet neque'));
