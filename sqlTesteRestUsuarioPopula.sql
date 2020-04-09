INSERT INTO public.usuario(
	cd_usuario, dt_cadastro, email, nome, perfil_usuario, status)
VALUES 
(1, now(), 'admin@teste.com', 'Admin', 'ADMIN', true),
(2, now(), 'teste1@teste.com', 'teste1', 'DISTRIBUIDOR', true),
(3, now(), 'teste2@teste.com', 'teste2', 'DISTRIBUIDOR', true),
(4, now(), 'teste3@teste.com', 'teste3', 'DISTRIBUIDOR', true),
(5, now(), 'teste4@teste.com', 'teste4', 'DISTRIBUIDOR', true),
(6, now(), 'teste5@teste.com', 'teste5', 'DISTRIBUIDOR', true),
(7, now(), 'teste6@teste.com', 'teste6', 'FINALIZADOR', true),
(8, now(), 'teste7@teste.com', 'teste7', 'FINALIZADOR', true);
	