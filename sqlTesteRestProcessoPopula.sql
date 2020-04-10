INSERT INTO public.processo(
	 cd_processo, cd_usuario_cadastro, cd_usuario_finaliza, ds_processo, dt_parecer_inc, dt_parecer_mod, num_processo, parecer, status_finalizado)
	VALUES 
	(1, 2, 7, 'processo de licenciamento' ,null , null, '1.1.0', null, false),
	(2, 3, 7, 'processo de compra' ,null , null, '1.1.1', null, false),
	(3, 4, 8, 'processo de investimento' ,null , null, '1.1.2', null, false),
	(4, 4, 8, 'processo de gastos' ,null , null, '1.1.3', null, false),
	(5, 2, 7, 'processo de contrato' ,null , null, '1.2.0', null, false),
	(6, 3, 8, 'processo de compra' ,null , null, '1.2.1', null, false);