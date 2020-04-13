import { http } from './config'

export default {

    listarProcesso:() => {
        return http.get('processos')
    },

    pesquisarProcessoNumero:(numProcesso) => {
        numProcesso
        return http.get('processoBuscaNumero', {
            params: {
                numProcesso: numProcesso
            }
        })
    },

    pesquisarProcessoDescricao:(dsProcesso) => {
        dsProcesso
        return http.get('processoBuscaDescricao', {
            params: {
                dsProcesso: dsProcesso
            }
        })
    },

    pesquisarProcessoUsuarioLogin:(email) => {
        email
        return http.get('processoBuscaUsuarioLogin', {
            params: {
                email: email
            }
        })
    },  


    salvarProcesso:(processo) => {
        return http.post('processo', processo)
    },

    atualizarProcesso:(processo) => {
        return http.put('processo', processo)
    }

}