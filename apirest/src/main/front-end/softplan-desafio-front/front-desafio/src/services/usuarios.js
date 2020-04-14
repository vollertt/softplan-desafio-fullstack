import { http } from './config'

export default {

    buscarUsuarioLogin:(email) => {
        email
        return http.get('usuarioLogin', {
            params: {
                email: email
            }
        })
    },


    listaUsuarioPerfil:(perfilUsuario) => {
        perfilUsuario
        return http.get('usuarioPerfil', {
            params: {
                perfilUsuario: perfilUsuario
            }
        })
    },

    listarUsuario:() => {
        return http.get('usuarios')
    },

    pesquisarUsuario:(searchField) => {
        searchField
        return http.get('usuarioBusca', {
            params: {
                searchField: searchField
            }
        })
    },

    salvarUsuario:(usuario) => {
        return http.post('usuario', usuario)
    },

    atualizarUsuario:(usuario) => {
        return http.put('usuario', usuario)
    }

}